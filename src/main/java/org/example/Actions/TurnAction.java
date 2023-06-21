package org.example.Actions;

import org.example.Area.Area;
import org.example.Entities.*;
import org.example.Area.AreaRenderer;

import java.util.ArrayList;
import java.util.List;

public class TurnAction extends Action {
    private Area area;
    private int turnCount;
    private AreaRenderer renderer;
    private List<Action> actions;
    private boolean isPaused;
    private Object lock;

    public TurnAction(Area area, AreaRenderer renderer) {
        this.area = area;
        this.renderer = renderer;
        actions = new ArrayList<>();
        isPaused = false;
        lock = new Object();
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    @Override
    public void perform() {
        turnCount++;

        for (Action action : actions) {
            action.perform();
        }

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

    public void resumeSimulation() {
        isPaused = false;
        synchronized (lock) {
            lock.notify();
        }
    }
}
