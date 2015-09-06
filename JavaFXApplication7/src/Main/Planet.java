package Main;

import Interfaces.SpaceBody;
import Support.Origin;
import Support.SpaceMath;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Planet extends Circle implements SpaceBody
{
    private final double PI = 3.1415;
    protected Point2D point;
    protected Origin origin;   
    private double velocityX;
    private double velocityY;
    private double mass;
    private Color color;
    
    

    public Planet (Planet p){
        velocityX = p.getVelocityX();
        velocityY = p.getVelocityY();
        point = new Point2D(p.getX(), p.getY());
        this.setCenterX(point.getX());
        this.setCenterY(point.getY());
        color = new Color(p.getColor().getRed(), p.getColor().getGreen(),p.getColor().getBlue(), p.getColor().getOpacity());
        this.setRadius(p.getRadius());
        mass = PI*(this.getRadius()*this.getRadius());
        origin = p.getOrigin();
        
    }
    public Planet(Planet a, Planet b){
        
        this.setMass(a.getMass()+b.getMass());
        velocityX = (a.getMass()*a.getVelocityX()+b.getMass()*b.getVelocityX())/mass;
        velocityY = (a.getMass()*a.getVelocityY()+b.getMass()*b.getVelocityY())/mass;
        origin = a.getOrigin();
        if (a.getMass()>b.getMass()){
            this.point = new Point2D(origin.getX()+a.getX(),origin.getY()+ a.getY());
            this.color = new Color(a.getColor().getRed(), a.getColor().getGreen(),a.getColor().getBlue(), a.getColor().getOpacity());
            this.setCenterX(a.getCenterX());
            this.setCenterY(a.getCenterY());
        }
        else{
            this.point = new Point2D(origin.getX()+b.getX(),origin.getY()+ b.getY());
            this.color = new Color(b.getColor().getRed(), b.getColor().getGreen(),b.getColor().getBlue(), b.getColor().getOpacity());
            this.setCenterX(b.getCenterX());
            this.setCenterY(b.getCenterY());
        }
        
        
        
    }
    

    public Planet(double radius, Origin origin)
    {   
        
        velocityX = 0;
        velocityY = 0;
        color = Color.BLUE;
        this.setCenterX(0);
        this.setCenterY(0);
        this.setRadius(radius);
        point = new Point2D(origin.getY()+this.getCenterX(),origin.getX()+ this.getCenterY());
        mass = PI*(radius*radius);
        this.origin = origin;
    }
    
    public Planet(double radius, double x, double y, Origin origin)
    {   
        
        velocityX = 0;
        velocityY = 0;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(radius);
        color = Color.BLUE;
        point = new Point2D(origin.getX()+this.getCenterX(),origin.getY()+ this.getCenterY());
        mass = PI*(radius*radius);
        this.origin = origin;
    }
    
    public Planet(double radius, double x, double y, Color color, Origin origin)
    {   
        
        velocityX = 0;
        velocityY = 0;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(radius);
        this.color = color;
        point = new Point2D(origin.getX()+this.getCenterX(),origin.getY()+ this.getCenterY());
        mass = PI*(radius*radius);
        this.origin = origin;
    
    }
    public void updateRadius(double radius){
        
    }
    @Override
    public double getMass(){
        return mass;
    }    
    
    @Override
    public void setMass(double mass){
        this.mass = mass;
        this.setRadius(Math.sqrt(mass/(PI)));
    }
    @Override
    public void setPosition(double x, double y)
    {
        this.setCenterX(x);
        this.setCenterY(y);
        point = new Point2D(this.getCenterX(), this.getCenterY());
    }

    @Override
    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    @Override
    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }
    @Override
    public double getVelocityX(){
        return velocityX;
    }
    
    @Override
    public double getVelocityY(){
        return velocityY;
    }
    
    public Origin getOrigin(){
        return origin;
        
    }
    
    @Override
    public void gravitateTo(SpaceBody p, double time){
        double angle, dvX, dvY;
        if(this.getY() < p.getY()){
            angle = Math.toRadians(180+ this.getPoint().angle(new Point2D(this.getX()-10, this.getY()), p.getPoint()));
            
        }
        else{
            angle = Math.toRadians(this.getPoint().angle(new Point2D(this.getX()+10, this.getY()), p.getPoint()));
            
            //dvY = -this.accelerationTo(p)*Math.sin(angle)*time;
        }
        //System.out.println(""+angle);
        dvY = this.accelerationTo(p)*Math.sin(angle)*time;
        dvX = this.accelerationTo(p)*Math.cos(angle)*time;
        
        //System.out.println(color.toString() + " "+angle);
        
        this.addVelocity(dvX, -dvY);
        
    }
    @Override
    public double accelerationTo(SpaceBody p){
        return (p.getMass())/(SpaceMath.distanceSquared(this, p)); 
    }
    
    
    public Color getColor(){
        return color;
    
    }
    
    @Override
    public void update(double time)
    {
        
        this.setCenterX(this.getCenterX() + velocityX * time);
        this.setCenterY(this.getCenterY() + velocityY * time);
        point = new Point2D(this.origin.getX()+this.getCenterX(), this.origin.getY()+this.getCenterY());
        
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
    public void render(GraphicsContext gc)
    {
        gc.setFill(color);
        gc.fillOval(origin.getX()+this.getCenterX()-this.getRadius(),origin.getY()+ this.getCenterY() - this.getRadius(), this.getRadius() +this.getRadius(), this.getRadius() +this.getRadius());
        System.out.println("Planet: "+this.origin.getX()+" "+this.origin.getY());
        
    }
    
    public void render(GraphicsContext gc, Origin origin){
        gc.setFill(color);
        gc.fillOval(this.origin.getX()+this.getCenterX()-this.getRadius(),this.origin.getY()+this.getCenterY() - this.getRadius(), 
                this.getRadius() +this.getRadius(), this.getRadius() +this.getRadius());
        System.out.println("Planet: "+this.origin.getX()+" "+this.origin.getY());
    }
    public void renderLines(GraphicsContext gc, Planet p){
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        gc.strokeLine(origin.getX()+this.getCenterX(), origin.getY()+this.getCenterY(), origin.getX()+p.getCenterX(),origin.getY()+ p.getCenterY());
        //gc.strokeLine(origin.getX()+this.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ this.getY());
        //gc.strokeLine(origin.getX()+p.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ p.getY());
    }
    
    @Override
    public Point2D getPoint(){
        return point;
    }
    public double distance(Planet planet){
        return this.getPoint().distance(planet.getPoint());
    }
    public boolean intersects(Planet s)
    {
        return ((this.getRadius()+s.getRadius())*(this.getRadius()+s.getRadius()) > 
                    (((this.getCenterX()-s.getCenterX())*(this.getCenterX()-s.getCenterX()))+((this.getCenterY()-s.getCenterY())*(this.getCenterY()-s.getCenterY()))));
    }
    
    public boolean intersects(Canvas c){
        return ((this.getPoint().getX() < this.getRadius())||(this.getPoint().getX() > c.getWidth()-this.getRadius())
                ||(this.getPoint().getY() < this.getRadius())||this.getPoint().getY() > c.getHeight() - this.getRadius());
    }
    
    @Override
    public String toString()
    {
        return " Position: [" + this.getCenterX() + "," + this.getCenterY() + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "] Color: " +color.toString() +" Hashcode: "+this.hashCode();
    }

    @Override
    public boolean isPlanet() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
}
