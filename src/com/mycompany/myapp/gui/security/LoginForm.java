/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui.security;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.entities.Member;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.coach.homeCoachView;
import com.mycompany.myapp.gui.member.HomeMember;
import com.mycompany.myapp.services.coach.searchCoachService;
import com.mycompany.myapp.services.member.searchMemberService;
import com.mycompany.myapp.services.security.loginService;

/**
 * The Login form
 *
 * @author Shai Almog
 */
public class LoginForm extends Form {
    Form current;
    public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("BIENVENUE\n", "WelcomeBlue"),
                new Label(" SUR ", "WelcomeBlue")
                
        );
        
        getTitleArea().setUIID("Container");
        
        Image profilePic = theme.getImage("logo.jpg");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(800, 200);
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
//        profilePicLabel.setMask(mask.createMask());
        
        TextField login = new TextField("Email", "Login", 20, TextField.EMAILADDR) ;
        TextField password = new TextField("Mot de passe", "Password", 20, TextField.PASSWORD) ;
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE,5);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 5);
        
        Button loginButton = new Button("Se connecter");
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {
//            Toolbar.setGlobalToolbar(false);
//           
//            Toolbar.setGlobalToolbar(true);
            if(new loginService().isUserExists(login.getText(), password.getText()) ){
                   User user = new loginService().searchUser(login.getText(), password.getText()).get(0);
                   if(user.getStatut().equals("nonactived") || user.getStatut().equals("blocked")){
                       if(user.getStatut().equals("nonactived"))
                           Dialog.show("Error", "Votre compte n'est pas encore activé", new Command("OK"));
                       if(user.getStatut().equals("blocked"))
                           Dialog.show("Error", "Votre compte est bloqué", new Command("OK"));
                   }
                   else{
                       
                       if(user.getType().equals("[ROLE_MEMBER]")){
                           Member member = new searchMemberService().searchUser(login.getText()).get(0);
                           Dialog.show("Success", "Connexion réussie! Bienvenue "+user.getUsername(),new Command("OK"));
                           new HomeMember(theme,member).show();
                           
                       }
                       if(user.getType().equals("[ROLE_COACH]")){
                           Coach coach = new searchCoachService().searchUser(login.getText()).get(0);
                            Dialog.show("Success", "Connexion réussie! Bienvenue "+user.getUsername(),new Command("OK"));
                            new homeCoachView(theme,coach).show();
                       }
                   }
                       
               }
               else
                   Dialog.show("Error", "Identifiants invalides", new Command("OK"));
        });
        
        Button createNewAccount = new Button("Pas encore inscrit? \n Créer un nouveau compte.");
        createNewAccount.setUIID("CreateNewAccountButton");
        createNewAccount.addActionListener(e-> new SignupHome(this).show());
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                profilePicLabel,
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}
