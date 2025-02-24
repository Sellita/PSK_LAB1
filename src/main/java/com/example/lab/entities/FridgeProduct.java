package com.example.lab.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FRIDGEPRODUCT")
@Getter @Setter
public class FridgeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToOne
    @JoinTable(name = "PRODUCT")
    public Product product;

    private Integer quantity;
}
