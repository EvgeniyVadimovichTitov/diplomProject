package ru.gb.webServise.models.dto;


import lombok.Data;

@Data
public class ClientDataDto {

    private String name;

    private String surname;

    private String patronymic;

    private String phoneNumber;

    public ClientDataDto(String name, String surname, String patronymic, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
    }

}
