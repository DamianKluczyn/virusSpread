package com.example.virusspread.model.Object;

import java.util.HashMap;
import java.util.Map;

public class SimulationCareTaker {
    private Simulation simulation;
    private Map<Integer, SimulationMemento> simulationMementoMap;

    SimulationCareTaker(Simulation simulation){
        this.simulation = simulation;
        simulationMementoMap = new HashMap<>();
    }

    public void save(Integer second){
        System.out.println(simulationMementoMap.size());
        simulationMementoMap.put(second, simulation.memento());
    }

    public boolean load(Integer second){
        if(simulationMementoMap.containsKey(second)){
            SimulationMemento snapshot = simulationMementoMap.get(second);
            simulation.setPerson(snapshot.personArrayList);
            return true;
        }
        else{
            return false;
        }
    }
}
