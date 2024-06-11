package ru.gb.cardServise.models.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.gb.cardServise.models.exceptions.ExceptionFileIsEmpty;

import java.io.File;
import java.io.IOException;
import java.util.List;
//интерфейс работы с файлами
public interface IFileOperation {
    //запись полученного файла в хранилище
    File fileWrite(MultipartFile file) throws ExceptionFileIsEmpty,IOException;
    //чтение файла из локального хранилища
    List<String>  fileRead(File fileInput) throws IOException;
}
