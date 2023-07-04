package org.example.simulation;

import org.example.actions.InitAction;
import org.example.actions.TurnAction;
import org.example.area.Area;
import org.example.area.AreaRenderer;
import org.example.area.ClearScreen;

import java.util.Scanner;

public class Simulation {
    private Area area;
    private AreaRenderer renderer;
    private TurnAction turnAction;
    private Scanner scanner;

    public Simulation() {
        area = new Area();
        renderer = new AreaRenderer();
        turnAction = new TurnAction(area, renderer);
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        InitAction initAction = new InitAction(simulation.area);
        initAction.perform();

        simulation.startSimulation();
    }

    public void nextTurn() {
        turnAction.perform();
    }

    public void startSimulation() {
        Thread simulationThread = new Thread(() -> {
            while (area.hasGrass() && area.hasHerbivore()) {
                if (!turnAction.isPaused()) {
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
}