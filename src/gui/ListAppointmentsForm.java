/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import services.ServiceAppointment;

/**
 *
 * @author acer
 */
public class ListAppointmentsForm extends Form {
    public ListAppointmentsForm(Form previous) {
        setTitle("List Appointmentss");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceAppointment.getInstance().getAllAppointments());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
