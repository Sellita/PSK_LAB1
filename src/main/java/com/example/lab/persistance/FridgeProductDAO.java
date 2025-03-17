package com.example.lab.persistance;

import com.example.lab.entities.Fridge;
import com.example.lab.entities.FridgeProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class FridgeProductDAO {
    @Inject
    private EntityManager em;

    public List<Fridge> loadAll() {
        return em.createNamedQuery("FridgeProduct.FindAll", Fridge.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(FridgeProduct fridgeProduct){
        this.em.persist(fridgeProduct);
    }

    public void remove(FridgeProduct fridgeProduct){
        this.em.remove(fridgeProduct);
    }

    public FridgeProduct findOne(Integer id) {
        return em.find(FridgeProduct.class, id);
    }
}
