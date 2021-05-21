/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceProgramme;


/**
 *
 * @author ASUS
 */
public class HomeNutriForm extends Form {
    Form current;
    
    
    

    public HomeNutriForm(Form previous) {
       /* super("HomeNutri", BoxLayout.y());
         current = this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Programme");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});*/
        setTitle("Liste Programme");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceProgramme.getInstance().getAllProgramme().toString());
        add(sp);
       
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        //Tabs swipe = new Tabs();
        //Récupération de l'interface(Form) en cours
        //setTitle("Home");
        //setLayout(BoxLayout.y());

       // add(new Label("Choose an option"));
        //Button btnAddTask = new Button("Add Task");
        //Button btnListProgrammes = new Button("List Programmes");

        /*btnAddTask.addActionListener(e -> new AddTaskForm(current).show());
        btnListProgrammes.addActionListener(e -> new ProgrammeForm(current).show());
        addAll( btnListProgrammes);*/
    }
    
}
