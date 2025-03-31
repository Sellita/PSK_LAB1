package com.example.lab.controllers;

import com.example.lab.entities.Fridge;
import com.example.lab.entities.FridgeProduct;
import com.example.lab.entities.Product;
import com.example.lab.persistance.FridgeProductDAO;
import com.example.lab.persistance.FridgesDAO;
import com.example.lab.persistance.ProductsDAO;
import com.example.lab.services.SubService;
import com.example.lab.services.SubServiceSession;
import lombok.Getter;
import lombok.Setter;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Model
public class FridgeController {

    @Inject
    private FridgesDAO fridgesDAO;
    @Inject
    private ProductsDAO productsDAO;
    @Inject
    private FridgeProductDAO fridgeProductDAO;
    @Inject
    private SubService subService;
    @Inject
    private SubServiceSession subServiceSession;

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
        this.fridge = fridgesDAO.findOne(fridgeId);

        if(subService.getId() != 0){
            var appProduct = new FridgeProduct();
            var product = productsDAO.findOne(subService.getId());
            System.out.println("Product name: " + product.getName());

            appProduct.setProduct(product);
            appProduct.setQuantity(subService.getQuantity());
            fridge.fridgeProducts.add(appProduct);
        }
        if(subServiceSession.getId() != 0){
            var appProduct = new FridgeProduct();
            var product = productsDAO.findOne(subServiceSession.getId());
            System.out.println("Product name: " + product.getName());

            appProduct.setProduct(product);
            appProduct.setQuantity(subServiceSession.getQuantity());
            fridge.fridgeProducts.add(appProduct);
        }


        loadAllProducts();
    }

    private void loadAllProducts(){
        this.allProducts = productsDAO.loadAll();
    }

    @Transactional
    public void updateQuantity(){
        var productsInFridge = fridge.getFridgeProducts();


        var product = productsInFridge.stream().filter(fp -> fp.getProduct().getId() == productIdToChange).findFirst();

        if (product.isPresent()) {
            var resultQuantity = product.get().getQuantity() + quantityToChange;
            if(resultQuantity <= 0) {
                var productToRemove = product.get();
                fridgeProductDAO.remove(productToRemove);
                productsInFridge.removeIf(fp -> fp.getProduct().getId() == productIdToChange);
            }
            else {
                product.get().setQuantity(resultQuantity);
            }
        } else {
            if(quantityToChange <= 0) {
                return;
            }

            Product newProduct = productsDAO.findOne(productIdToChange);
            var productToAdd = new FridgeProduct();
            productToAdd.setProduct(newProduct);
            productToAdd.setQuantity(quantityToChange);
            productToAdd.setFridge(fridge);
            fridgeProductDAO.persist(productToAdd);
            productsInFridge.add(productToAdd);
        }

        fridge.setFridgeProducts(productsInFridge);

        fridgesDAO.persist(fridge);
    }

    public void reduceQuantity(){


        new Thread(() -> {
            try {
                subService.SubProduct(productIdToChange, quantityToChange);
            } catch (InterruptedException e) {
                return;
            }
        }).start();

        System.out.println("End of reduceQuantity method");

    }

    public void saveSession() {

        subServiceSession.saveProduct(productIdToChange, quantityToChange);


        System.out.println("End of reduceQuantity method");

    }
}
