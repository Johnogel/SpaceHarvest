/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

/**
 *
 * @author Johnogel
 */
public class Origin {
    private double x, y;
    public Origin(double x, double y){
        
        this.x = x;
        this.y = y;
        
    }
    
    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        
        return x;
    }
    
    public double getY(){
        
        return y;
    }
    
}
