package com.example.lab.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FRIDGEPRODUCT")
@NamedQueries({
        @NamedQuery(name = "FridgeProduct.FindAll", query = "select t from FridgeProduct as t")
})
@Getter @Setter
public class FridgeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FRIDGE_ID")
    public Fridge fridge;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    public Product product;

    private Integer quantity;
}
