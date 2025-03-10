package com.example.lab.controllers;

import com.example.lab.entities.Fridge;
import com.example.lab.persistance.FridgesDAO;
import lombok.Getter;
import lombok.Setter;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class FridgeController {

    @Inject
    private FridgesDAO fridgesDAO;

    @Getter @Setter
    private Fridge fridge;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teamId = Integer.parseInt(requestParameters.get("fridgeId"));
        this.fridge = fridgesDAO.findOne(teamId);
    }


}
