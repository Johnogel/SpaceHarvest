/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Johnogel
 */
public interface SpaceBody {
    
    public double getMass();
    
    public void setMass(double mass);
    
    public void setPosition(double x, double y);

    public void setVelocity(double x, double y);

    public void addVelocity(double x, double y);
    
    public double getVelocityX();
    
    public double getVelocityY();
    
    public void gravitateTo(SpaceBody p, double time);
    
    public double accelerationTo(SpaceBody p);
    
    public Point2D getPoint();
    
    public double getX();
    
    public double getY();
    
    public Color getColor();
    
    public double getRadius();
    
    public void update(double time);
    
    public void render(GraphicsContext gc);
    
    public boolean isPlanet();
    
 
    
}
