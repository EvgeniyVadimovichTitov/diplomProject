package ru.gb.cardServise.models.exceptions;
//Загрузка пустого файла
public class ExceptionFileIsEmpty extends RuntimeException{
    public ExceptionFileIsEmpty(String message) {
        super(message);
    }
}
