package ru.gb.cardServise.models.servises;

import org.springframework.stereotype.Service;
import ru.gb.cardServise.models.domain.CardData;
import ru.gb.cardServise.models.exceptions.ExceptionCardNumberIncorrect;
import ru.gb.cardServise.models.exceptions.ExceptionIsUsingIncorrect;
import ru.gb.cardServise.models.interfaces.IMapper;

import java.util.regex.Pattern;

@Service
public class MapperCsv implements IMapper {
    @Override
    public CardData map(String inputText) throws ExceptionCardNumberIncorrect, ExceptionIsUsingIncorrect{
        String [] buff = inputText.split(";");
        //валидация карты
        if(сardNumberIsValidate(buff[0])){
            if(isUsingIsValidate(buff[1])) {
                //преобразование строки в объект cardData
                boolean isUsing = buff[1].equals("1");
                CardData cardData = new CardData();
                cardData.setCardNumber(buff[0]);
                cardData.setIsUsing(isUsing);
                return cardData;
            }
            //ошибка если признак использования невалиден
            throw new ExceptionIsUsingIncorrect("isUsing is incorrect!");
        }
        //ошибка если номер карты невалиден
        throw new ExceptionCardNumberIncorrect("cardNumber is incorrect!");
    }
    //валидация номера карты по regex
    private boolean сardNumberIsValidate(String cardNumber){
        return Pattern.matches("\\d{16}", cardNumber);
    }
    //валидация признака использования карты по regex
    private boolean isUsingIsValidate(String isUsing){
        return Pattern.matches("[01]", isUsing);
    }

}
