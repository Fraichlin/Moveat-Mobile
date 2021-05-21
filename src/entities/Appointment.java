/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author acer
 */
public class Appointment {
    
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String date;
    
    private String message;

    public Appointment() {
    }

    public Appointment(String nom, String prenom, String email, String tel, String date, String message) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.date = date;
        
        this.message = message;
    }

    public Appointment(int id, String nom, String prenom, String email, String tel, String date, String message) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.date = date;
      
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getDate() {
        return date;
    }
    public String getEvery() {
        return ("id ="+id +"\n"+"nom ="+ nom +"\n"+"prenom ="+prenom+"\n"+"email ="+email+"\n"+"tel ="+tel+"\n"+"date ="+date+"\n");
    }
    

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setDate(String date) {
        this.date = date;
    }

    

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Appointment{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + ", date=" + date + ", message=" + message + '}';
    }

   
    
}
