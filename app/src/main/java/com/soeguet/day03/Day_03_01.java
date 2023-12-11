package com.soeguet.day03;

import java.net.NetworkInterface;

public class Day_03_01 {

    public Day_03_01() {

        String input = getInput();

        char[][] matrix = convertMultiLineStringToMatrix(input);

        int neighbours = this.determinNeighbourCount(matrix, 4, 0);

        System.out.println(neighbours);
    }


    private int determinNeighbourCount(char[][] matrix, int row, int column) {

        int neighbourCount = 0;

        int[] columnArray = new int[]{-1, 0, 1};
        int[] rowArray = new int[]{-1, 0, 1};

        for (int r = 0; r < rowArray.length; r++) {
            
            for (int c = 0; c < columnArray.length; c++) {
                
                if (r == 0 && c == 0) {
                    
                    continue;
                }

                if (pointOutOfBounds(matrix, r, c)) {
                    
                    continue;
                }
            }
        }






        return 0;
    }


    private boolean pointOutOfBounds(char[][] matrix, int r, int c) {
        return false;
    }


    // ready
    private int grabValueAtPoint(char[][] matrix, int row, int column) {

        if (!Character.isDigit(matrix[row][column])) {

            return 0;
        }

        String valueAtPoint = "" + matrix[row][column];

        int indexForwards = 0;
        int indexBackwards = 0;

        // forwards
        do {

            indexForwards++;

            // if out of bounds
            if (column + indexForwards > matrix[row].length - 1) {
                break;
            }

            char spot = matrix[row][column + indexForwards];
            if (!Character.isDigit(spot)) {

                break;

            } else {

                valueAtPoint = valueAtPoint + spot;
            }

        } while (true);

        // backwards
        do {

            indexBackwards--;

            // if out of bounds
            if (column + indexBackwards < 0) {

                break;
            }

            char spot = matrix[row][column + indexBackwards];
            if (!Character.isDigit(spot)) {

                break;

            } else {

                valueAtPoint = spot + valueAtPoint;
            }

        } while (true);

        return Integer.parseInt(valueAtPoint);
    }

    private char[][] convertMultiLineStringToMatrix(String input) {
        String[] lines = input.split("\\n");
        char[][] matrix = new char[lines.length][];

        for (int i = 0; i < matrix.length; i++) {

            String row = lines[i].trim();

            matrix[i] = row.toCharArray();
        }

        return matrix;
    }

    private String getInput() {
        return """
                                    467..114..
                                    ...*......
                                    ..35..633.
                                    ......#...
                                    617*......
                                    .....+.58.
                                    ..592.....
                                    ......755.
                                    ...$.*....
                                    .664.598..
                """;

    }

}
