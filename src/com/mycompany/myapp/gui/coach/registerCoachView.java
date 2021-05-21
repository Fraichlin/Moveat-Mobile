/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.coach;

import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.io.File;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.entities.Member;
import com.mycompany.myapp.services.coach.registerCoachService;
import com.mycompany.myapp.services.member.registerMemberService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

/**
 *
 * @author NGONGANG Loic F
 */
public class registerCoachView extends Form{

    public registerCoachView(Form previous) {
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
                                    new Label("Inscription Coach", "WelcomeWhite")
                                )
                            )
                );
      
        tb.setTitleComponent(titleCmp);  
    
        TextField tName = new TextField("", "Nom");
        TextField tSurname = new TextField("", "Prénom");
        TextField tEmail = new TextField("","Email*",30,TextField.EMAILADDR);
        TextField tUsername = new TextField("","Username*");
        TextField tPassword = new TextField("","Mot de passe*",20,TextField.PASSWORD);
        TextField tConfirmPassword = new TextField("","Confirmer mot de passe*",20,TextField.PASSWORD);
        ComboBox cbSexe = new ComboBox("Sexe");
        cbSexe.addItem("Homme");
        cbSexe.addItem("Femme");
        
        ComboBox cbSp = new ComboBox("Spécialité");
        cbSp.addItem("Nutritioniste");
        cbSp.addItem("Psychothérapeute");       
        cbSp.addItem("Coach Sportif");

        
        TextField tAdresse = new TextField("","Adresse*");
        TextField tTel = new TextField("","Téléphone*");
        Button btnImage = new Button("Choisir photo de profil");
        Button btnFile = new Button("Choisir fichier justificatif");
        Label lbImage = new Label();
        Container profilPicture = new Container(BoxLayout.x());
        Container profilfile = new Container(BoxLayout.x());
        profilPicture.add(lbImage);
        profilPicture.add(btnImage);
        profilfile.add(btnFile);
         
                
        addAll(tName,tSurname,tEmail,tUsername,tPassword,tConfirmPassword,cbSexe,cbSp,tAdresse,tTel,profilPicture,profilfile);
        
        
        CheckBox multiSelect = new CheckBox("Multi-select");    
         Date date = new Date();
         long uuid = date.getTime();
         String UUID = uuid+"x"+uuid;
        btnImage.addActionListener((e)->{         
             if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".jpg, .jpeg, .png/plain", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);
                        }
                        return;
                    }

                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;

                        try {
                            logo = Image.createImage(file).scaledHeight(200);
                            add(logo);
                            String imageFile ="file://C:/xampp/htdocs/moveat2/public/upload/images/"+UUID+".png";

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                System.out.println(imageFile);
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }

                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                            StringBuilder hi = new StringBuilder(file);
                            if (file.startsWith("file://")) {
                                hi.delete(0, 7);
                            }
                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
                            Log.p(hi.toString());
                            String ext = hi.toString().substring(lastIndexPeriod);
                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                            try {
                                String namePic = saveFileToDevice(file, ext);
                                System.out.println(namePic);
                            } catch (IOException ex) {
                            }

                            revalidate();

                        
                    }
                    }
                        });
            }
        });
        btnFile.addActionListener((e)->{         
              if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf", e2-> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[])e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);
                            
                        }
                        return;
                    }
                    
                    String file = (String)e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                       System.out.println("File path: " + file);
                       
                       String extension = null;
                       if (file.lastIndexOf(".") > 0) {
                           extension = file.substring(file.lastIndexOf(".")+1);
                       }
                        FileSystemStorage fs = FileSystemStorage.getInstance();
                       if ("txt".equals(extension)) {
                          
                           try {
                               InputStream fis = fs.openInputStream(file);
                               addComponent(new SpanLabel(Util.readToString(fis)));
                           } catch (Exception ex) {
                               Log.e(ex);
                           }
                       } else {
                           add("Selected file "+file);
                           
                       }
                       
                        try {
                        InputStream stream = FileSystemStorage.getInstance().openInputStream(file);
                        OutputStream out = Storage.getInstance().createOutputStream("file://C:/xampp/htdocs/moveat2/public/upload/fichiers/"+UUID+".pdf");
                        Util.copy(stream, out);
                        Util.cleanup(stream);
                        Util.cleanup(out);
                         } catch (IOException ex) {
                        }
                       
                      
                       
                       
                    }
                    revalidate();
                });
            }
        });
        
        
        Button btnRegister = new Button("Enregistrer");
         btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(tEmail.getText().equals("")
                        ||tUsername.getText().equals("")||tPassword.getText().equals("")||
                        tConfirmPassword.getText().equals("")||tAdresse.getText().equals("")||
                        tTel.getText().equals(""))
                    Dialog.show("Alert","veuillez remplir tous les champs avec un *",new Command("ok"));
                else{
                    try{
                        Coach m = new Coach();
                        m.setNom(tName.getText());
                        m.setPrenom(tSurname.getText());
                        m.setEmail(tEmail.getText());
                        m.setUsername(tUsername.getText());
                        m.setPassword(tPassword.getText());
                        m.setSexe(cbSexe.getSelectedItem().toString());
                        m.setAdresse(tAdresse.getText());
                        m.setSpecialite(cbSp.getSelectedItem().toString());
                        m.setTel(tTel.getText());
                        m.setPhoto(UUID+".png");
                        if(new registerCoachService().registerCoach(m))
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
    }
    protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
    
}
