/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.security;

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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NGONGANG Loic F
 */
public class loginService {
     public static loginService instance;
    public boolean result;
    private ConnectionRequest req;
   public ArrayList<User> users;

    public loginService() {
        req = new ConnectionRequest();
    }
     public static loginService getInstance(){
        if(instance == null)instance = new loginService();
        return instance;
    }
     public boolean isUserExists(String email,String password){
         return this.searchUser( email , password).get(0).getIdUser() != 0;
     }
     public ArrayList<User> searchUser(String email,String password){
        String url = Statics.URL+"/login?email="+email+"&password="+password;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
     public ArrayList<User> parseUsers(String jsonText){
         try{
              users = new ArrayList<User>();
             JSONParser j = new JSONParser();
            Map<String,Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
             System.out.println(userListJson.toString());
             List<Map<String,Object>> list = (List<Map<String,Object>>)userListJson.get("root");
             if(!"[]".equals(list.toString())){
             for(Map<String,Object> obj : list){
                    User user = new User();
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
                    
                    users.add(user);
                 }
             }
             if("[]".equals(list.toString())){
                 User user1 = new User();
                 user1.setIdUser(0);
                 users.add(user1);
             }
                 
             }
         catch(IOException ex){
             System.out.println(ex.getMessage());
         }
         return users;
     }
}
