package ru.gb.cardServise.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Дл;
import ru.gb.cardServise.models.domain.CardData;
import ru.gb.cardServise.models.entities.ResponseEntityResultImport;
import ru.gb.cardServise.models.exceptions.ExceptionCardNumberIncorrect;
import ru.gb.cardServise.models.exceptions.ExceptionFileIsEmpty;
import ru.gb.cardServise.models.exceptions.ExceptionIsUsingIncorrect;
import ru.gb.cardServise.models.interfaces.IServiseFileParsing;
import ru.gb.cardServise.models.interfaces.IServiseRep;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/card")
public class ControllerCardDate {

    private final IServiseRep serviseRep;
    private final IServiseFileParsing serviseFileParsing;

    @GetMapping("/getAll")
    public ResponseEntity<List<CardData>> getAll() {
        return new ResponseEntity<>(serviseRep.findAll(), HttpStatus.OK);
    }

    @PutMapping("/setUsingCard")
    public ResponseEntity<CardData> setIsingCard(@RequestBody CardData cardData) {
        try {
            return new ResponseEntity<>(serviseRep.updateCardData(cardData), HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getFreeCard")
    public ResponseEntity<CardData> getFreeCard() {
        try {
            return new ResponseEntity<>(serviseRep.findIsNotUsedCard(), HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCardByNumber/{cardNumber}")
    public ResponseEntity<CardData> getCardByNumber(@PathVariable String cardData) {
        try {
            return new ResponseEntity<CardData>(serviseRep.findByCardNumber(cardData), HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping("/importFileCsv")
    public ResponseEntity<ResponseEntityResultImport> importFileCsv(@RequestParam("file") MultipartFile file) {
        System.out.println("1223");
        try {
            serviseRep.addAll(serviseFileParsing.fileWorking(file));
            return ResponseEntity.ok(new ResponseEntityResultImport("Import is correct", 201));
        } catch (IOException  exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(new ResponseEntityResultImport("Error import", 501),HttpStatus.BAD_REQUEST);
        } catch (ExceptionCardNumberIncorrect | ExceptionFileIsEmpty |
                 ExceptionIsUsingIncorrect exception){
            return ResponseEntity.ok(new ResponseEntityResultImport(exception.getMessage(), 401));
        }catch (Exception exception){

            return ResponseEntity.ok(new ResponseEntityResultImport(exception.getMessage(), 402));
        }
    }
}
