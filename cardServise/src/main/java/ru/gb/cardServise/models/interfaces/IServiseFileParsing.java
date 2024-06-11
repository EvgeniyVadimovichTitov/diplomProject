package ru.gb.cardServise.models.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.gb.cardServise.models.domain.CardData;
import ru.gb.cardServise.models.exceptions.ExceptionFileIsEmpty;

import java.io.IOException;
import java.util.List;
//интерфейс парсинга файла
public interface IServiseFileParsing {
    List<CardData> fileWorking(MultipartFile file) throws ExceptionFileIsEmpty, IOException;
}
