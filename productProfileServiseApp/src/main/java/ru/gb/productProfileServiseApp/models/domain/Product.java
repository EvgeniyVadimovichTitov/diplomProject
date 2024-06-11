package ru.gb.productProfileServiseApp.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "product_data")
public class Product {
//Данный класс отвечает за slave таблицу
// (продуктовый профиль клиента) и содержит унификацию продукта,
// то есть для добавления продукта кредит вносим его в
// enum допиливаем логику оформления, например сумма кредита падает
// на мастер карту, а баланс кредита означает
// сумму долга и уменьшается с внесением денег

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID id;

    //Для добавления нового продукта необходимо внести его в enum
    @Column(name = "NAME", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ProductName name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid_client")
    @JsonIgnore
    private ClientData client;

    //номера карт берутся из свободных в таблице card,
    // подразумевается, что они изготовлены физически,
    // поэтому номера готовых карт вносятся и ведутся отдельно
    // для кредита, например, это будет номер договора
    @Column(name = "number", unique = true)
    private String number;

    //выбран класс для хранения денег BigDecimal.
    //Преимущества:
    //1.Точность и значность известна
    //2.Арифметики и сравнение включены
    //3.Поддерживается в JDK, JDBC и т.д.
    //4.Неплохая производительность
    @Column(name = "balance")
    private BigDecimal balance;

    //признак главной карты, на нее автоматически приходят переводы
    @Column(name = "main_card", nullable = false)
    private boolean isMainCard = false;
}
