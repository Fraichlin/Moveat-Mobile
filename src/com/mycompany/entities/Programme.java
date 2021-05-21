/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ASUS
 */
public class Programme {
    private int id;
    private String name;
    private String type;
    private String description;
    private String image;
    private String breackfast;
    private String lunch;
    private String dinner;

    public Programme() {
    }

    public Programme(int id, String name, String type, String description, String image, String breackfast, String lunch, String dinner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.image = image;
        this.breackfast = breackfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public Programme(String name, String type, String description, String image, String breackfast, String lunch, String dinner) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.image = image;
        this.breackfast = breackfast;
        this.lunch = lunch;
        this.dinner = dinner;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBreackfast() {
        return breackfast;
    }

    public void setBreackfast(String breackfast) {
        this.breackfast = breackfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    @Override
    public String toString() {
        return "Programme{" + "name=" + name + ", type=" + type + ", description=" + description + ", image=" + image + ", breackfast=" + breackfast + ", lunch=" + lunch + ", dinner=" + dinner + '}';
    }
    
    
    
}
