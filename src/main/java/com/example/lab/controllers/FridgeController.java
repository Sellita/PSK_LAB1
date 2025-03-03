package com.example.lab.controllers;

import com.example.lab.entities.Fridge;
import com.example.lab.persistance.FridgesDAO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.transaction.Transactional;
import javax.inject.Inject;

@Model
public class FridgeController {
    @Inject
    private FridgesDAO fridgesDAO;

    @Getter
    @Setter
    private Fridge fridgeToCreate = new Fridge();

    @Getter
    private List<Fridge> allFridges;

    @PostConstruct
    public void init(){
        loadAllFridges();
    }

    @Transactional
    public void createFridge(){
        this.fridgesDAO.persist(fridgeToCreate);
    }

    private void loadAllFridges(){
        this.allFridges = fridgesDAO.loadAll();
    }
}