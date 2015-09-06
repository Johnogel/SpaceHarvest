/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Interfaces.SpaceBody;
import Map.Map;
import Player.SpaceShip;
import Score.Highscore;
import Score.Score;
import Support.Origin;
import Support.Gravity;
import Support.LongValue;
import Support.SpaceMath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Johnogel
 */
public class GravityFx extends Application {
public ArrayList<SpaceBody> planets;
public SpaceShip player;
public Point2D  clickPoint;
public Origin origin;
public Background bg;
public Gravity gravity;
public Score score;
public Highscore highscore;
public Map map;
public double canvasOriginX = 0;
public double canvasOriginY = 0;
    
    public void initialize(Canvas canvas){
        planets = new ArrayList<>();
        gravity = new Gravity(planets);
        origin = new Origin(canvas.getWidth()*.5,canvas.getHeight()*.5);
        clickPoint = new Point2D(0,0);
        bg = new Background(origin);
        player = new SpaceShip(origin);
        for (int i = 0; i < 25; i++){
            planets.add(new Planet(Math.random()*50+5 ,Math.random()*8000-4000, Math.random()*8000-4000, new Color(Math.random(), Math.random(), Math.random(), 1),origin));
            //planets.add(new Planet(Math.random()*5+2 ,Math.random()*canvas.getWidth(), Math.random()*canvas.getHeight(), new Color(Math.random(), Math.random(), Math.random(), 1)));
            //planets.add(new Planet(Math.random()*20,20,Math.random()*canvas.getWidth(),new Color(Math.rando)));
            //planets.get(planets.size()-1).setVelocity(Math.random()*20-10, Math.random()*20-10);
            while(SpaceMath.distanceSquared(planets.get(i).getPoint(), player.getPoint()) < (canvas.getWidth()*.5)*(canvas.getWidth()*.5)){
                planets.set(i, new Planet(Math.random()*50+5 ,Math.random()*8000-4000, Math.random()*8000-4000, new Color(Math.random(), Math.random(), Math.random(), 1),origin));
            }
        }
        map = new Map(planets, origin, canvas.getWidth(),canvas.getHeight());
        score = new Score(player);
        highscore = new Highscore(score);
        
        
        
    }
    @Override
    public void start(Stage theStage) {
        
        
        Rectangle2D screen = Screen.getPrimary().getBounds();
        theStage.setTitle("Collect the Money Bags!");
        Canvas canvas = new Canvas(screen.getWidth(), screen.getHeight());
        Group root = new Group();
        Scene theScene = new Scene(root);
        
        theStage.setScene(theScene);
        theStage.setFullScreen(true);
        
        
//        ScrollPane pane = new ScrollPane();
//        pane.setPrefViewportWidth(theScene.getWidth());
//        pane.setPrefViewportHeight(theScene.getHeight());
//        pane.setFitToHeight(true);
//        pane.setFitToWidth(true);
//        pane.setPannable(true);
        
        root.getChildren().add(canvas);
        //pane.setContent(canvas);
        
        //root.getChildren().add(canvas);
        //Camera camera = new Camera();
        
        final double SCALE_DELTA = 1.1;
        
        
        
        
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);
        
        initialize(canvas);
       
        
        
        
        //planets.add(new Planet(275, 700, 100, Color.BLUE, origin));
        //planets.add(new Planet(50, canvas.getWidth()/2, canvas.getHeight()/2, Color.YELLOW, origin));
        //planets.add(new Planet(50, canvas.getWidth()/2+900, canvas.getHeight()/2-500, Color.YELLOW));
        //planets.get(0).setVelocity(60000, 60000);
        //planets.add(new Planet(30, canvas.getWidth()/2+150, canvas.getHeight()/2+50, Color.AQUA));
       //planets.add(new Planet(20, 800,200,Color.BLUE));
        //planets.get(1).addVelocity(-30, -10);
//        planets.add(new Planet(20, 20, 20, Color.RED));
//        planets.add(new Planet(30, 40, 90, Color.BLUE));
        
        //for(Planet p:planets){
        //    p.addVelocity(Math.random()*100-50, Math.random()*100-50);
        //}
//        Point2D p1 = new Point2D(100,100);
//        Point2D p2 = new Point2D(80,100);
//        Point2D p3 = new Point2D(200,80);
//        
//        System.out.println(""+p2.angle(p3, p1));
      
      theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            
            @Override
            public void handle(KeyEvent event){
                event.consume();
                
                System.out.println("This is the shit: "+event.getCode().getName());
                if("R".equals(event.getCode().getName())){
                    initialize(canvas);
                }
                if("Left".equals(event.getCode().getName())){
                    
                    player.setCounterClockwiseThrust(true);
                    
                }
                if("Right".equals(event.getCode().getName())){
                    player.setClockwiseThrust(true);
                }
                
                if("Space".equals(event.getCode().getName())){
                    player.setLinThrust(true);
                    player.setHarvesting(true);
                    player.tharvest(planets);
                }
                
               
                //if(event.getCode()."LEFT"){
                    
                //}
                //System.out.println("Stufffff");
//                planets.removeAll(planets);
//                for (int i = 0; i < 150; i++){
//                    
//                    planets.add(new Planet(Math.random()*25+1 ,Math.random()*canvas.getWidth(), Math.random()*canvas.getHeight(), new Color(Math.random(), Math.random(), Math.random(), 1)));
//                    //planets.add(new Planet(Math.random()*5+2 ,Math.random()*canvas.getWidth(), Math.random()*canvas.getHeight(), new Color(Math.random(), Math.random(), Math.random(), 1)));
//                    //planets.add(new Planet(Math.random()*20,20,Math.random()*canvas.getWidth(),new Color(Math.rando)));
//                    planets.get(planets.size()-1).setVelocity(Math.random()*40-20, Math.random()*40-20);
//                if(event.getCharacter() == "R"){
//                    System.out.println("Stufffff");
//                    
//                    }
//                }
                
                
            }
            
            
        });
    
    //Event listener for player controls
    theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
            
            @Override
            public void handle(KeyEvent event){
                event.consume();
                
                System.out.println("This is the shit: "+event.getCode().getName());
                if("Left".equals(event.getCode().getName())){
                    
                    player.setCounterClockwiseThrust(false);
                    
                }
                if("Right".equals(event.getCode().getName())){
                    player.setClockwiseThrust(false);
                }
                if("Space".equals(event.getCode().getName())){
                    player.setLinThrust(false);
                    player.setHarvesting(false);
                    player.tharvest(planets);
                }
                if("Shift".equals(event.getCode().getName())){
                    player.toggleThruster();
                }
            }
    });
        canvas.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                clickPoint = new Point2D(event.getX(), event.getY());
//                System.out.println("Origin Coordinates: ("+origin.getX()+", "+origin.getY()+"}");
//                System.out.println("ClickPoint Coordinates: ("+clickPoint.getX()+", "+clickPoint.getY()+"}");
            }
            
        });
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                double dx = event.getX() - clickPoint.getX();
                double dy = event.getY() - clickPoint.getY();
                origin.setPosition(dx+origin.getX(),  dy+origin.getY());
                clickPoint =  new Point2D(clickPoint.getX()+ dx, clickPoint.getY()+ dy);
                System.out.println("Origin Coordinates: ("+origin.getX()+", "+origin.getY()+")");
//                System.out.println("ClickPoint Coordinates: ("+clickPoint.getX()+", "+clickPoint.getY()+"}");
            }
        
        
        });
        //Still in the works, probably won't need it for gameplay
        canvas.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override 
            public void handle(ScrollEvent event) {
                event.consume();

            if (event.getDeltaY() == 0) {
              return;
            }
            
            double scaleFactor;
            int sign;
            if (event.getDeltaY() > 0){
                scaleFactor = SCALE_DELTA;
                sign = -1;
            }
            else{
                scaleFactor = 1 / SCALE_DELTA;
                sign = 1;
            }
            
            System.out.println("Width: "+canvas.getWidth()+" Height: "+canvas.getHeight());
            canvas.setScaleX(canvas.getScaleX() * scaleFactor);
            canvas.setScaleY(canvas.getScaleY() * scaleFactor);
            
            double scaleItX = (1/scaleFactor)*canvas.getWidth();
            double scaleItY = (1/scaleFactor)*canvas.getHeight();
            
            //canvas.setWidth((1/scaleFactor)*canvas.getWidth());
            //canvas.setHeight((1/scaleFactor)*canvas.getHeight());
            //canvas.prefHeight(pane.getPrefViewportHeight());
            //canvas.prefWidth(pane.getPrefViewportWidth());
            //canvas.relocate(sign*canvasOriginX+scaleItX/scaleFactor, sign*canvasOriginY+scaleItY/scaleFactor);
            //canvas.relocate(, scaleFactor);
            canvasOriginX = scaleItX;
            canvasOriginY = scaleItY;
            
            
            //canvas.resize(canvas.getWidth()+root.getScaleX() * scaleFactor,canvas.getHeight()+ root.getScaleY() * scaleFactor);
            
          }
        });
        LongValue lastNanoTime = new LongValue(System.nanoTime());
         new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 100000000.0;
                lastNanoTime.value = currentNanoTime;

                
                // collision detection
