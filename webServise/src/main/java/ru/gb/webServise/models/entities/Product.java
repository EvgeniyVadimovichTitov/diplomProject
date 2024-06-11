package ru.gb.webServise.models.entities;


import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Product {

    private UUID id = UUID.randomUUID();

    private ProductName name;

    private ClientData client;

    private String number;

    private BigDecimal balance;

    private boolean isMainCard = false;
}
