package ru.gb.cardServise.models.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
//базовый класс карт выпущенных банком и используемых при открытии продукта при необходимости
@Entity
@Data
@Table(name = "card")
public class CardData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "card_number", nullable = false,updatable = false, unique = true)
    private String cardNumber;

    @Column(name = "is_using", nullable = false)
    private Boolean isUsing = false;


}
