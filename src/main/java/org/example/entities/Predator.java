package org.example.entities;

import org.example.area.Area;
import org.example.area.Position;

public class Predator extends Creature {

    public Predator(Position position, Area area) {
        super(position, 100, area);
    }

    @Override
    public boolean isTarget(Entity entity) {
        return entity instanceof Herbivore;
    }

    @Override
    public void performAction(Position nextPosition) {
        Entity entityAtNextPosition = area.getEntityAtLocation(nextPosition);

        if (entityAtNextPosition instanceof Herbivore) {
            eatHerbivore(nextPosition);
        }
    }

    public void eatHerbivore(Position position) {
        area.removeEntityAtLocation(position);
        changeHealth(10);
    }
}
