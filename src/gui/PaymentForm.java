/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;


/**
 *
 * @author acer
 */
public class PaymentForm extends Form {
    
    public PaymentForm(Form previous) {
        
        Form hi = new Form("Browser", new BorderLayout());
        BrowserComponent browser = new BrowserComponent();
//        browser.setURL("https://www.codenameone.com/");
  browser.setURL("http://127.0.0.1:8000/pay/");
//        browser.setURL("https://google.com");
//        hi.add(BorderLayout.CENTER, browser);
        add(hi.add(BorderLayout.CENTER, browser));

     
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
