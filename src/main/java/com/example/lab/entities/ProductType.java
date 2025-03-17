package com.example.lab.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PRODUCTTYPE")
@NamedQueries({
        @NamedQuery(name = "ProductsTypes.FindAll", query = "select t from ProductType as t")
})
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

    @ManyToMany(mappedBy = "productTypes")
    private Set<Product> products;
}
