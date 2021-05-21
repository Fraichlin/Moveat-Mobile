/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.member;

import com.codename1.components.MultiButton;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Member;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.member.registerMemberService;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
/**
 *
 * @author NGONGANG Loic F
 */
public class profilMemberView extends SideMenuBaseForm{
    public profilMemberView(Resources res,Member user){
    super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Profil", "WelcomeWhite"),
                                    new Label("Modification", "SubTitle")
                                )
                            ).add(BorderLayout.WEST, profilePicLabel)
                );
      
        tb.setTitleComponent(titleCmp);                                
       
        setupSideMenu(res,user);
        TextField tName = new TextField(user.getNom());
        TextField tSurname = new TextField(user.getPrenom());
        TextField tEmail = new TextField(user.getEmail());
        TextField tUsername = new TextField(user.getUsername());
        TextField tPassword = new TextField("12345678","12345678",20,TextField.PASSWORD);
        TextField tConfirmPassword = new TextField("12345678","12345678",20,TextField.PASSWORD);
        
        TextField tTaille = new TextField(user.getTaille());
        TextField tPoids = new TextField(user.getPoids());
        Button btnImage = new Button("Choisir photo de profil");
        Label lbImage = new Label();
        Container profilPicture = new Container(BoxLayout.x());
        profilPicture.add(lbImage);
        profilPicture.add(btnImage);
          
        addAll(tName,tSurname,tEmail,tUsername,tPassword,tConfirmPassword,tTaille,tPoids,profilPicture);
        
         CheckBox multiSelect = new CheckBox("Multi-select");    
         Date date = new Date();
         long uuid = date.getTime();
         String imageId = uuid+"x"+uuid;
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
                            String imageFile ="file://C:/xampp/htdocs/moveat2/public/upload/images/"+imageId+".png";

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
        
         Button btnRegister = new Button("Enregistrer");
         add(btnRegister);
         btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(tEmail.getText().equals("")
                        ||tUsername.getText().equals("")||tPassword.getText().equals("")||
                        tConfirmPassword.getText().equals("")||tTaille.getText().equals("")||
                        tPoids.getText().equals(""))
                    Dialog.show("Alert","veuillez remplir tous les champs avec un *",new Command("ok"));
                else{
                    try{
                        Member m = new Member();
                        m.setNom(tName.getText());
                        m.setPrenom(tSurname.getText());
                        m.setEmail(tEmail.getText());
                        m.setUsername(tUsername.getText());
                        
                        if(!tPassword.getText().equals("12345678"))
                            m.setPassword(tPassword.getText());
                        else
                            m.setPassword("none");
                        
                        System.out.println(m.getPassword());
                        m.setTaille(tTaille.getText());
                        m.setPoids(tPoids.getText());
                        if(lbImage.getText().equals("") || lbImage.getText().equals("No file was selected"))
                            m.setPhoto(user.getPhoto());
                        else
                            m.setPhoto(imageId+".png");
                        if(new registerMemberService().updateMember(m))
                            Dialog.show("Success", "Modification réussie", new Command("OK"));
                        else
                            Dialog.show("Error", "Echec de modification", new Command("OK"));
                    }catch(Exception e){
                        Dialog.show("Error", "erreur inconnue !",new Command("OK"));
                    }
                }
                
            }
            
        });
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
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

    @Override
    protected void showOtherForm(Resources res) {
    }
}
