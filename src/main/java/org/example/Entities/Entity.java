package org.example.Entities;

import org.example.Path.PathFinder;
import org.example.Area.Position;

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
