package com.example.lab.persistance;

import com.example.lab.entities.Manufacturer;
import com.example.lab.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProductsDAO {
    @Inject
    private EntityManager em;

    public List<Product> loadAll() {
        return em.createNamedQuery("Products.FindAll", Product.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Product product){
        this.em.persist(product);
    }

    public Product findOne(Integer id) {
        return em.find(Product.class, id);
    }
}
