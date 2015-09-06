/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Support.Origin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Johnogel
 */
public class Background {
protected Origin origin;
private final Image image;
protected double x, y;
    public Background(Origin origin){
        
        this.origin = origin;
        this.image = new Image("Images/background.jpg");
        x = -600;
        y = -600;
    }
    
    public void render(GraphicsContext gc){
        gc.drawImage(image, x+origin.getX()*.1, y+origin.getY()*.1);
    }
    
}
