package ru.gb.productProfileServiseApp.models.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.productProfileServiseApp.models.domain.ClientData;
import ru.gb.productProfileServiseApp.models.interfaces.IServiceClientData;
import ru.gb.productProfileServiseApp.models.repositories.ClientDataRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientDataService implements IServiceClientData {

    private final ClientDataRepository clientDataRepository;

    @Override
    public ClientData addClientData(ClientData clientData) {
        //сохранение и возврат результата
        return clientDataRepository.save(clientData);
    }

    @Override
    public ClientData findByUUid(UUID uuid) throws NoSuchElementException {
        //поиск и возврат результата
        return clientDataRepository.findById(uuid).orElseThrow();
    }

    @Override
    public ClientData findByPhoneNumber(String phoneNumber) throws NoSuchElementException{
        //поиск и возврат результата
        return clientDataRepository.findByPhoneNumber(phoneNumber).orElseThrow();
    }

    @Override
    public ClientData updateClientData(ClientData clientData) throws NoSuchElementException{
        //поиск
        ClientData clientDataFound = findByUUid(clientData.getUuid());
        //обновление информации
        clientDataFound.setName(clientData.getName());
        clientDataFound.setSurname(clientData.getSurname());
        clientDataFound.setPatronymic(clientData.getPatronymic());
        clientDataFound.setPhoneNumber(clientData.getPhoneNumber());
        //сохранение и возврат
        return clientDataRepository.save(clientDataFound);
    }
}
