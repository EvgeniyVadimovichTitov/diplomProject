package ru.gb.cardServise.models.servises;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.gb.cardServise.models.domain.CardData;
import ru.gb.cardServise.models.exceptions.ExceptionCardNumberIncorrect;
import ru.gb.cardServise.models.exceptions.ExceptionFileIsEmpty;
import ru.gb.cardServise.models.exceptions.ExceptionIsUsingIncorrect;
import ru.gb.cardServise.models.interfaces.IFileOperation;
import ru.gb.cardServise.models.interfaces.IMapper;
import ru.gb.cardServise.models.interfaces.IServiseFileParsing;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiseParsingCsv implements IServiseFileParsing {

    private final IMapper mapper;
    private final IFileOperation fileOperation;
    //обработка файла
    @Override
    public List<CardData> fileWorking(MultipartFile file) throws ExceptionFileIsEmpty, IOException, ExceptionIsUsingIncorrect, ExceptionCardNumberIncorrect {
        return fileOperation.fileRead(fileOperation.fileWrite(file)).stream().map(mapper::map).toList();
    }
}

