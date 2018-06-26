package com.mostafa.fci.retrofitlibrary;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


@Root(name = "product" , strict = false)
public class XMLFlower {

    @Element(name = "productId")
    private int id;
    @Element(name = "name")
    private String name;
    @Element(name = "category")
    private String category;
    @Element(name = "instructions")
    private String instructions;
    @Element(name = "price")
    private String price;
    @Element(name = "photo")
    private String photo;

    public XMLFlower() {
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