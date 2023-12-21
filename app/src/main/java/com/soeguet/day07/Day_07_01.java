package com.soeguet.day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Day_07_01
 */
public class Day_07_01 {

   public Day_07_01() {

      this.setupMap();

      String input = getInput();

      String[] lines = input.split("\n");

      for (String line : lines) {

         String[] parts = line.split(" ");
         String hand = parts[0];
         int value = Integer.parseInt(parts[1]);

         Game game = new Game();
         game.setValue(value);
         game.convertCardStringToCharArray(hand);
         game.determineHandWeight();

         System.out.println(game);
      }
   }

   private void setupMap() {

      weightMap.put(HandWeight.FIVE_OF_A_KIND, 1);
      weightMap.put(HandWeight.FOUR_OF_A_KIND, 2);
      weightMap.put(HandWeight.FULL_HOUSE, 3);
      weightMap.put(HandWeight.THREE_OF_A_KIND, 4);
      weightMap.put(HandWeight.TWO_PAIR, 5);
      weightMap.put(HandWeight.ONE_PAIR, 6);
      weightMap.put(HandWeight.HIGH_CARD, 7);
   }

   class Game {

      char[] cards = new char[5];
      int value;
      HandWeight weight;

      public void convertCardStringToCharArray(String cardString) {
         for (int i = 0; i < cardString.length(); i++) {
            cards[i] = cardString.charAt(i);
         }
      }

      public void determineHandWeight() {

         Set<Character> cardSet = new HashSet<>();

         cardSet.add(cards[0]);
         cardSet.add(cards[1]);
         cardSet.add(cards[2]);
         cardSet.add(cards[3]);
         cardSet.add(cards[4]);

         if (cardSet.size() == 1) {

            // FIVE_OF_A_KIND
            weight = HandWeight.FIVE_OF_A_KIND;

         } else if (cardSet.size() == 2) {

            // FOUR_OF_A_KIND, FULL HOUSE

         } else if (cardSet.size() == 3) {

            // THREE OF A KIND, TWO PAIR
            
            //TODO HASHMAP


         } else if (cardSet.size() == 4) {

            // ONE PAIR
            weight = HandWeight.ONE_PAIR;

         } else if (cardSet.size() == 5) {

            // HIGH CARD
            weight = HandWeight.HIGH_CARD;
         }
      }

      private ArrayList<Character> createArrayList(char[] cards2) {

		return new ArrayList<Character>(){{
         add(cards2[0]);
         add(cards2[1]);
         add(cards2[2]);
         add(cards2[3]);
         add(cards2[4]);
      }};
	}

	public void setValue(int value) {
         this.value = value;
      }

      public char[] getCards() {
         return cards;
      }

      public void setCards(char[] cards) {
         this.cards = cards;
      }

      public int getValue() {
         return value;
      }

      public HandWeight getWeight() {
         return weight;
      }

      public void setWeight(HandWeight weight) {
         this.weight = weight;
      }
   }

   enum HandWeight {
      FIVE_OF_A_KIND,
      FOUR_OF_A_KIND,
      THREE_OF_A_KIND,
      FULL_HOUSE,
      TWO_PAIR,
      ONE_PAIR,
      HIGH_CARD
   }

   HashMap<HandWeight, Integer> weightMap = new HashMap<>();

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
