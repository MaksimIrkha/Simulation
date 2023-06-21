package org.example.Entities;

import org.example.Area.Area;
import org.example.Path.PathFinder;
import org.example.Area.Position;

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
    public void eatHerbivore(Position position) {
        Area area = Area.getInstance();
        area.removeEntityAtLocation(position);
        changeHealth(10);

    }
    public void eatGrass(Position position) {
        Area area = Area.getInstance();
        area.removeEntityAtLocation(position);
        changeHealth(5);

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
                } else if (entityAtNextPosition instanceof Herbivore) {
                    eatHerbivore(nextPosition);
                    area.removeEntityAtLocation(currentPosition);
                    setPosition(nextPosition);
                    area.setEntity(getPosition(), this);
                } else if (entityAtNextPosition instanceof Grass) {
                    eatGrass(nextPosition);
                    area.removeEntityAtLocation(currentPosition);
                    setPosition(nextPosition);
                    area.setEntity(getPosition(), this);
                }
            }
        }
    }


}
