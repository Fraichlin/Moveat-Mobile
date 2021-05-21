/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceProgramme;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Programme;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ProgrammeForm extends Form {

    ProgrammeForm(Form previous) {
       setTitle("Add Programme");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.show());
    
        TextField tName = new TextField("", "Name");
        TextField tType = new TextField("", "Type");
        TextField tDescription = new TextField("","Description");
        TextField tImage = new TextField("","Image");
        TextField tBreakfast = new TextField("","Breakfast");
        TextField tLunch = new TextField("","Lunch");
       /* ComboBox cb = new ComboBox();
        cb.addItem("Homme");
        cb.addItem("Femme");*/
        
        TextField tDinner = new TextField("","Dinner");
      //  TextField tPoids = new TextField("","Poids*",5,TextField.NUMERIC);
        
        addAll(tName,tType,tDescription,tBreakfast,tLunch,tDinner,tImage);
        Button btnRegister = new Button("Enregistrer");
         btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(tName.getText().equals("")
                        ||tType.getText().equals("")||tDescription.getText().equals("")||
                        tBreakfast.getText().equals("")||tLunch.getText().equals("")||
                       tDinner.getText().equals(""))
                    Dialog.show("Alert","veuillez remplir tous les champs avec un *",new Command("ok"));
                else{
                    try{
                        Programme m = new Programme();
                        m.setName(tName.getText());
                        m.setType(tType.getText());
                        m.setDescription(tDescription.getText());
                        m.setImage(tImage.getText());
                        m.setBreackfast(tBreakfast.getText());
                       // m.setLunch(cb.getSelectCommandText());
                       m.setLunch(tLunch.getText());
                        m.setDinner(tDinner.getText());
                       // m.setPoids(tPoids.getText());
                       // m.setPhoto(tImage.getText());
                        if(new ServiceProgramme().addTask(m))
                            Dialog.show("Success", "Inscription réussie", new Command("OK"));
                        else
                            Dialog.show("Error", "Echec de l'inscription", new Command("OK"));
                    }catch(Exception e){
                        Dialog.show("Error", "erreur inconnue !",new Command("OK"));
                    }
                }
                
            }
            
        });
        add(btnRegister);
//        Button btnImage = new Button("Choisir photo profil");
//        btnImage.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                
//            }
//            
//        });
    }
    

    ProgrammeForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
