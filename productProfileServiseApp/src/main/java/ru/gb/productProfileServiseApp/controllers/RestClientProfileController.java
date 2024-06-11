package ru.gb.productProfileServiseApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import ru.gb.productProfileServiseApp.models.domain.ClientData;
import ru.gb.productProfileServiseApp.models.interfaces.IServiceClientData;


import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class RestClientProfileController {

    private final IServiceClientData service;

    @GetMapping("getProfileByUuid/{uuid}")
    public ResponseEntity <ClientData> findById(@PathVariable UUID uuid){
        try {
            return new ResponseEntity<>(service.findByUUid(uuid), HttpStatus.OK);
        }catch (NoSuchElementException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("getProfileByPhoneNumber/{phoneNumber}")
    public ResponseEntity <ClientData> findByPhoneNumber(@PathVariable String phoneNumber){
        try {
            return new ResponseEntity <ClientData>(service.findByPhoneNumber(phoneNumber), HttpStatus.OK);
        }catch (NoSuchElementException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("createProfile")
    public ResponseEntity<ClientData> createClientData(@RequestBody ClientData clientData){
        return new ResponseEntity<>(service.addClientData(clientData), HttpStatus.CREATED);
    }

    @PutMapping("updateProfile")
    public ResponseEntity<ClientData> updateClientData(@RequestBody ClientData clientData){
        try {
            return new ResponseEntity<>(service.updateClientData(clientData), HttpStatus.OK);

        }catch (NoSuchElementException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

}
