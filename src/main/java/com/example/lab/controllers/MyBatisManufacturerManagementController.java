package com.example.lab.controllers;

import com.example.lab.mybatis.dao.ManufacturerMapper;
import com.example.lab.mybatis.model.Manufacturer;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MyBatisManufacturerManagementController {
    @Inject
    private ManufacturerMapper manufacturerMapper;

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
        this.manufacturerMapper.insert(manufacturerToCreate);
    }

    private void loadAllManufacturers(){
        this.allManufacturers = manufacturerMapper.selectAll();
    }
}