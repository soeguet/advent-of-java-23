package com.soeguet.day02;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Day_02_01 {

    public Day_02_01() {

        String base = """
                Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """;

        Stream<String> lines = base.lines();

        // each line represents a game
        lines.forEach(line -> {

            String[] gameAndRounds = splitGameAndRounds(line);

            String[] rounds = splitRounds(gameAndRounds[1]);

            for (String round : rounds) {
                
                String[] cubes = splitCubes(round);

                for (String cube : cubes) {
                    
                    String[] colorAndAmount = splitValue(cube.trim());

                    System.out.println(colorAndAmount[0]);
                }

            }

            
        });
    }

    private String[] splitValue(String input){
        return input.split("[ ]");
    }

    private String[] splitCubes(String input){
        return input.split("[,] ");
    }

    private String[] splitRounds(String input){
        return input.split("[;]");
    }

    private String[] splitGameAndRounds(String input){
        return input.split("[:]");
    }
}
