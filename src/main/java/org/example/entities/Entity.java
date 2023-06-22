package org.example.entities;

import org.example.area.Position;
import org.example.path.PathFinder;

public abstract class Entity {

    public Position position;
    PathFinder pathFinder;

    public Entity(Position position) {
        this.position = position;
        pathFinder = new PathFinder();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


}
