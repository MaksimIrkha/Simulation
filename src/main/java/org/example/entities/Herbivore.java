package org.example.entities;

import org.example.area.Area;
import org.example.area.Position;

public class Herbivore extends Creature {
    public Herbivore(Position position, Area area) {
        super(position, 80, area);
    }

    @Override
    public void performAction(Position nextPosition) {
        Entity entityAtNextPosition = area.getEntityAtLocation(nextPosition);

        if (entityAtNextPosition instanceof Grass) {
            eatGrass(nextPosition);
        }
    }

    public void eatGrass(Position position) {
        area.removeEntityAtLocation(position);
        changeHealth(5);
    }
}