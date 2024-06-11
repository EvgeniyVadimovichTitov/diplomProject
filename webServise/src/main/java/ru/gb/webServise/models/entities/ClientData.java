package ru.gb.webServise.models.entities;

//Сущность клиента таблица master

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ClientData {

    private UUID uuid;

    private String name;

    private String surname;

    private String patronymic;

    private String phoneNumber;

    private List<Product> products = new ArrayList<>();

}
