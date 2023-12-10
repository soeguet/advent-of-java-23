package com.soeguet.day03;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Day_03_01 {

    public Day_03_01() {

        String input = getInput();
        Stream<String> lines = input.lines();
        ArrayList<Point> pointColumnList = new ArrayList<>();
        ArrayList<ArrayList<Point>> rowList = new ArrayList<>();

        lines.forEach(line -> {

            char[] charArray = line.toCharArray();

            String concatValue = "";

            for (int i = 0; i < charArray.length; i++) {

                char abc = charArray[i];

                if (Character.isDigit(abc)){
                    

                    concatValue = concatValue + abc;
                }
                else if (abc == '.') {
                    
                    concatValue = "";
                }
                else if (!Character.isLetterOrDigit(abc)) {
                    
                }
            }
        });

        System.out.println(input);
    }

    public class Point {

        private int row;
        private int column;
        private Type type;
        private int value;
        private boolean evaluate = false;

        public int lookForValue() {

            if (evaluate) {

                return value;
            }

            return 0;
        }
    }

    enum Type {
        NONE, DIGIT, CHAR
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
