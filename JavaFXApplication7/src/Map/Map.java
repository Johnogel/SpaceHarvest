/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Interfaces.SpaceBody;
import Main.Planet;
import Support.Origin;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Johnogel
 */
public class Map {
private ArrayList<SpaceBody> planets;
private Origin origin;
private double canvasWidth, canvasHeight;
    public Map(ArrayList<SpaceBody> planets, Origin origin, double canvasWidth, double canvasHeight){
        
        this.planets = planets;
        this.origin = origin;
        this.canvasHeight = canvasHeight*.25;
        this.canvasWidth = canvasWidth*.25;
        
    }
    
    public void render(GraphicsContext gc){
        
        gc.setFill(new Color(0,0,0,.65));
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        gc.setFill(Color.WHITE);
        for(SpaceBody planet : planets){
            Planet temp = (Planet) planet;
            if((temp.getCenterX()+6000-temp.getRadius())*.02 < canvasWidth-2 && (temp.getCenterY()+6000-temp.getRadius())*.02 < canvasHeight-2){
                gc.fillOval((temp.getCenterX()+6000-temp.getRadius())*.02, (temp.getCenterY()+6000-temp.getRadius())*.02, (temp.getRadius()+temp.getRadius())*.02, (temp.getRadius()+temp.getRadius())*.02);
        
            }
        }
        gc.setFill(Color.RED);
        gc.fillRect((-origin.getX()+6000)*.02+11, (-origin.getY()+6000)*.02+9, 2, 2);
    }
}
