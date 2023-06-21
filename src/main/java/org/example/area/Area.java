package org.example.area;

import org.example.entities.*;

import java.util.HashMap;
import java.util.Map;

public class Area {
    private static final Area instance = new Area();

    private Map<Position, Entity> entities = new HashMap<>();

    private final int width = 20;
    private final int height = 20;

    private Area() {

    }

    public static Area getInstance() {
        return instance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void setEntity(Position position, Entity entity) {
        entity.setPosition(position);
        entities.put(position, entity);
    }

    public Entity getEntityAtLocation(Position position) {
        return entities.get(position);
    }

    public boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

    public Map<Position, Entity> getEntities() {
        return entities;
    }

    public synchronized void removeEntityAtLocation(Position position) {
        entities.remove(position);
    }

    public boolean hasGrass() {
        for (Entity entity : entities.values()) {
            if (entity instanceof Grass) {
                return true;
            }
        }
        return false;
    }

    public boolean hasHerbivore() {
        for (Entity entity : entities.values()) {
            if (entity instanceof Herbivore) {
                return true;
            }
        }
        return false;
    }

}