package com.mostafa.fci.retrofitlibrary;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FCI on 2018-06-25.
 */

public class Flower {

    @SerializedName("productId")
    private int id;
    private String name;
    private String category;
    private String instructions;
    private String price;
    private String photo;



    public Flower(int id, String name) {

        this.id = id;
        this.name = name;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
