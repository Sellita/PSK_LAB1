package com.example.lab.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PRODUCTTYPE")
@Getter
@Setter
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "PRODUCTTYPE_PRODUCT",
            joinColumns = @JoinColumn(name = "PRODUCTTYPE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private Set<Product> products;
}
