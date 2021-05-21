/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceProgram;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form{
    

    public ListTasksForm(Form previous) {
        setTitle("List Programs");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceProgram.getInstance().displayPrograms().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

   /** ListTasksForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    
}
