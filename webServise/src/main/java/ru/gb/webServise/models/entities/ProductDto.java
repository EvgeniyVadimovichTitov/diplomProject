package ru.gb.webServise.models.entities;


import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDto {

    private UUID id = UUID.randomUUID();

    private ProductName name;

    private UUID client;

    private String number;

    private BigDecimal balance;

    private boolean isMainCard = false;
}