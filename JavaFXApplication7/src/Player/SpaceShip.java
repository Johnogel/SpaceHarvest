/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Interfaces.SpaceBody;
import Main.Planet;
import Support.SpaceMath;
import Support.Origin;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Johnogel
 */
public class SpaceShip implements SpaceBody {
    protected Point2D  center;
    private Origin origin;
    private double mass;
    //rotational thrusting
    private boolean clockwiseThrust, counterClockwiseThrust, linThrust, harvesting, harvester;
    private double angle, angVel, angAccel, cwAngAccel, ccwAngAccel, linAccel, velocityX, velocityY;
    private double[] xPoints;
    private double[] yPoints;
    private Color color;
    private boolean isDead;
    private HarvestBeam beam;
    
    public SpaceShip(Origin origin){
        xPoints = new double[6];
        yPoints = new double[6];
        this.origin = origin;
        this.center = new Point2D(origin.getX(), origin.getY());
        this.angle = 0;
        this.isDead = false;
        this.mass = 0;
        this.harvesting = false;
        this.harvester = false;
        color = Color.WHITE;
        angVel = 0;
        
        for(int i = 0; i < 6; i++){
            switch (i){
                case 0:
                    //bottom right
                    xPoints[i] =origin.getX()+ Math.cos(1.70*SpaceMath.PI+angle)*30;
                    yPoints[i] =origin.getY() -Math.sin(1.70*SpaceMath.PI+angle)*30;
                    break;
                case 1:
                    //right
                    xPoints[i] =origin.getX() +Math.cos(1.9*SpaceMath.PI+angle)*5;
                    yPoints[i] =origin.getY() -Math.sin(1.9*SpaceMath.PI+angle)*5;
                    break;
                case 2:
                    //top
                    xPoints[i] =origin.getX() +Math.cos(.5*SpaceMath.PI+angle)*30;
                    yPoints[i] =origin.getY() -Math.sin(.5*SpaceMath.PI+angle)*30;
                    break;
                case 3:
                    //left
                    xPoints[i] =origin.getX() + Math.cos(1.1*SpaceMath.PI+angle)*5;
                    yPoints[i] =origin.getY() -Math.sin(1.1*SpaceMath.PI+angle)*5;
                    break;
                case 4:
                    //bottom left
                    xPoints[i] =origin.getX()+ Math.cos(1.30*SpaceMath.PI+angle)*30;
                    yPoints[i] =origin.getY() -Math.sin(1.30*SpaceMath.PI+angle)*30;
                    break;
                case 5:
                    //bottom
                    xPoints[i] =origin.getX() + Math.cos(1.5*SpaceMath.PI+angle)*18;
                    yPoints[i] =origin.getY()  -Math.sin(1.5*SpaceMath.PI+angle)*18;
                    break;
            }
        }
        beam = new HarvestBeam(this, 200);
        
    }
    
    public void setHarvesting(boolean harvesting){
        this.harvesting = harvesting;
    }
    
    public void setClockwiseThrust(boolean thrust){
        clockwiseThrust = thrust;
        if(thrust){
            cwAngAccel = -3;
        }
        else{
            cwAngAccel = 0;
        }
    }
    
    public void setCounterClockwiseThrust(boolean thrust){
        counterClockwiseThrust = thrust;
        if(thrust){
            ccwAngAccel = 3;
        }
        else{
            ccwAngAccel = 0;
        }
    }
    
    @Override
    public double getMass(){
        return mass;
    }
    
    public void addMass(double mass){
        this.mass += mass;
        
    }
    @Override
    public Point2D getPoint(){
        
        return center;
    }
    
    @Override
    public Color getColor(){
        return color;
    }
    
    @Override
    public double getX(){
        return this.getPoint().getX();
    }
    
    @Override
    public double getY(){
        return this.getPoint().getY();
    }
    
