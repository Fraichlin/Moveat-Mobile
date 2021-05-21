/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Appointment;
import services.ServiceAppointment;

/**
 *
 * @author acer
 */
public class AddAppointmentForm extends Form {
    public AddAppointmentForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Make a new Appointment");
        setLayout(BoxLayout.y());
        
        TextField tfnom = new TextField("","Nom");
        TextField tfprenom = new TextField("","Prenom");
        TextField tfemail = new TextField("","Email");
        TextField tftel = new TextField("","Tel");
        TextField tfdate = new TextField("","Date");
        TextField tfmessage = new TextField("","Message");
        
        
        
        
        
        Button btnValider = new Button("Make Appointment");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(tfprenom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
        Appointment a = new Appointment(tfnom.getText(),tfprenom.getText(), tfemail.getText(),tftel.getText(),tfdate.getText(),tfmessage.getText());
                        if( ServiceAppointment.getInstance().addAppointment(a))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfnom,tfprenom,tfemail,tftel,tfdate,tfmessage,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
}
