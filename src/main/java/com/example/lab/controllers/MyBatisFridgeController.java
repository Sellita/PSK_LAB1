package com.example.lab.controllers;

import com.example.lab.mybatis.dao.FridgeMapper;
import com.example.lab.mybatis.dao.FridgeproductMapper;
import com.example.lab.mybatis.dao.ProductMapper;
import com.example.lab.mybatis.model.Fridge;
import com.example.lab.mybatis.model.Product;
import com.example.lab.persistance.FridgeProductDAO;
import com.example.lab.persistance.FridgesDAO;
import com.example.lab.persistance.ProductsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class MyBatisFridgeController {

    @Inject
    private FridgeMapper fridgeMapper;
    @Inject
    private FridgeproductMapper fridgeProductMapper;
    @Inject
    private ProductMapper productsMapper;


    @Getter @Setter
    private Fridge fridge;

    @Getter
    private List<Product> allProducts;

    @Getter
    @Setter
    private int productIdToChange;
    @Getter
    @Setter
    private int quantityToChange;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer fridgeId = Integer.parseInt(requestParameters.get("fridgeId"));
        this.fridge = fridgeMapper.selectByPrimaryKey(fridgeId);

        loadAllProducts();
    }

    private void loadAllProducts(){
        this.allProducts = productsMapper.selectAll();
    }

    @Transactional
    public void updateQuantity(){
//        var productsInFridge = fridge.getFridgeProducts();
//
//
//        var product = productsInFridge.stream().filter(fp -> fp.getProduct().getId() == productIdToChange).findFirst();
//
//        if (product.isPresent()) {
//            var resultQuantity = product.get().getQuantity() + quantityToChange;
//            if(resultQuantity <= 0) {
//                var productToRemove = product.get();
//                fridgeProductDAO.remove(productToRemove);
//                productsInFridge.removeIf(fp -> fp.getProduct().getId() == productIdToChange);
//            }
//            else {
//                product.get().setQuantity(resultQuantity);
//            }
//        } else {
//            if(quantityToChange <= 0) {
//                return;
//            }
//
//            Product newProduct = productsDAO.findOne(productIdToChange);
//            var productToAdd = new FridgeProduct();
//            productToAdd.setProduct(newProduct);
//            productToAdd.setQuantity(quantityToChange);
//            productToAdd.setFridge(fridge);
//            fridgeProductDAO.persist(productToAdd);
//            productsInFridge.add(productToAdd);
//        }
//
//        fridge.setFridgeProducts(productsInFridge);
//
//        fridgesDAO.persist(fridge);
    }

}
