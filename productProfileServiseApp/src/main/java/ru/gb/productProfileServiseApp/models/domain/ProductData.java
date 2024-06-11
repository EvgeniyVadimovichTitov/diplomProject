package ru.gb.productProfileServiseApp.models.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "product_data")
public class ProductData {


    @Id
    private UUID id;

    //Для добавления нового продукта необходимо внести его в enum
    @Column(name = "NAME", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ProductName name;

    @Column(name = "uuid_client")
    private UUID client;

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