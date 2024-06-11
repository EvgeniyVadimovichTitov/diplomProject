package ru.gb.cardServise.models.servises;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.gb.cardServise.models.exceptions.ExceptionFileIsEmpty;
import ru.gb.cardServise.models.interfaces.IFileOperation;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//
@Service
public class FileOperation implements IFileOperation {

    @Override
    public File fileWrite(MultipartFile file) throws ExceptionFileIsEmpty, IOException {
        //проверка на пустоту
        if (file.isEmpty()) {
            throw new ExceptionFileIsEmpty("File is empty");
        }
        //считывание входного потока
        InputStream initialStream = file.getInputStream();
        byte[] buffer = new byte[initialStream.available()];
        initialStream.read(buffer);
        //создание файла для записи
        File targetFile = new File("src/main/resources/targetFile" + LocalDateTime.now().hashCode() +".tmp");
        //запись в файл
        try (OutputStream outStream = new FileOutputStream(targetFile)) {
            outStream.write(buffer);
        }

        return targetFile;
    }

    @Override
    public List<String> fileRead(File fileInput) throws IOException {
        //считывание файла в массив строк
        List<String> lines = new ArrayList<>();
        try (FileReader fr = new FileReader(fileInput)) {

            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            return lines;
        }
    }
}
