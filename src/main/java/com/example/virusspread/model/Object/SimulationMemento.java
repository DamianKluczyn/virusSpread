package com.example.virusspread.model.Object;

import java.util.ArrayList;

public class SimulationMemento {
    public ArrayList<Person> personArrayList;

    public SimulationMemento(ArrayList<Person> personArrayList){
        this.personArrayList = new ArrayList<>();
        this.personArrayList.addAll(personArrayList);
    }
}
