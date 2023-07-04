package org.example.entities;

import org.example.area.Area;
import org.example.area.Position;


public class Predator extends Creature {

    public Predator(Position position, Area area) {
        super(position, 100, area);
    }


    public void eatHerbivore(Position position) {
        area.removeEntityAtLocation(position);
        changeHealth(10);

    }


}