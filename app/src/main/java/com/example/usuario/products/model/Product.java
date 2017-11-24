package com.example.usuario.products.model;

import com.google.gson.annotations.SerializedName;



public class Product {

    @SerializedName("Active")
    private boolean Active;

    @SerializedName("Description")
    private String Description;

    @SerializedName("Id")
    private int Id;


    @SerializedName("Name")
    private String Name;

    @SerializedName("Quantity")
    private int Quantity;

    @SerializedName("Size")
    private String Size;


    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }
}
