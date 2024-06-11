package ru.gb.productProfileServiseApp.models.interfaces;

import ru.gb.productProfileServiseApp.models.domain.ClientData;

import java.util.NoSuchElementException;
import java.util.UUID;

public interface IServiceClientData {
    ClientData addClientData(ClientData cd);
    ClientData findByUUid(UUID uuid) throws NoSuchElementException;
    ClientData findByPhoneNumber(String phoneNumber) throws NoSuchElementException;
    ClientData updateClientData(ClientData clientData) throws NoSuchElementException;
}
