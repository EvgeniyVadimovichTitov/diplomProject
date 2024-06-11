package ru.gb.webServise.models.domain;

import lombok.Data;

import java.util.UUID;

//класс для отображения в таблице на странице работы с картами (передаем в Model)
@Data
public class CardData {

    private UUID id;


    private String cardNumber;


    private Boolean isUsing;


}
