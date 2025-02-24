package com.example.lab.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "FRIDGE")
@Getter @Setter
public class Fridge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name="FRIDGEPRODUCT")
    public Set<FridgeProduct> fridgeProducts;


}
