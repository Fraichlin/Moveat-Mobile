/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.member;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Member;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NGONGANG Loic F
 */
public class searchMemberService {
    public static searchMemberService instance;
    public boolean result;
    private ConnectionRequest req;
    public ArrayList<Member> members;

    public searchMemberService() {
        req = new ConnectionRequest();
    }
     public static searchMemberService getInstance(){
        if(instance == null)instance = new searchMemberService();
        return instance;
    }
     
     public ArrayList<Member> searchUser(String email){
        String url = Statics.URL+"/searchMember?email="+email;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                members = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return members;
    }
     public ArrayList<Member> parseUsers(String jsonText){
         try{
              members = new ArrayList<Member>();
             JSONParser j = new JSONParser();
            Map<String,Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
             System.out.println(userListJson.toString());
             List<Map<String,Object>> list = (List<Map<String,Object>>)userListJson.get("root");
             if(!"[]".equals(list.toString())){
             for(Map<String,Object> obj : list){
                    Member user = new Member();
                    float id =Float.parseFloat(obj.get("id").toString());
                    user.setIdUser((int)id);
                    user.setEmail(obj.get("email").toString());
                    user.setType(obj.get("roles").toString());
                    user.setUsername(obj.get("username").toString());
                    user.setNom(obj.get("nom").toString());
                    user.setPrenom(obj.get("prenom").toString());
                    user.setSexe(obj.get("sexe").toString());
                    user.setStatut(obj.get("statut").toString());
                    user.setPhoto(obj.get("photo").toString());
                    user.setTaille(obj.get("taille").toString());
                    user.setPoids(obj.get("poids").toString());

                    members.add(user);
                 }
             }
             if("[]".equals(list.toString())){
                 Member user1 = new Member();
                 user1.setIdUser(0);
                 members.add(user1);
             }
                 
             }
         catch(IOException ex){
             System.out.println(ex.getMessage());
         }
         return members;
     }
}
