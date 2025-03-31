package com.example.lab.controllers;

import com.example.lab.mybatis.dao.ProducttypeMapper;
import com.example.lab.mybatis.model.Producttype;
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
    private ProducttypeMapper producttypeMapper;

    @Getter
    @Setter
    private Producttype productTypeToCreate = new Producttype();

    @Getter
    private List<Producttype> allProductsTypes;

    @PostConstruct
    public void init(){
        loadAllManufacturers();
    }

    @Transactional
    public void createProductType(){
        this.producttypeMapper.insert(productTypeToCreate);
    }

    private void loadAllManufacturers(){
        this.allProductsTypes = producttypeMapper.selectAll();
    }
}