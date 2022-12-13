package com.kluczynskidamian.virusspread.model.Object;

import java.util.HashMap;
import java.util.Map;

public class CareTaker {
    private Person person;
    private Map<Integer, Memento> mementoMap;
    CareTaker(Person person){
        this.person = person;
        mementoMap = new HashMap<>();
    }
    public void save(Integer second){
        mementoMap.put(second, person.memento());
    }
    public boolean load(Integer second){
        if(mementoMap.containsKey(second)){
            Memento snapshot = mementoMap.get(second);
            person.setDirection(snapshot.direction);
            person.setCircle(snapshot.circle);
            person.setHealTime(snapshot.healTime);
            person.setPane(snapshot.pane);
            person.setPosition(snapshot.position);
            person.setSickTime(snapshot.sickTime);
            person.setState(snapshot.state);
            person.setTimeNearSick(snapshot.timeNearSick);
            return true;
        }
        else{
            return false;
        }
    }

}
