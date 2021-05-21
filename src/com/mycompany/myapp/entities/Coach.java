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
public class Coach extends User{
    private String specialite;
    private String adresse;
    private String justificatif;
    private Date dateValidation;

    public Coach(String specialite, String adresse, String justificatif, Date dateValidation, String email, String username, String password, String nom, String prenom, String sexe, String tel, String statut, String photo, String type, java.util.Date dateInscription) {
        super(email, username, password, nom, prenom, sexe, tel, statut, photo, type, dateInscription);
        this.specialite = specialite;
        this.adresse = adresse;
        this.justificatif = justificatif;
        this.dateValidation = dateValidation;
    }

    public Coach() {

    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }
    
    
}
