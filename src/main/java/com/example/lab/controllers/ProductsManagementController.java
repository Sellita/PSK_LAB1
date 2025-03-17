package com.example.lab.controllers;

import com.example.lab.entities.Product;
import com.example.lab.entities.ProductType;
import com.example.lab.persistance.ProductsDAO;
import com.example.lab.persistance.ProductsTypesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Model
public class ProductsManagementController {
    @Inject
    private ProductsDAO productsDAO;
    @Inject
    private ProductsTypesDAO productsTypesDAO;

    @Getter
    @Setter
    private Product productToCreate = new Product();

    @Getter
    private List<Product> allProducts;

    @Getter
    private List<ProductType> productTypes;

    @Getter
    @Setter
    private List<Integer> selectedProductTypes;

    @PostConstruct
    public void init(){
        loadAllProducts();
        loadAllProductsTypes();
    }

    @Transactional
    public void createProduct(){
        Set<ProductType> productTypeSet = productTypes.stream()
                .filter(m -> selectedProductTypes.contains(m.getId()))
                .collect(Collectors.toSet());

        productToCreate.setProductTypes(productTypeSet);


        this.productsDAO.persist(productToCreate);
    }

    private void loadAllProducts(){
        this.allProducts = productsDAO.loadAll();
    }
    private void loadAllProductsTypes(){
        this.productTypes = productsTypesDAO.loadAll();
    }
}