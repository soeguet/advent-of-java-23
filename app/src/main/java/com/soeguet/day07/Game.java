package com.soeguet.day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game implements Comparable<Game> {
   char[] cards = new char[5];
   int value;
   int weight;

   public void convertCardStringToCharArray(String cardString) {

      for (int i = 0; i < cardString.length(); i++) {

         cards[i] = cardString.charAt(i);
      }
   }

   public void determineHandWeight() {

      // create set
      Set<Character> cardSet = new HashSet<>();
      for (char card : cards) {
         cardSet.add(card);
      }

      ArrayList<Character> cardList = createArrayList(cards);

      if (cardSet.size() == 1) {

         // FIVE_OF_A_KIND
         weight = HandWeight.FIVE_OF_A_KIND;

      } else if (cardSet.size() == 2) {

         // FOUR_OF_A_KIND, FULL HOUSE

         cardSet.forEach(set -> {

            long sameCardCount = cardList.stream().filter(card -> card == set).count();

            if (sameCardCount == 4) {

               weight = HandWeight.FOUR_OF_A_KIND;
            }
         });

         if (weight == 0) {

            weight = HandWeight.FULL_HOUSE;
         }

      } else if (cardSet.size() == 3) {

         // THREE OF A KIND, TWO PAIR

        cardSet.forEach(set -> {

            long sameCardCount = cardList.stream().filter(card -> card == set).count();

            if (sameCardCount == 3) {

               weight = HandWeight.THREE_OF_A_KIND;
            }

         });

         if (weight == 0) {

            weight = HandWeight.TWO_PAIR;
         }

      } else if (cardSet.size() == 4) {

         // ONE PAIR
         weight = HandWeight.ONE_PAIR;

      } else if (cardSet.size() == 5) {

         // HIGH CARD
         weight = HandWeight.HIGH_CARD;
      }
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

   public int getWeight() {
      return weight;
   }

   public void setWeight(int weight) {
      this.weight = weight;
   }

   private ArrayList<Character> createArrayList(char[] cards2) {

      return new ArrayList<Character>() {
         {
            add(cards2[0]);
            add(cards2[1]);
            add(cards2[2]);
            add(cards2[3]);
            add(cards2[4]);
         }
      };
   }

   @Override
   public int compareTo(Game o) {

      if (o == this) {
         throw new IllegalStateException();
      }

      int comp = Integer.compare(this.weight, o.weight);

      // if same weight -> compare each character
      if (comp == 0) {
         
         return this.compareEachCharacter(0, this.getCards(), o.getCards());
      }

      return comp;
   }

   /**
    * @param round
    * @param thisHand
    * @param otherHand
    * @return int
    */

   private int compareEachCharacter(int round, char[] thisHand, char[] otherHand) {

      if (round == thisHand.length) {
         
         return 0;
      }

      int compare = compareCards(thisHand[round], otherHand[round]);

      if (compare != 0) {

         return compare;
      }

      return compareEachCharacter(round+1, thisHand, otherHand);
   }

   private Map<Character, Integer> cardValues = new HashMap<>() {
      {
         put('2', 2);
         put('3', 3);
         put('4', 4);
         put('5', 5);
         put('6', 6);
         put('7', 7);
         put('8', 8);
         put('9', 9);
         put('T', 10);
         put('J', 11);
         put('Q', 12);
         put('K', 13);
         put('A', 14);
      }
   };

   public int compareCards(char card1, char card2) {
      int value1 = cardValues.getOrDefault(card1, 0);
      int value2 = cardValues.getOrDefault(card2, 0);

      if (value1 == 0 || value2 == 0) {
         throw new IllegalArgumentException("Invalid card value");
      }

      return Integer.compare(value2, value1);
   }
}
