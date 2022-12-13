package com.example.virusspread;

import com.example.virusspread.model.Object.Simulation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.example.virusspread.model.Object.Simulation;
import javafx.scene.paint.Color;
import javafx.event.Event;

import javafx.animation.AnimationTimer;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class VirusController {
    @FXML
    private Pane pane;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button stepButton;
    @FXML
    private CheckBox immunityBox;
    @FXML
    private ChoiceBox<Integer> snapshotBox;
    @FXML
    private TextField timeText;

    private Movement clock;

    Simulation simulation;

    private class Movement extends AnimationTimer{
        private long fps = 25;
        private long interval = 1000000000 / fps;
        private long last = 0;
        private Integer ticks = 0;
        private double time = 0.0;

        public void setTime(double time){
            this.time = time;
        }

        public void setTicks(Integer ticks){
            this.ticks = ticks;
        }

        @Override
        public void handle(long now){
            if(now - last > interval){
                step();
                last = now;
                time  += 1.0 / fps;
                ticks ++;
                timeText.setText(String.format("%.2f", time));
                if(ticks % 25 == 0){
                    simulation.saveMemento(ticks/25);
                    snapshotBox.getItems().add(ticks/25);
                }
            }
        }
        public void resetTime(){
            time = 0.0;
            timeText.setText(String.format("%.2f", time));
            snapshotBox.getItems().clear();
            ticks = 0;
        }
    }

    @FXML
    public void initialize(){
        clock = new Movement();
        snapshotBox.setOnAction(this::load);
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    @FXML
    public void start(){
        if(simulation == null){
            reset();
        }
        clock.start();
    }

    @FXML
    public void reset(){
        stop();
        pane.getChildren().clear();
        simulation = new Simulation(pane, 100, immunityBox.isSelected());
        clock.resetTime();
        snapshotBox.getItems().clear();
        simulation.saveMemento(0);
        snapshotBox.getItems().add(0);
    }

    @FXML
    public void stop(){
        clock.stop();
    }

    @FXML
    public void step(){
        simulation.step(pane);
    }

    @FXML
    public void load(Event event){
        pane.getChildren().clear();
        simulation.loadMemento(snapshotBox.getValue());
        clock.setTime(snapshotBox.getValue());
        clock.setTicks(snapshotBox.getValue() * 25);
        for(int i = snapshotBox.getItems().size() - 1; i >= snapshotBox.getValue(); i--){
            snapshotBox.getItems().remove(i);
        }

    }



}