/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Yassine Harbaoui
 */
public class Programme {
    private int id;
    private String Nom,description,image;
    
    
      public Programme() {
    }


    public Programme(int id, String Nom, String description, String image) {
        this.id = id;
        this.Nom = Nom;
        this.description = description;
        this.image = image;
    }

  
    public Programme(String Nom, String description, String image) {
        this.Nom = Nom;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
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

    @Override
    public String toString() {
        return "Programme{" + "id=" + id + ", Nom=" + Nom + ", description=" + description + ", image=" + image + '}';
    }
    
}
