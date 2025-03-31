package com.example.lab.services;

import com.example.lab.entities.Fridge;
import com.example.lab.entities.FridgeProduct;
import com.example.lab.persistance.FridgeProductDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.SynchronizationType;
import java.io.Serializable;

@ApplicationScoped
public class SubService implements Serializable {

    @Getter
    Integer id = 0;

    @Getter
    Integer quantity = 0;

    public void SubProduct(int productIdToChange, int quantityToChange) throws InterruptedException {
        //var em = emf.createEntityManager(SynchronizationType.SYNCHRONIZED);
        id = productIdToChange;
        quantity = quantityToChange;
        System.out.println("Product to reduce updated: " + id + " Quantity to reduce: " + quantity);


        while (quantity > 0) {
           quantity --;
            System.out.println("Quantity left: " + quantity);
            Thread.sleep(10000);
        }


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
//
//
//var product = fridgeProductDAO.findOne(productIdToChange);
//
//        while (quantityToChange != 0) {
//        product.setQuantity(product.getQuantity() - 1);
//quantityToChange--;
//
//        System.out.println("Quantity left: " + product.getQuantity());
//
//        if (product.getQuantity() == 0) {
//        return;
//        } else {
//        System.out.println("Persisting product.");
////em.persist(product);
//            }
//
//                    Thread.sleep(10000);
//        }