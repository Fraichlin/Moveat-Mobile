/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author acer
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddAppointment = new Button("Take Appointment");
        Button btnListAppointments = new Button("List Appointments");
        Button btnPay = new Button("Pay");
        
        
        
        btnAddAppointment.addActionListener(e -> new AddAppointmentForm(current).show());
        btnListAppointments.addActionListener(e -> new ListAppointmentsForm(current).show());
        btnPay.addActionListener(e -> new PaymentForm(current).show());
        
        addAll(btnAddAppointment, btnListAppointments,btnPay);

    }

}