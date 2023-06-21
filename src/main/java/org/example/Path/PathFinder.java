package org.example.Path;

import org.example.Area.Area;
import org.example.Area.Position;
import org.example.Entities.*;

import java.util.*;

public class PathFinder {

    public List<Position> findPath(Position startPosition, Creature creature, Area area) {
        Deque<Position> positionQueue = new ArrayDeque<>();
        Set<Position> visitedPositions = new HashSet<>();

        HashMap<Position, Position> parents = new HashMap<>();
        positionQueue.add(startPosition);
        Position targetPosition = null;
        boolean foundTarget = false;

        while (!positionQueue.isEmpty() && !foundTarget) {
            Position currentPosition = positionQueue.remove();
            visitedPositions.add(currentPosition);
            List<Position> neighborPositions = getNeighborPositions(currentPosition, area.getWidth(), area.getHeight());

            for (Position neighborPosition : neighborPositions) {
                if (area.isValidPosition(neighborPosition) && !visitedPositions.contains(neighborPosition)) {
                    if (creature instanceof Predator && area.getEntityAtLocation(neighborPosition) instanceof Herbivore) {
                        targetPosition = neighborPosition;
                        parents.put(neighborPosition, currentPosition);
                        foundTarget = true;
                        break;
                    }
                    if (creature instanceof Herbivore && area.getEntityAtLocation(neighborPosition) instanceof Grass) {
                        targetPosition = neighborPosition;
                        parents.put(neighborPosition, currentPosition);
                        foundTarget = true;
                        break;
                    }
                    if (area.getEntityAtLocation(neighborPosition) == null) {
                        positionQueue.add(neighborPosition);
                        parents.put(neighborPosition, currentPosition);
                    }
                }
            }
        }

        List<Position> path = new LinkedList<>();
        if (targetPosition != null) {
            path.add(targetPosition);
            Position tempPosition = targetPosition;
            while (path.get(path.size() - 1) != startPosition) {
                tempPosition = parents.get(tempPosition);
                path.add(tempPosition);
            }
            Collections.reverse(path);
        }

        return path;
    }

    public Position findNearestEntity(Creature creature, Area area) {
        Position currentPosition = creature.getPosition();
        Position nearestEntity = null;
        double nearestDistance = Double.POSITIVE_INFINITY;

        for (Position position : area.getEntities().keySet()) {
            Entity entity = area.getEntityAtLocation(position);

            if (creature instanceof Predator && entity instanceof Herbivore) {
                double distance = calculateDistance(currentPosition, position);

                if (distance <= nearestDistance) {
                    nearestEntity = position;
                    nearestDistance = distance;
                }
            }
            if (creature instanceof Herbivore && entity instanceof Grass) {
                double distance = calculateDistance(currentPosition, position);

                if (distance <= nearestDistance) {
                    nearestEntity = position;
                    nearestDistance = distance;
                }
            }
        }

        return nearestEntity;
    }

    private double calculateDistance(Position position1, Position position2) {
        int deltaX = position2.getX() - position1.getX();
        int deltaY = position2.getY() - position1.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    private List<Position> getNeighborPositions(Position position, int mapWidth, int mapHeight) {
        int width;
        int height;
        List<Position> neighborPositions = new ArrayList<>();

        if ((width = position.getX() - 1) >= 0) {
            neighborPositions.add(new Position(width, position.getY()));
        }
        if ((width = position.getX() + 1) < mapWidth) {
            neighborPositions.add(new Position(width, position.getY()));
        }
        if ((height = position.getY() - 1) >= 0) {
            neighborPositions.add(new Position(position.getX(), height));
        }
        if ((height = position.getY() + 1) < mapHeight) {
            neighborPositions.add(new Position(position.getX(), height));
        }

        return neighborPositions;
    }
}





