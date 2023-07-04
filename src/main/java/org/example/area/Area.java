package org.example.area;

import org.example.entities.Entity;
import org.example.entities.Grass;
import org.example.entities.Herbivore;

import java.util.HashMap;
import java.util.Map;

public class Area {
    private final int width = 20;
    private final int height = 20;
    private Map<Position, Entity> entities = new HashMap<>();

    public Area() {

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setEntity(Position position, Entity entity) {
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

    public void removeEntityAtLocation(Position position) {
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
