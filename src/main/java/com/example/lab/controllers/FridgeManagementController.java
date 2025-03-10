package com.example.lab.controllers;

import com.example.lab.entities.Fridge;
import com.example.lab.entities.Manufacturer;
import com.example.lab.persistance.FridgesDAO;
import com.example.lab.persistance.ManufacturersDAO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.transaction.Transactional;
import javax.inject.Inject;

@Model
public class FridgeManagementController {
    @Inject
    private FridgesDAO fridgesDAO;
    @Inject
    private ManufacturersDAO manufacturersDAO;

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
        var manufacturer = manufacturers.stream().filter(m -> m.getId() == manufacturerId).findFirst().orElse(null);
        fridgeToCreate.setManufacturer(manufacturer);
        this.fridgesDAO.persist(fridgeToCreate);
    }

    private void loadAllFridges(){
        this.allFridges = fridgesDAO.loadAll();
    }

    private void loadAllManufacturers(){
        this.manufacturers = manufacturersDAO.loadAll();
    }
}