package com.soeguet.day03;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Day_03_02 {

    public Day_03_02() {

        String input = getInput();

        char[][] matrix = convertMultiLineStringToMatrix(input);

        for (int row = 0; row < matrix.length; row++) {

            for (int column = 0; column < matrix[0].length; column++) {

                char x = matrix[row][column];

                if (x == '.') {

                    continue;
                }

                if (x == '*') {

                    this.checkForNeighbourDigits(matrix, row, column);
                }
            }
        }

        System.out.println("result: " + resultLong.get());
    }

    private void checkForNeighbourDigits(char[][] matrix, int row, int column) {

        ArrayList<Integer> neighbourDigitList = new ArrayList<>();

        int[] columnArray = new int[] { -1, 0, 1 };
        int[] rowArray = new int[] { -1, 0, 1 };

        // loop through rows
        for (int r = 0; r < rowArray.length; r++) {

            // continue if out of bounds
            int currentRow = row + rowArray[r];

            if (currentRow < 0 || currentRow > matrix.length - 1) {

                continue;
            }

            // loop through columns
            for (int c = 0; c < columnArray.length; c++) {

                int currentColumn = column + columnArray[c];

                // continue if out of bounds
                if (currentColumn < 0 || currentColumn > matrix[0].length - 1) {

                    continue;
                }

                char x = matrix[currentRow][currentColumn];

                // continue if this spot
                if (columnArray[c] == 0 && rowArray[r] == 0) {

                    continue;
                }

                if (x == '.') {

                    continue;
                }

                if (Character.isDigit(x)) {

                    int valueAtPoint = grabValueAtPoint(matrix, currentRow, currentColumn);
                    //TODO need to handle skip -> if value extends to the right, all is ok. if 
                    // if extends to the left, I need to handle that
                    int skipCounter = String.valueOf(valueAtPoint).length() - 1;
                    c += skipCounter;
                    neighbourDigitList.add(valueAtPoint);
                }
            }
        }

        if (neighbourDigitList.size() == 2) {

            long result = neighbourDigitList.get(0) + neighbourDigitList.get(1);

            resultLong.getAndAdd(result);
        }
    }

    private int grabValueAtPoint(char[][] matrix, int row, int column) {

        String valueAtPoint = "" + matrix[row][column];

        int indexForwards = 0;
        int indexBackwards = 0;

        // forwards
        do {

            indexForwards++;

            // if out of bounds
            int currentColumn = column + indexForwards;
            if (currentColumn > matrix[row].length - 1) {
                break;
            }

            char spot = matrix[row][currentColumn];
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
            int currentColumn = column + indexBackwards;

            if (currentColumn < 0) {

                break;
            }

            char spot = matrix[row][currentColumn];
            if (!Character.isDigit(spot)) {

                break;

            } else {

                valueAtPoint = spot + valueAtPoint;
            }

        } while (true);

        return Integer.parseInt(valueAtPoint);
    }

    private AtomicLong resultLong = new AtomicLong(0);

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
