/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Interfaces.SpaceBody;
import Support.SpaceMath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Johnogel
 */
public class HarvestBeam {
private final SpaceShip player;
private double x1, y1, x2, y2, length, renderLength;
private Color color;
    public HarvestBeam(SpaceShip player, double length){
        this.length = length;
        this.player = player;
        x1 = player.getX();
        y1 = player.getY();
    }
    public void setColor(Color color){
        this.color = color;
    }
    
    public void harvest(SpaceBody planet){
        
        for(int i = (int) length; i > 5; i -= 5){
            if(SpaceMath.distanceSquared(new Point2D(x1 + Math.cos(1.5*SpaceMath.PI+player.getAngle())*i, 
                    y1 - Math.sin(1.5*SpaceMath.PI+player.getAngle())*i), planet.getPoint())<planet.getRadius()*planet.getRadius()){
                renderLength = i;
                setColor(planet.getColor());
                player.addMass(20);
                planet.setMass(planet.getMass()-20);
            }
        }
        
    }
    
    public double getEndX(){        
        
        return x2;
    }
    
    public double getEndY(){       
        
        return y2;
    }
    
    public void update(){
        
        x2 = x1 + Math.cos(1.5*SpaceMath.PI+player.getAngle())*renderLength;
        y2 = y1 - Math.sin(1.5*SpaceMath.PI+player.getAngle())*renderLength;
        renderLength = length;
    }
    
    public void render(GraphicsContext gc){
        
        
        gc.setStroke(color);
        gc.setLineWidth(3);
        gc.strokeLine(x1, y1, x2, y2);
        color = Color.WHITE;
        
    }
    
}
