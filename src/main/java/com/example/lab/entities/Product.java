package com.example.lab.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@NamedQueries({
        @NamedQuery(name = "Products.FindAll", query = "select t from Product as t")
})
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "product")
    private Set<FridgeProduct> fridgeProduct;

    @ManyToMany
    @JoinTable(
            name = "PRODUCTTYPE_PRODUCT",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCTTYPE_ID")
    )
    private Set<ProductType> productTypes;
}
