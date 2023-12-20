package com.soeguet.day07;

/**
 * Day_07_01
 */
public class Day_07_01 {

   public Day_07_01() {

      String input = getInput();

      String[] lines = input.split("\n");

      for (String line : lines) {
         String[] parts = line.split(" ");
         String hand = parts[0];
         int value = Integer.parseInt(parts[1]);

         System.out.println(hand + " " + value);
      }
   }
   class Game{

      char[] cards = new char[5];
      int value;
      HandWeight weight;
   }
   enum HandWeight{
      FIVE_OF_A_KIND,
      FOUR_OF_A_KIND,
      THREE_OF_A_KIND,
      FULL_HOUSE,
      TWO_PAIR,
      ONE_PAIR,
      HIGH_CARD
   }

   private String getInput() {

      return """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
                  """;
   }

}
