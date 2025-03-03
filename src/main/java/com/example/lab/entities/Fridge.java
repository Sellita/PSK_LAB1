package com.example.lab.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "FRIDGE")
@NamedQueries({
        @NamedQuery(name = "Fridge.FindAll", query = "select t from Fridge as t")
})
@Getter @Setter
public class Fridge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "fridge")
    public Set<FridgeProduct> fridgeProducts;

    @ManyToOne
    @JoinColumn(name = "MANUFACTURER_ID")
    private Manufacturer manufacturer;
}
