/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.security;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.coach.registerCoachView;
import com.mycompany.myapp.gui.member.homeMemberView;
import com.mycompany.myapp.services.security.loginService;

/**
 *
 * @author NGONGANG Loic F
 */
public class loginView extends Form{
Form current;
    public loginView() {
        current = this;
        setTitle("Connexion");
        setLayout(BoxLayout.y());
        add(new Label("Veuillez vous connecter s'il vous plait!!"));
        TextField tEmail = new TextField("", "Email");
        TextField tPassword = new TextField("", "Mot de passe");
        Button btnlogin = new Button("Connexion");
       
        
        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if(new loginService().isUserExists(tEmail.getText(), tPassword.getText()) ){
                   User user = new loginService().searchUser(tEmail.getText(), tPassword.getText()).get(0);
                   if(user.getStatut().equals("nonactived") || user.getStatut().equals("blocked")){
                       if(user.getStatut().equals("nonactived"))
                           Dialog.show("Error", "Votre compte n'est pas encore activé", new Command("OK"));
                       if(user.getStatut().equals("blocked"))
                           Dialog.show("Error", "Votre compte est bloqué", new Command("OK"));
                   }
                   else{
                       
                       if(user.getType().equals("[ROLE_MEMBER]")){
                           Dialog.show("Success", "Connexion réussie! Bienvenue "+user.getUsername(),new Command("OK"));
                           
                       }
                       if(user.getType().equals("[ROLE_COACH]")){
                            Dialog.show("Success", "Connexion réussie! Bienvenue "+user.getUsername(),new Command("OK"));
                       }
                   }
                       
               }
               else
                   Dialog.show("Error", "Identifiants invalides", new Command("OK"));
            }
        });
        
        add(tEmail);
        add(tPassword);
        add(btnlogin);
        
        
    }
    
    
}
