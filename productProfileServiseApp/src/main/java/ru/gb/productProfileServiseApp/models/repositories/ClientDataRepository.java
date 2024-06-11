package ru.gb.productProfileServiseApp.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.productProfileServiseApp.models.domain.ClientData;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClientDataRepository extends JpaRepository <ClientData, UUID> {
    //поиск по номеру телефона
    Optional<ClientData> findByPhoneNumber(String phoneNumber);

}
