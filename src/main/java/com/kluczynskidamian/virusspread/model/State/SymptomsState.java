package com.kluczynskidamian.virusspread.model.State;

import javafx.scene.paint.Color;

public class SymptomsState implements IState{

    @Override
    public Color getColor(){
        return Color.RED;
    }
}