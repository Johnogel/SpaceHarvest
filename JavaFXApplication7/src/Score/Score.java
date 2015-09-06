/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Score;

import Player.SpaceShip;
import java.text.DecimalFormat;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Johnogel
 */
public class Score {
private int score;
private SpaceShip player;
private final DecimalFormat formatter;
private String scoreString;
    public Score(SpaceShip player){
        this.player = player;
        score = (int) player.getMass();
        formatter = new DecimalFormat("000000000");
        scoreString = formatter.format(score);
    }
    
    public void update(){
        
        score = (int) player.getMass();
        scoreString = formatter.format(score);
    }
    
    public int getScore(){
        return score;
    }
    
    @Override
    public String toString(){
        return scoreString;
    }
}