    @Override
    public void update(double time){
        double adjTime = time*.1;
        angAccel = cwAngAccel + ccwAngAccel;
        angVel = angVel + angAccel*adjTime;
        angle = angle + (angVel)*(adjTime);
        for(int i = 0; i < 6; i++){
            switch (i){
                case 0:
                    //bottom right
                    xPoints[i] =center.getX()+ Math.cos(1.70*SpaceMath.PI+angle)*30;
                    yPoints[i] =center.getY() -Math.sin(1.70*SpaceMath.PI+angle)*30;
                    break;
                case 1:
                    //right
                    xPoints[i] =center.getX() +Math.cos(1.9*SpaceMath.PI+angle)*5;
                    yPoints[i] =center.getY() -Math.sin(1.9*SpaceMath.PI+angle)*5;
                    break;
                case 2:
                    //top
                    xPoints[i] =center.getX() +Math.cos(.5*SpaceMath.PI+angle)*30;
                    yPoints[i] =center.getY() -Math.sin(.5*SpaceMath.PI+angle)*30;
                    break;
                case 3:
                    //left
                    xPoints[i] =center.getX() + Math.cos(1.1*SpaceMath.PI+angle)*5;
                    yPoints[i] =center.getY() -Math.sin(1.1*SpaceMath.PI+angle)*5;
                    break;
                case 4:
                    //bottom left
                    xPoints[i] =center.getX()+ Math.cos(1.30*SpaceMath.PI+angle)*30;
                    yPoints[i] =center.getY() -Math.sin(1.30*SpaceMath.PI+angle)*30;
                    break;
                case 5:
                    //bottom
                    xPoints[i] =center.getX() + Math.cos(1.5*SpaceMath.PI+angle)*18;
                    yPoints[i] =center.getY()  -Math.sin(1.5*SpaceMath.PI+angle)*18;
                    break;
            }
        }
        
        
        double dvY = linAccel*Math.sin(angle+.5*SpaceMath.PI)*adjTime;
        double dvX = linAccel*Math.cos(angle+.5*SpaceMath.PI)*adjTime;
        addVelocity(-dvX, dvY);
        this.setPosition(origin.getX() + velocityX * adjTime, origin.getY() + velocityY * adjTime);
        //System.out.println("Origin points: "+origin.getX()+" "+origin.getY());
        beam.update();
        
        
        
    }
    
    @Override
    public void gravitateTo(SpaceBody p, double time){
        double planetAngle, dvX, dvY;
        
        if(center.getY() < p.getY()){
            planetAngle = Math.toRadians(180+ center.angle(new Point2D(center.getX()-10, center.getY()), p.getPoint()));
            
        }
        else{
            planetAngle = Math.toRadians(center.angle(new Point2D(center.getX()+10, center.getY()), p.getPoint()));
            
            //dvY = -this.accelerationTo(p)*Math.sin(angle)*time;
        }
        //System.out.println(""+angle);
        dvY = this.accelerationTo(p)*Math.sin(planetAngle)*time;
        dvX = this.accelerationTo(p)*Math.cos(planetAngle)*time;
        
        //System.out.println(color.toString() + " "+angle);
        
        this.addVelocity(-dvX, dvY);
        
    }
    @Override
    public double accelerationTo(SpaceBody p){
        return (p.getMass())/(SpaceMath.distanceSquared(this, p)); 
    }
    
    public boolean collides(Planet planet){
        Point2D temp;
        for(int i = 0; i < xPoints.length; i++){
                temp = new Point2D(xPoints[i],yPoints[i]);
                if(SpaceMath.distanceSquared(temp, planet.getPoint()) < planet.getRadius()*planet.getRadius()){
                    isDead = true;
                    return true;
                }
            }
        return false;
    }
    
    public boolean isDead(){
        
        return isDead;
    }
    
    @Override
    public double getRadius(){
        return 5;
    }
    
    public void attractTo(double time, ArrayList<SpaceBody> planets){
        
        planets.stream().forEach((planet) -> {
            this.gravitateTo(planet, time);
        });
    }
    
