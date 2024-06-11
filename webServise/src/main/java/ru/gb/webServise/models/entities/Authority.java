package ru.gb.webServise.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private User user;

}
