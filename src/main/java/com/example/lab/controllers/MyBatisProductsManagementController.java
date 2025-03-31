package com.example.lab.controllers;

import com.example.lab.mybatis.dao.ProductMapper;
import com.example.lab.mybatis.dao.ProducttypeMapper;
import com.example.lab.mybatis.dao.ProducttypeProductMapper;
import com.example.lab.mybatis.model.Product;
import com.example.lab.mybatis.model.Producttype;
import com.example.lab.mybatis.model.ProducttypeProduct;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Model
public class MyBatisProductsManagementController {
    @Inject
    private ProductMapper productMapper;
    @Inject
    private ProducttypeMapper producttypeMapper;
    @Inject
    private ProducttypeProductMapper producttypeProductMapper;

    @Getter
    @Setter
    private Product productToCreate = new Product();

    @Getter
    private List<Product> allProducts;

    @Getter
    private List<Producttype> productTypes;

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
        Set<Producttype> productTypeSet = productTypes.stream()
                .filter(m -> selectedProductTypes.contains(m.getId()))
                .collect(Collectors.toSet());

        this.productMapper.insert(productToCreate);

        for (Producttype productType : productTypeSet) {
            ProducttypeProduct productTypeProduct = new ProducttypeProduct();
            productTypeProduct.setProductId(productToCreate.getId());
            productTypeProduct.setProducttypeId(productType.getId());
            producttypeProductMapper.insert(productTypeProduct);
        }
    }

    private void loadAllProducts(){
        this.allProducts = productMapper.selectAll();
    }

    private void loadAllProductsTypes(){
        this.productTypes = producttypeMapper.selectAll();
    }
}