/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.security;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Member;
import com.mycompany.myapp.gui.coach.registerCoachView;
import com.mycompany.myapp.gui.member.registerMemberView;

/**
 *
 * @author NGONGANG Loic F
 */
public class SignupHome extends Form{
    public SignupHome(Form previous){
    Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_ARROW_BACK);
        menuButton.addActionListener(e->previous.show());
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Choix de compte", "WelcomeWhite")
                                )
                            )
                );
      
        tb.setTitleComponent(titleCmp);  
         Button registerMember = new Button("En tant que Membre");
         Button registerCoach = new Button("En tant que Coach");
         
         add(registerMember);add(registerCoach);
         
         registerMember.addActionListener(e-> new registerMemberView(this).show());
         registerCoach.addActionListener(e-> new registerCoachView(this).show());
        
        
    }
}
