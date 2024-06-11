package ru.gb.cardServise.models.exceptions;
//ошибка валидации при парсинге файла признак использования
public class ExceptionIsUsingIncorrect extends RuntimeException{
    public ExceptionIsUsingIncorrect(String message) {
        super(message);
    }
}
