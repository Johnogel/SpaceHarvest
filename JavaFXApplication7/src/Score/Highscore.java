/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Score;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Scanner;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Johnogel
 */
public class Highscore implements Serializable{
private int highscore, initialScore;
private String highscoreString;
private FileOutputStream f_out;
private PrintWriter pw;
private Scanner scan;
private Score score;
private File data;
private DecimalFormat formatter;

    public Highscore(Score score){
        this.score = score;
        formatter = new DecimalFormat("000000000");
        try{
            data = new File("highscore.txt");
           
            
            
            data.createNewFile();
            
            
            
            
            scan = new Scanner(data);
            if(!scan.hasNext()){
                f_out = new FileOutputStream(data, true);
                pw = new PrintWriter(f_out);
                pw.print("0");
                pw.close();
                f_out.close();
            }
            highscore = Integer.parseInt(scan.nextLine());
            initialScore = highscore;
            scan.close();
            highscoreString = formatter.format(highscore);
            
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void load() throws IOException, ClassNotFoundException{
        scan = new Scanner(data);
        highscore = scan.nextInt();
        scan.close();

    }
    
    public void save() throws FileNotFoundException, IOException{
        if(score.getScore() > initialScore){
            f_out = new FileOutputStream(data, false);
            pw = new PrintWriter(f_out);
            pw.println(highscore+"");
            pw.close();
            f_out.close();
        }
    }
    
    public void update(){
        if (score.getScore() > highscore){
            highscore = score.getScore();
            highscoreString = formatter.format(highscore);
        }
    }
    
@Override
    public String toString(){
        return highscoreString;
    }
    
    
    
}
