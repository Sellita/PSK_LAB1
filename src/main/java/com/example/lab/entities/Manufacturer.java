package com.example.lab.entities;

import jakarta.validation.constraints.Size;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "MANUFACTURER")
@Getter @Setter
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    public Set<Fridge> fridges;
}
