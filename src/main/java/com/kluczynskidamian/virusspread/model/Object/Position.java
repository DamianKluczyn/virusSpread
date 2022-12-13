package com.kluczynskidamian.virusspread.model.Object;


import com.kluczynskidamian.virusspread.model.Vector2d.Vector2D;
import javafx.scene.layout.Pane;


public class Position {
    private Vector2D position;
    public Position(double x, double y){
        position = new Vector2D(x, y);
    }
    public Position(Pane pane, double radius){
        this(radius + Math.random() * (pane.getWidth() - 2 * radius), radius + Math.random() * (pane.getHeight() - 2 * radius));
    }

    public boolean move(Direction direction, Pane pane, double radius){
        position = new Vector2D(getX() + direction.getX(), getY() + direction.getY());
        if(getX()  < 0 || getX() + radius > pane.getWidth()){
            if(Math.random() <= 0.5){
                direction.bounceX();
                position = new Vector2D(getX() + direction.getX(), getY());
            }
            else{
                return false;
            }
        }
        if(getY()  < 0 || getY() + radius > pane.getHeight()){
            if(Math.random() <= 0.5){
                direction.bounceY();
                position = new Vector2D(getX(), getY() + direction.getY());
            }
            else{
                return false;
            }
        }
        return true;
    }
    public boolean meet(Position other){
        return distance(other) <= 2 * Person.radius + 4;
    }

    public double distance(Position other){
        double x = getX();
        double y = getY();
        double otherX = other.getX();
        double otherY = other.getY();

        double distanceX = x - otherX;
        double distanceY = y - otherY;
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    public double getX(){
        return position.getComponents().get(0);
    }
    public double getY(){
        return position.getComponents().get(1);
    }

}
