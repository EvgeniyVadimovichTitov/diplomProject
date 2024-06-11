package ru.gb.webServise.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

//базовый класс карт выпущенных банком и используемых при открытии продукта при необходимости

@Data
public class CardData {

    private UUID id;

    private String cardNumber;

    private Boolean isUsing;


}
