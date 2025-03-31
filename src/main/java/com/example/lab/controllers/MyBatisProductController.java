package com.example.lab.controllers;

import com.example.lab.mybatis.dao.ProductMapper;
import com.example.lab.mybatis.model.Product;
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
    private ProductMapper productMapper;

    @Getter @Setter
    private Product product;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer productId = Integer.parseInt(requestParameters.get("productId"));
        this.product = productMapper.selectByPrimaryKey(productId);
    }


}
