package ru.gb.cardServise.models.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.cardServise.models.domain.CardData;
import ru.gb.cardServise.models.interfaces.IServiseRep;
import ru.gb.cardServise.models.repositories.CardDataRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ServiseCardData implements IServiseRep {

    private final CardDataRepository cardDataRepository;

    @Override
    public CardData updateCardData(CardData cardData) throws NoSuchElementException {
        CardData cardDataDb = findByCardNumber(cardData.getCardNumber());
        cardDataDb.setIsUsing(cardData.getIsUsing());
        return cardDataRepository.save(cardDataDb);
    }


    @Override
    public List<CardData> addAll(List<CardData> cardDataList) throws RuntimeException {
        return cardDataRepository.saveAll(cardDataList);
    }

    @Override
    public CardData findIsNotUsedCard() throws NoSuchElementException {
        return cardDataRepository.findFirstByIsUsing(false);
    }

    @Override
    public List<CardData> findAll() {
        return cardDataRepository.findAll();
    }

    @Override
    public CardData findByCardNumber(String cardNumber) throws NoSuchElementException {
        return cardDataRepository.findByCardNumber(cardNumber);
    }
}
