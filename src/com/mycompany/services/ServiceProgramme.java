/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Programme;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceProgramme {

     public ArrayList<Programme> programme;
    
    public static ServiceProgramme instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceProgramme() {
         req = new ConnectionRequest();
    }

    public static ServiceProgramme getInstance() {
        if (instance == null) {
            instance = new ServiceProgramme();
        }
        return instance;
        
    }
    ArrayList<Programme>  listc = new ArrayList<>();

    public boolean addTask(Programme t) {
        String url = Statics.BASE_URL + "/addProgJson?name=" + t.getName() + "&type=" + t.getType()+ "&description=" + t.getDescription()
                + "&image=" + t.getImage() + "&breackfast=" + t.getBreackfast() + "&lunch=" + t.getLunch() + "&dinner=" + t.getDinner(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Programme> parseProgramme(String jsonText){
        try {
            programme=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> programmeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)programmeListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Programme t = new Programme();
                //float id = Float.parseFloat(obj.get("id").toString());
                //t.setId((int)id);
                t.setName(obj.get("name").toString());
                t.setType(obj.get("type").toString());
                t.setDescription(obj.get("description").toString());
                t.setImage(obj.get("image").toString());
                t.setBreackfast(obj.get("breackfast").toString());
                //t.setLunch(obj.get("lunch ").toString());
                t.setDinner(obj.get("dinner").toString());
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                programme.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return programme;
    }
    public ArrayList<Programme> getlistc(){       
        ConnectionRequest con = new ConnectionRequest();//Appel au service web (demande de connexion).
   con.setUrl("http://localhost:8000/allProgJson");        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
           
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProgramme ser = new ServiceProgramme();
             listc = ser.parseProgramme(new String(con.getResponseData()));
                System.out.println(listc);//Récupération de la réponse du serveur
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);

        return   listc;
    }
    
    public ArrayList<Programme> getAllProgramme(){
        String url = Statics.BASE_URL+"/allProgJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                programme = parseProgramme(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return programme;
    }
    
     ArrayList<Programme>  listDetailReponses = new ArrayList<>();
    

   
    
}
