package org.example.area;

import org.example.entities.*;

public class AreaRenderer {

    public static final String ANSI_PREDATOR =  "\uD83D\uDC3A";
    public static final String ANSI_HERBIVORE =  "\uD83D\uDC11";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GRASS = "\uD83C\uDF3F";
    public static final String ANSI_ROCK = "\u26F0";
    public static final String ANSI_TREE = "\uD83C\uDF33";
    public static final String ANSI_BACKGROUND = "\u2B1B";


    public void renderArea(Area area){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int x = 0; x < Area.getInstance().getHeight() ; x++) {
            for (int y = 0; y < Area.getInstance().getWidth(); y++) {
                Position position = new Position(x,y);
                Entity entity = area.getEntityAtLocation(position);
                if(entity!=null){
                    String symbol;
                    if(entity instanceof Predator){
                        symbol = ANSI_PREDATOR;
                    }
                    else if(entity instanceof Herbivore){
                        symbol = ANSI_HERBIVORE;
                    }
                    else if(entity instanceof Grass){
                        symbol = ANSI_GRASS;
                    }
                    else if(entity instanceof Rock){
                        symbol = ANSI_ROCK;
                    }
                    else if (entity instanceof Tree){
                        symbol = ANSI_TREE;
                    }
                    else {
                        symbol = "";
                    }
                    System.out.print(symbol + ANSI_RESET);
                } else {
                    System.out.print(ANSI_BACKGROUND);
                }
            }

            System.out.println();
        }
    }


}


