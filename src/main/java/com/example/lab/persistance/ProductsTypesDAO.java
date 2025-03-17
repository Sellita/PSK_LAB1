package com.example.lab.persistance;

import com.example.lab.entities.Manufacturer;
import com.example.lab.entities.ProductType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProductsTypesDAO {
    @Inject
    private EntityManager em;

    public List<ProductType> loadAll() {
        return em.createNamedQuery("ProductsTypes.FindAll", ProductType.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(ProductType productType){
        this.em.persist(productType);
    }

    public ProductType findOne(Integer id) {
        return em.find(ProductType.class, id);
    }
}
