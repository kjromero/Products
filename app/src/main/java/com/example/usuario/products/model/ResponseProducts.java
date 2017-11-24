package com.example.usuario.products.model;


import com.google.gson.annotations.SerializedName;

public class ResponseProducts {

    @SerializedName("GetProductListResult")
    private ObjectProducts objectProducts;

    public ObjectProducts getObjectProducts() {
        return objectProducts;
    }

    public void setObjectProducts(ObjectProducts objectProducts) {
        this.objectProducts = objectProducts;
    }
}
