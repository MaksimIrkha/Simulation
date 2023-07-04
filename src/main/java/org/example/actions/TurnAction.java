package org.example.actions;

import org.example.area.Area;
import org.example.area.AreaRenderer;
import org.example.entities.Creature;
import org.example.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class TurnAction extends Action {
    private Area area;
    private int turnCount;
    private AreaRenderer renderer;
    private boolean isPaused;
    private Object lock;

    public TurnAction(Area area, AreaRenderer renderer) {
        this.area = area;
        this.renderer = renderer;
        isPaused = false;
        lock = new Object();
    }

    @Override
    public void perform() {
        turnCount++;

        if (!isPaused) {
            for (Entity entity : new ArrayList<>(area.getEntities().values())) {
                if (entity instanceof Creature) {
                    Creature creature = (Creature) entity;
                    creature.makeMove();
                }
            }

            renderer.renderArea(area);
            System.out.println("Turn count: " + turnCount);
        }
    }

    public void pauseSimulation() {
        isPaused = true;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void resumeSimulation() {
        isPaused = false;
        synchronized (lock) {
            lock.notify();
        }
    }
}