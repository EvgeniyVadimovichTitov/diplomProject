package ru.gb.webServise.models.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {

    private String name;

    private String surname;

    private String patronymic;

    private String phoneNumber;

    private String login;

    private String password;

    private String PasswordConfirm;
}
