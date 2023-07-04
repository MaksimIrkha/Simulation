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

    public void addEntity(Position position, Entity entity) {
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
        return new HashMap<>(entities);
    }

    public void removeEntityAtLocation(Position position) {
        entities.remove(position);
    }

    public <T extends Entity> boolean hasEntity(Class<T> entityClass) {
        for (Entity entity : entities.values()) {
            if (entity.getClass() == entityClass) {
                return true;
            }
        }
        return false;
    }

    public boolean hasGrass() {
        return hasEntity(Grass.class);
    }

    public boolean hasHerbivore() {
        return hasEntity(Herbivore.class);
    }
}
