/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.gui.coach.registerCoachView;
import com.mycompany.myapp.gui.member.registerMemberView;
import com.mycompany.myapp.gui.security.loginView;

/**
 *
 * @author NGONGANG Loic F
 */
public class IndexView extends Form{
Form current;
    public IndexView() {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        Label l1 = new Label("Bienvenue sur MoVeat !");
        Label l2 = new Label("Veuillez choisir une option");
        Button btnlogin = new Button("Se connecter");
        Label l3 = new Label("Pas encore inscrit ?");
        btnlogin.addActionListener(e-> new loginView().show());
        Button btnRegisterCoach = new Button("Inscription Coach");
        Button btnRegisterMember = new Button("Inscription Membre");
        btnRegisterCoach.addActionListener(e-> new registerCoachView(current).show());
        btnRegisterMember.addActionListener(e-> new registerMemberView(current).show());
        addAll(l1,l2,btnlogin,l3,btnRegisterCoach,btnRegisterMember);
        
    }
    
    
        
}
