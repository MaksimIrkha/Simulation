package org.example.entities;

import org.example.area.Area;
import org.example.area.Position;


public class Predator extends Creature {

    public Predator(Position position) {
        super(position, 100);
    }


    public void eatHerbivore(Position position) {
        Area area = Area.getInstance();
        area.removeEntityAtLocation(position);
        changeHealth(10);

    }


}