//                Iterator<Planet> moneybagIter = moneybagList.iterator();
//                while (moneybagIter.hasNext()) {
//                    Sprite moneybag = moneybagIter.next();
//                    if (briefcase.intersects(moneybag)) {
//                        moneybagIter.remove();
//                        score.value++;
//                    }
//                }
//                if (canvas.getHeight() != theStage.getHeight()||canvas.getWidth() != theStage.getWidth())
//                {
//                    canvas.setWidth(theStage.getWidth());
//                    canvas.setHeight(theStage.getHeight());
//                }
                // render
                //gravity.attract(elapsedTime);
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                bg.render(gc);
                gravity.attract(elapsedTime, gc);
                player.attractTo(elapsedTime, planets);

                player.update(elapsedTime);
                score.update();
                highscore.update();
                planets.stream().map((planet) -> {
                    planet.gravitateTo(player, elapsedTime);
                    planet.update(elapsedTime);
                    //planets.get(i).render(gc);
                    return planet;
                }).filter((planet) -> ((planet.getX()+planet.getRadius() > 0)&&(planet.getX()-planet.getRadius() < canvas.getWidth())&&(planet.getY()+planet.getRadius() > 0)&&(planet.getY()-planet.getRadius() < canvas.getHeight()))).forEach((planet) -> {
                    planet.render(gc);
                    //System.out.println(planets.get(i).toString());
                });
                player.tharvest(planets);
                player.collisionTest(planets);
                player.render(gc);
                gc.setFill(Color.WHITE);
                gc.setFont(Font.font(32));
                gc.fillText(score.toString(), canvas.getWidth()*.5, 70);
                gc.setFont(Font.font(28));
                gc.fillText(highscore.toString(), canvas.getWidth()*.5, 35);
                gc.setFont(Font.font(16));
                gc.fillText("background image: http://vignette1.wikia.nocookie.net/powerlisting/images/e/e4/DEEP_SPACE.jpg/revision/latest?cb=20131006085915", canvas.getWidth()*.5, canvas.getHeight()-15);
                //ensures there are always 25 planets on the field or map or whatever you want to call it.
                if(planets.size()<25){
                    planets.add(new Planet(Math.random()*50+5 ,Math.random()*8000-4000, Math.random()*8000-4000, new Color(Math.random(), Math.random(), Math.random(), 1),origin));
                    while(SpaceMath.distanceSquared(planets.get(planets.size()-1).getPoint(), player.getPoint()) < (canvas.getWidth()*.5)*(canvas.getWidth()*.5)){
                        planets.set(planets.size()-1,new Planet(Math.random()*50+5 ,Math.random()*8000-4000, Math.random()*8000-4000, new Color(Math.random(), Math.random(), Math.random(), 1),origin));
                    }
                }
                map.render(gc);
                if(player.isDead()){
                    try {
                        highscore.save();
                    } catch (IOException ex) {
                        Logger.getLogger(GravityFx.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
//                for (Sprite moneybag : moneybagList) {
//                    moneybag.render(gc);
//                }

//                String pointsText = "Cash: $" + (100 * score.value);
//                gc.fillText(pointsText, 360, 36);
//                gc.strokeText(pointsText, 362, 36);
            }
        }.start();
         
         theStage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
