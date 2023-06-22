package org.example.entities;

import org.example.area.Area;
import org.example.area.Position;

public class Herbivore extends Creature {
    public Herbivore(Position position) {
        super(position, 80);
    }

    public void eatGrass(Position position) {
        Area area = Area.getInstance();
        area.removeEntityAtLocation(position);
        changeHealth(5);

    }

}
