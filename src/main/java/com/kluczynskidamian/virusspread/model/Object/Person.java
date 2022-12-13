package com.kluczynskidamian.virusspread.model.Object;

import com.kluczynskidamian.virusspread.model.State.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Person {
    private IState state;
    private Position position;
    private Direction direction;
    private Pane pane;
    private Circle circle;
    private final CareTaker careTaker;
    private Map<Person, Double> timeNearSick = new HashMap<>();
    private double sickTime = 0.0;
    public static double radius = 6.0;
    public double healTime;

    public Person(IState state, Pane pane, boolean live){
        this.state = state;
        this.direction = new Direction();
        this.circle = new Circle(radius, state.getColor());
        this.pane = pane;
        this.careTaker = new CareTaker(this);
        this.healTime = Math.random() * (30 - 20) + 20;
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);

        if(live){
            this.position = new Position(pane, radius);
        }
        else{
            switch (new Random().nextInt(5 - 1) + 1) {
                case 1 -> this.position = new Position(pane.getWidth(), pane.getHeight() * Math.random());
                case 2 -> this.position = new Position(pane.getWidth() * Math.random(), pane.getHeight());
                case 3 -> this.position = new Position(0, pane.getHeight() * Math.random());
                case 4 -> this.position = new Position(pane.getWidth() * Math.random(), 0);
            }
        }
    }
    public void checkSurrounding(Person other){
        if(position.meet(other.getPosition())){
            if((other.getState() instanceof NoSymptomsState || other.getState() instanceof SymptomsState) && state instanceof HealtyState){
                if(timeNearSick.containsKey(other)){
                    if(timeNearSick.get(other) >= 3.0){
                        if(other.getState() instanceof NoSymptomsState){
                            if(Math.random() <= 0.5){
                                if(Math.random() <= 0.5){
                                    setState(new NoSymptomsState());
                                }
                                else{
                                    setState(new SymptomsState());
                                }
                            }
                        }
                        else{
                            if(Math.random() <= 0.5){
                                setState(new NoSymptomsState());
                            }
                            else{
                                setState(new SymptomsState());
                            }
                        }
                    }
                    else{
                        timeNearSick.put(other, timeNearSick.get(other) + 0.04);
                    }
                }
                else{
                    timeNearSick.put(other, 0.0);
                }
            }
        }
        else{
            if(timeNearSick.containsKey(other)){
                timeNearSick.remove(other);
            }
        }
    }

    public Memento memento(){
        return new Memento(healTime, state, direction, position, sickTime, timeNearSick, pane);
    }
    public void saveMemento(Integer second){
        careTaker.save(second);
    }
    public boolean load(int second){
        boolean memento = careTaker.load(second);
        draw();
        return memento;
    }
    public void recoverHealth(){
        if(state instanceof NoSymptomsState || state instanceof  SymptomsState){
            sickTime += 0.04;
            if(sickTime >= healTime){
                setState(new ImmuneState());
            }
        }
    }
    public IState getState(){
        return state;
    }
    public Position getPosition(){
        return position;
    }
    public void setState(IState state){
        this.state = state;
        circle.setFill(state.getColor());
    }
    public void changeDirection(){
        direction = new Direction();
    }
    public boolean move(){
        return position.move(direction, pane, radius);
    }
    public void draw(){
        circle.setRadius(radius);
        circle.setTranslateX(position.getX());
        circle.setTranslateY(position.getY());
        circle.setStroke(Color.BLACK);
    }
    public void undraw(){
        circle.setRadius(0);
        circle.setStroke(Color.TRANSPARENT);
    }
    public Circle getCircle(){
        return circle;
    }
    public void setPosition(Position position){
        this.position = position;
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }
    public void setPane(Pane pane){
        this.pane = pane;
    }
    public void setCircle(Circle circle){
        this.circle = circle;
    }
    public void setSickTime(double sickTime){
        this.sickTime = sickTime;
    }
    public void setTimeNearSick(Map<Person, Double> timeNearSick){
        this.timeNearSick = timeNearSick;
    }
    public void setHealTime(double healTime){
        this.healTime = healTime;
    }
}
