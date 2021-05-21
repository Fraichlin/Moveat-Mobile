/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.member;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Member;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author NGONGANG Loic F
 */
public class registerMemberService {
    public static registerMemberService instance;
    public boolean result;
    private ConnectionRequest req;

    public registerMemberService() {
        req = new ConnectionRequest();
    }
     public static registerMemberService getInstance(){
        if(instance == null)instance = new registerMemberService();
        return instance;
    }
    
    
    public boolean registerMember(Member m){
        String url = Statics.URL+"/registerMember?nom="+m.getNom()+"&prenom="+m.getPrenom()+"&email="+m.getEmail()+"&password="+
                m.getPassword()+"&username="+m.getUsername()+"&sexe="+m.getSexe()+"&taille="+m.getTaille()+"&poids="+m.getPoids()+"&photo="+
                m.getPhoto();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode()==200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    public boolean updateMember(Member m){
        String url = Statics.URL+"/updateMember?nom="+m.getNom()+"&prenom="+m.getPrenom()+"&email="+m.getEmail()+"&password="+
                m.getPassword()+"&username="+m.getUsername()+"&taille="+m.getTaille()+"&poids="+m.getPoids()+"&photo="+
                m.getPhoto();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode()==200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
}
