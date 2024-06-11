package ru.gb.cardServise.models.interfaces;

import ru.gb.cardServise.models.domain.CardData;

import java.util.List;
import java.util.NoSuchElementException;
//интерфейс сервиса для работы с репозиторием
public interface IServiseRep {
    List<CardData> addAll(List<CardData> cardDataList) throws RuntimeException;
    CardData findIsNotUsedCard() throws NoSuchElementException;
    List<CardData> findAll();
    CardData findByCardNumber(String cardNumber) throws NoSuchElementException;
    CardData updateCardData(CardData cardData) throws NoSuchElementException;
}
