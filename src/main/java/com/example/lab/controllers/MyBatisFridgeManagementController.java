package com.example.lab.controllers;

import com.example.lab.mybatis.dao.FridgeMapper;
import com.example.lab.mybatis.dao.ManufacturerMapper;
import com.example.lab.mybatis.model.Fridge;
import com.example.lab.mybatis.model.Manufacturer;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MyBatisFridgeManagementController {
    @Inject
    private FridgeMapper fridgeMapper;
    @Inject
    private ManufacturerMapper manufacturerMapper;

    @Getter
    @Setter
    private Fridge fridgeToCreate = new Fridge();

    @Getter
    @Setter
    private int manufacturerId;

    @Getter
    private List<Fridge> allFridges;

    @Getter
    private List<Manufacturer> manufacturers;


    @PostConstruct
    public void init(){

        loadAllFridges();
        loadAllManufacturers();
    }



    @Transactional
    public void createFridge(){
        fridgeToCreate.setManufacturerId(manufacturerId);
        this.fridgeMapper.insert(fridgeToCreate);
    }

    private void loadAllFridges(){
        this.allFridges = fridgeMapper.selectAll();
    }

    private void loadAllManufacturers(){
        this.manufacturers = manufacturerMapper.selectAll();
    }
}