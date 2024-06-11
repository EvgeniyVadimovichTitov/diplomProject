package ru.gb.productProfileServiseApp.models.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//Сущность клиента таблица master
@Entity
@Data
@Table(name = "client_data")
public class ClientData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "telefone_mob")
    private String phoneNumber;
    //ссылка на slave-таблицу продуктов имеющихся у клиента отношение один ко многим
    @OneToMany(fetch = FetchType.EAGER,mappedBy="client")
    @OrderBy("main_card, balance DESC")
    private List<Product> products = new ArrayList<>();


}
