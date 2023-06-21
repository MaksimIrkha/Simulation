package org.example.entities;

import org.example.area.Area;
import org.example.path.PathFinder;
import org.example.area.Position;

import java.util.List;

public abstract class Creature extends Entity {
    PathFinder pathFinder;
    private int health;
    public Creature(Position position,int health) {
        super(position);
        pathFinder = new PathFinder();
        this.health = health;
    }
    public void changeHealth(int amount) {
        health += amount;
    }

    public void makeMove() {
        Position currentPosition = getPosition();
        Area area = Area.getInstance();

        changeHealth(-5);

        if (health <= 0) {
            area.removeEntityAtLocation(currentPosition);
            return;
        }

        Position targetPosition = pathFinder.findNearestEntity(this, area);

        if (targetPosition != null) {
            List<Position> path = pathFinder.findPath(currentPosition, this, area);

            if (!path.isEmpty()) {
                Position nextPosition = path.get(1);
                Entity entityAtNextPosition = area.getEntityAtLocation(nextPosition);

                if (entityAtNextPosition == null) {
                    area.removeEntityAtLocation(currentPosition);
                    setPosition(nextPosition);
                    area.setEntity(getPosition(), this);
                } else if (entityAtNextPosition instanceof Herbivore && this instanceof Predator) {
                    Predator predator = (Predator) this;
                    Herbivore herbivore = (Herbivore) entityAtNextPosition;
                    predator.eatHerbivore(nextPosition);
                    area.removeEntityAtLocation(currentPosition);
                    setPosition(nextPosition);
                    area.setEntity(getPosition(), this);
                } else if (entityAtNextPosition instanceof Grass && this instanceof Herbivore) {
                    Herbivore herbivore = (Herbivore) this;
                    Grass grass = (Grass) entityAtNextPosition;
                    herbivore.eatGrass(nextPosition);
                    area.removeEntityAtLocation(currentPosition);
                    setPosition(nextPosition);
                    area.setEntity(getPosition(), this);
                }
            }
        }
    }

}
