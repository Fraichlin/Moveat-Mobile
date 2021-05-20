/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;


/**
 *
 * @author NGONGANG Loic F
 */
public class User {
    private int idUser;
    private String email;
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String sexe;
    private String tel;
    private String statut;
    private String photo;
    private String type;
    private Date dateInscription;
    private Date dateBlocage;
    private Date dateDeblocage;
    private Date dateModification;

    
    public User() {
    }
    public User(String email, String username, String password, String nom, String prenom, String sexe, String tel, String statut, String photo, String type, Date dateInscription) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.tel = tel;
        this.statut = statut;
        this.photo = photo;
        this.type = type;
        this.dateInscription = dateInscription;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Date getDateBlocage() {
        return dateBlocage;
    }

    public void setDateBlocage(Date dateBlocage) {
        this.dateBlocage = dateBlocage;
    }

    public Date getDateDeblocage() {
        return dateDeblocage;
    }

    public void setDateDeblocage(Date dateDeblocage) {
        this.dateDeblocage = dateDeblocage;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