    @Override
    public void addVelocity(double vX, double vY){
        velocityX += vX;
        velocityY += vY;
    }
    public void tharvest(ArrayList<SpaceBody> planets){
        if(!isDead)
        {
            if(!harvester){
                if (linThrust){
                    linAccel = 75;
                }
                else{
                    linAccel = 0;
                }
            }
            else{
                if(harvesting){
                    Planet p;
                    Point2D temp;
                    for(SpaceBody planet : planets){
                        p = (Planet) planet;
                        beam.harvest(planet);


                    }
                }
            }
        }
        
    }
    
    public void setLinThrust(boolean linThrust){
        this.linThrust = linThrust;
        
    }
    
    public void toggleThruster(){
        
        harvester = !harvester;
        
    }
    
    public boolean collisionTest(ArrayList<SpaceBody> planets){
        Point2D temp;
        for(SpaceBody planet : planets){
            Planet plnt =(Planet) planet;
            for(int i = 0; i < xPoints.length; i++){
                temp = new Point2D(xPoints[i],yPoints[i]);
                if(SpaceMath.distanceSquared(temp, planet.getPoint()) < plnt.getRadius()*plnt.getRadius()){
                    isDead = true;
                    return true;
                }
            }
            
        }
        return false;
    }
    
    public double getAngle(){
        return angle;
    }
    
    @Override
    public void render(GraphicsContext gc){
        if(!isDead){
            if(!harvester){
                if(linThrust){
                    gc.setStroke(Color.YELLOW);
                    gc.setLineWidth(8);
                    gc.strokeLine(center.getX(), center.getY(),center.getX()+ Math.cos(1.5*SpaceMath.PI+angle)*25,center.getY()- Math.sin(1.5*SpaceMath.PI+angle)*25);
                    gc.setStroke(Color.BLUE);
                    gc.setLineWidth(6);
                    gc.strokeLine(center.getX(), center.getY(),center.getX()+ Math.cos(1.5*SpaceMath.PI+angle)*23,center.getY()- Math.sin(1.5*SpaceMath.PI+angle)*23);
                    gc.setStroke(Color.WHITE);
                    gc.setLineWidth(3);
                    gc.strokeLine(center.getX(), center.getY(),center.getX()+ Math.cos(1.5*SpaceMath.PI+angle)*21,center.getY()- Math.sin(1.5*SpaceMath.PI+angle)*21);
                }
                /*if(clockwiseThrust){
                    gc.setStroke(Color.YELLOW);
                    gc.setLineWidth(8);
                    gc.strokeLine(center.getX(), center.getY(),center.getX()+ Math.cos(1.5*SpaceMath.PI+angle)*25,center.getY()- Math.sin(1.5*SpaceMath.PI+angle)*25);
                    gc.setStroke(Color.BLUE);
                    gc.setLineWidth(6);
                    gc.strokeLine(center.getX(), center.getY(),center.getX()+ Math.cos(1.5*SpaceMath.PI+angle)*23,center.getY()- Math.sin(1.5*SpaceMath.PI+angle)*23);
                    gc.setStroke(Color.WHITE);
                    gc.setLineWidth(3);
                    gc.strokeLine(center.getX(), center.getY(),center.getX()+ Math.cos(1.5*SpaceMath.PI+angle)*21,center.getY()- Math.sin(1.5*SpaceMath.PI+angle)*21);
                }*/
            }
            else{
            
                if(harvesting){

                    beam.render(gc);

                }
            }
            gc.setStroke(color);
            gc.setFill(Color.WHITESMOKE);
            gc.fillPolygon(xPoints, yPoints, 6);

        }
        else{
            gc.setStroke(Color.RED);
            gc.setLineWidth(3.7);
            gc.setFont(Font.font(35));
            gc.strokeText("YOU DIED! PRESS 'R' TO RESTART", this.center.getX(), this.center.getY());
        }
    }

    @Override
    public void setMass(double mass) {
        this.mass = mass; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPosition(double x, double y) {
        origin.setPosition(x, y); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getVelocityX() {
        return velocityX; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getVelocityY() {
        return velocityY; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isPlanet(){
        return false;
    }
}
    

