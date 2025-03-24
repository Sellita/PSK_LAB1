package com.example.lab.controllers;

import com.example.lab.entities.ProductType;
import com.example.lab.persistance.ProductsTypesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MyBatisProductsTypeManagementController {
    @Inject
    private ProductsTypesDAO productsTypesDAO;

    @Getter
    @Setter
    private ProductType productTypeToCreate = new ProductType();

    @Getter
    private List<ProductType> allProductsTypes;

    @PostConstruct
    public void init(){
        loadAllManufacturers();
    }

    @Transactional
    public void createProductType(){
        this.productsTypesDAO.persist(productTypeToCreate);
    }

    private void loadAllManufacturers(){
        this.allProductsTypes = productsTypesDAO.loadAll();
    }
}