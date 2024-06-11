package ru.gb.cardServise.models.exceptions;
//ошибка валидации номера карт
public class ExceptionCardNumberIncorrect extends RuntimeException{
    public ExceptionCardNumberIncorrect(String message) {
        super(message);
    }
}
