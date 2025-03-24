package com.example.lab.controllers;

import com.example.lab.entities.Product;
import com.example.lab.persistance.ProductsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class MyBatisProductController {

    @Inject
    private ProductsDAO productsDAO;

    @Getter @Setter
    private Product product;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer productId = Integer.parseInt(requestParameters.get("productId"));
        this.product = productsDAO.findOne(productId);
    }


}
