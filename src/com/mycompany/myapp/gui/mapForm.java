/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Programs;
import com.mycompany.myapp.services.ServiceProgram;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Emir
 */
/**public class mapForm extends HomeForm {
    
    Form current;
    program p = new program();
    private Resources theme;
    public static int idstation = 0;
    List<program> rp = new ArrayList<program>();
    serviceprogram sr = new serviceprogram();

    

    public mapForm(com.codename1.ui.util.Resources resourceObjectInstance) throws IOException {
       
        current.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Nos locaux", "Title")
                )
        );
        current.getToolbar().addCommandToRightBar("retour", null, (ev) -> {
            programs r = new programs();
            getF().show();
        });
        rp=sr.getAllprograms();
        System.out.println(rp.toString());
       Image marker = Image.createImage("/marker.png");
        Image fill = marker.fill(30, 30);

        MapComponent m = new MapComponent();
        double latitude = 36.899527163883356;
        double longitude = 10.18983787368006;
        Coord lastLocation = new Coord(latitude, longitude);
        m.zoomTo(lastLocation, 10);
        //Image marker = Image.createImage(theme.getImage("marker.png"));

        //Coord coordonnees = new Coord(36.899527163883356, 10.18983787368006);
        for(program r:rp){
        Coord coordonnees = sr.getCoords(r.getVille());
        PointLayer pl = new PointLayer(coordonnees, r.getMarque(), fill);
        pl.setDisplayName(true);
        PointsLayer pointsL = new PointsLayer();
        pointsL.addPoint(pl);
        pointsL.setPointIcon(fill);
        m.addLayer(pointsL);
        }
        

        getF().show();
        
        
    }

    mapForm() {
         //To change body of generated methods, choose Tools | Templates.
    }

    
    public Form getF() {
        return current;
    }
}
*/

