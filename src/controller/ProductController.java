package controller;

import entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductController {

    //ObservableList<entity.Product> products = new controller.ProductController().loadProducts();
    public ObservableList<Product> loadProducts(){
        ObservableList<Product> observableProducts = FXCollections.observableArrayList();

        observableProducts.add(new Product("Philips One", 1500, 5));
        observableProducts.add(new Product("Samsung", 1400, 100));
        observableProducts.add(new Product("Sony", 1900, 20));
        observableProducts.add(new Product("Telefunken", 1000, 100));
        observableProducts.add(new Product("Vox", 900, 2000));
        return  observableProducts;
    }
}
