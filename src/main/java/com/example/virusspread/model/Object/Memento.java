package com.example.virusspread.model.Object;

import com.example.virusspread.model.State.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import static com.example.virusspread.model.Object.Person.radius;

import java.util.HashMap;
import java.util.Map;

public class Memento {
    public double healTime;
    public IState state;
    public Direction direction;
    public Position position;
    public Pane pane;
    public Circle circle;
    public double sickTime = 0.0;
    public Map<Person, Double> timeNearSick;

    public Memento(double healTime, IState state, Direction direction, Position position, double sickTime, Map<Person, Double> timeNearSick){
        this.healTime = healTime;
        this.circle = new Circle(radius, state.getColor());
        this.sickTime = sickTime;
        this.timeNearSick = new HashMap<>();
        this.direction = new Direction(direction.getX(), direction.getY(), direction.getSpeed());
        this.position = new Position(position.getX(), position.getY());
        this.circle.setStroke(Color.BLACK);
        for(Person person: timeNearSick.keySet()){
            this.timeNearSick.put(person, timeNearSick.get(person));
        }
        if(state instanceof HealtyState){
            this.state = new HealtyState();
        } else if (state instanceof ImmuneState) {
            this.state = new ImmuneState();
        } else if (state instanceof NoSymptomsState) {
            this.state = new NoSymptomsState();
        } else if (state instanceof SymptomsState) {
            this.state = new SymptomsState();
        }
    }
}
