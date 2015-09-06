/*
 * To change a license header, choose License Headers in Project Properties.
 * To change a template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import Interfaces.SpaceBody;
import javafx.geometry.Point2D;

/**
 *
 * @author Johnogel
 */
public abstract class SpaceMath {
    public static final double PI = 3.1415;
    public static double distanceSquared(Point2D a, Point2D b){
        
        return(((a.getX()-b.getX())*(a.getX()-b.getX()))+((a.getY()-b.getY())*(a.getY()-b.getY())));
        
    }
    public static double distanceSquared(SpaceBody a, SpaceBody b){
        
        return(((a.getX()-b.getX())*(a.getX()-b.getX()))+((a.getY()-b.getY())*(a.getY()-b.getY())));
        
    }
}
