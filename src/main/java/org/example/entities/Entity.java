package org.example.entities;

import org.example.path.PathFinder;
import org.example.area.Position;

public abstract class Entity {

    public Position position;
    PathFinder pathFinder;

    public Position getPosition() {
        return position;
    }
    public Entity(Position position) {
        this.position = position;
        pathFinder = new PathFinder();
    }

    public void setPosition(Position position) {
        this.position = position;
    }



}
