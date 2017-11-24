package com.example.usuario.products.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Usuario on 23/11/2017.
 */

public class ObjectProducts {

    @SerializedName("Products")
    private Product[] products;


    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
