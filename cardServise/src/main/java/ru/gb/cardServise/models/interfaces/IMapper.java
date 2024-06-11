package ru.gb.cardServise.models.interfaces;

import ru.gb.cardServise.models.domain.CardData;
//интерфейс преобразования типов
public interface IMapper {
    CardData map(String inputText);
}
