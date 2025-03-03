package com.example.lab.persistance;

import com.example.lab.entities.Fridge;

import javax.persistence.EntityManager;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class FridgesDAO {
    @Inject
    private EntityManager em;

    public List<Fridge> loadAll() {
        return em.createNamedQuery("Fridge.FindAll", Fridge.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Fridge fridge){
        this.em.persist(fridge);
    }

    public Fridge findOne(Integer id) {
        return em.find(Fridge.class, id);
    }
}
