package org.example.actions;

import org.example.area.Area;
import org.example.area.Position;
import org.example.entities.*;

import java.util.Map;
import java.util.Random;

public class InitAction extends Action {
    private Area area;
    private Random random = new Random();

    public InitAction(Area area) {
        this.area = area;
    }

    @Override
    public void perform() {
        setRandomEntities(7, 15, 25, 15, 15);
    }

    private void setEntity(Position position, Entity entity) {
        entity.setPosition(position);
        area.getEntities().put(position, entity);
    }

    private void setRandomEntities(int numPredators, int numHerbivores, int numGrass, int numRocks, int numTrees) {
        for (int i = 0; i < numPredators; i++) {
            Position position = generateRandomPosition();
            setEntity(position, new Predator(position,area));
        }

        for (int i = 0; i < numHerbivores; i++) {
            Position position = generateRandomPosition();
            setEntity(position, new Herbivore(position,area));
        }

        for (int i = 0; i < numGrass; i++) {
            Position position = generateRandomPosition();
            setEntity(position, new Grass(position));
        }

        for (int i = 0; i < numRocks; i++) {
            Position position = generateRandomPosition();
            setEntity(position, new Rock(position));
        }

        for (int i = 0; i < numTrees; i++) {
            Position position = generateRandomPosition();
            setEntity(position, new Tree(position));
        }
    }

    private Position generateRandomPosition() {
        Position position;
        Map<Position, Entity> entities = area.getEntities();
        do {
            int x = random.nextInt(area.getWidth());
            int y = random.nextInt(area.getHeight());
            position = new Position(x, y);
        } while (entities.containsKey(position));
        return position;
    }
}
