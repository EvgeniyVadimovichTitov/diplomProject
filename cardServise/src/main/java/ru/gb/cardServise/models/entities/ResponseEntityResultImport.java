package ru.gb.cardServise.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//кастомный респонс для ответа на отправку файла
@AllArgsConstructor
public class ResponseEntityResultImport {
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private int errCode;

}
