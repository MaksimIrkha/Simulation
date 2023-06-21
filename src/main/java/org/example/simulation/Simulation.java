package org.example.simulation;

import org.example.actions.*;
import org.example.area.Area;
import org.example.area.AreaRenderer;
import org.example.area.ClearScreen;

import java.util.Scanner;

public class Simulation {
    private Area area;
    private int turnCount;
    private AreaRenderer renderer;
    private TurnAction turnAction;
    private boolean isPaused;
    private Scanner scanner;

    public Simulation() {
        area = Area.getInstance();
        renderer = new AreaRenderer();
        turnAction = new TurnAction(area, renderer);
        isPaused = false;
        scanner = new Scanner(System.in);
    }

    public void addAction(Action action) {
        turnAction.addAction(action);
    }

    public void nextTurn() {
        turnCount++;
        turnAction.perform();
    }

    public void startSimulation()  {
        Thread simulationThread = new Thread(() -> {
            while (area.hasGrass() && area.hasHerbivore()) {
                if (!isPaused) {
                    ClearScreen.clrscr();
                    nextTurn();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        simulationThread.start();

        String input;

        System.out.println("Simulation started. Enter 'p' to pause or 'r' to resume or 'q' to exit...");

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("p")) {
                turnAction.pauseSimulation();
                System.out.println("Simulation paused. Enter 'r' to resume or 'q' to exit...");
            } else if (input.equalsIgnoreCase("r")) {
                turnAction.resumeSimulation();
                System.out.println("Simulation resumed.");
            } else if (input.equalsIgnoreCase("q")) {
                System.exit(0);
            }

            if (!area.hasGrass() || !area.hasHerbivore()) {
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();
        InitAction initAction = new InitAction(Area.getInstance());
        initAction.setRandomEntities(7, 15, 25, 20, 20);

        simulation.startSimulation();

    }
}

