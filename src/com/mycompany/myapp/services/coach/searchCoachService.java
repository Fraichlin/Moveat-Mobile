/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.coach;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Coach;
import com.mycompany.myapp.entities.Member;
import com.mycompany.myapp.services.member.searchMemberService;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NGONGANG Loic F
 */
public class searchCoachService {
     public static searchCoachService instance;
    public boolean result;
    private ConnectionRequest req;
    public ArrayList<Coach> coachs;

    public searchCoachService() {
        req = new ConnectionRequest();
    }
     public static searchCoachService getInstance(){
        if(instance == null)instance = new searchCoachService();
        return instance;
    }
     
     public ArrayList<Coach> searchUser(String email){
        String url = Statics.URL+"/searchMember?email="+email;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                coachs = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return coachs;
    }
     public ArrayList<Coach> parseUsers(String jsonText){
         try{
              coachs = new ArrayList<Coach>();
             JSONParser j = new JSONParser();
            Map<String,Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
             System.out.println(userListJson.toString());
             List<Map<String,Object>> list = (List<Map<String,Object>>)userListJson.get("root");
             if(!"[]".equals(list.toString())){
             for(Map<String,Object> obj : list){
                    Coach user = new Coach();
                    float id =Float.parseFloat(obj.get("id").toString());
                    user.setIdUser((int)id);
                    user.setEmail(obj.get("email").toString());
                    user.setType(obj.get("roles").toString());
                    user.setUsername(obj.get("username").toString());
                    user.setNom(obj.get("nom").toString());
                    user.setPrenom(obj.get("prenom").toString());
                    user.setStatut(obj.get("statut").toString());
                    user.setPhoto(obj.get("photo").toString());
                    user.setAdresse(obj.get("adresse").toString());
                    user.setTel(obj.get("telephone").toString());

                    coachs.add(user);
                 }
             }
             if("[]".equals(list.toString())){
                 Coach user1 = new Coach();
                 user1.setIdUser(0);
                 coachs.add(user1);
             }
                 
             }
         catch(IOException ex){
             System.out.println(ex.getMessage());
         }
         return coachs;
     }
}
