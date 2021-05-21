/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.coach;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author NGONGANG Loic F
 */
public class registerCoachService {
    public static registerCoachService instance;
    public boolean result;
    private ConnectionRequest req;

    public registerCoachService() {
        req = new ConnectionRequest();
    }
     public static registerCoachService getInstance(){
        if(instance == null)instance = new registerCoachService();
        return instance;
    }
    
    
    public boolean registerCoach(Coach m){
        String url = Statics.URL+"/registerCoach?nom="+m.getNom()+"&prenom="+m.getPrenom()+"&email="+m.getEmail()+"&password="+
                m.getPassword()+"&username="+m.getUsername()+"&sexe="+m.getSexe()+"&adresse="+m.getAdresse()+"&specialite="+m.getSpecialite()+"&tel="+m.getTel()+"&photo="+
                m.getPhoto();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode()==200;
                System.out.println("dksjhdksh");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    public boolean updateCoach(Coach m){
        String url = Statics.URL+"/updateCoach?nom="+m.getNom()+"&prenom="+m.getPrenom()+"&email="+m.getEmail()+"&password="+
                m.getPassword()+"&username="+m.getUsername()+"&sexe="+m.getSexe()+"&adresse="+m.getAdresse()+"&specialite="+m.getSpecialite()+"&tel="+m.getTel()+"&photo="+
                m.getPhoto();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode()==200;
                System.out.println("dksjhdksh");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
}
