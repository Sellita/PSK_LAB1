package com.example.lab.controllers;

import com.example.lab.entities.Fridge;
import com.example.lab.entities.Manufacturer;
import com.example.lab.persistance.FridgesDAO;
import com.example.lab.persistance.ManufacturersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ManufacturerManagementController {
    @Inject
    private ManufacturersDAO manufacturersDAO;

    @Getter
    @Setter
    private Manufacturer manufacturerToCreate = new Manufacturer();

    @Getter
    private List<Manufacturer> allManufacturers;

    @PostConstruct
    public void init(){
        loadAllManufacturers();
    }

    @Transactional
    public void createManufacturer(){
        this.manufacturersDAO.persist(manufacturerToCreate);
    }

    private void loadAllManufacturers(){
        this.allManufacturers = manufacturersDAO.loadAll();
    }
}