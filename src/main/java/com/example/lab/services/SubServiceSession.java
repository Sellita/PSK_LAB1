package com.example.lab.services;

import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class SubServiceSession implements Serializable {

    @Getter
    Integer id = 0;

    @Getter
    Integer quantity = 0;

    public void saveProduct(int productIdToChange, int quantityToChange) {
        //var em = emf.createEntityManager(SynchronizationType.SYNCHRONIZED);
        id = productIdToChange;
        quantity = quantityToChange;


    }

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed.");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die.");
    }
}
