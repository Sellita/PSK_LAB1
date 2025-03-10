package com.example.lab.persistance;

import com.example.lab.entities.Manufacturer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ManufacturersDAO {
    @Inject
    private EntityManager em;

    public List<Manufacturer> loadAll() {
        return em.createNamedQuery("Manufacturers.FindAll", Manufacturer.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Manufacturer manufacturer){
        this.em.persist(manufacturer);
    }

    public Manufacturer findOne(Integer id) {
        return em.find(Manufacturer.class, id);
    }
}
