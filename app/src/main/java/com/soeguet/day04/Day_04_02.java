package com.soeguet.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Day_04_02
 */
public class Day_04_02 {

    public Day_04_02() {

        ArrayList<Card> cardList = new ArrayList<>();

        String input = getInput();
        System.out.println("input: " + input);

        Stream<String> lines = input.lines();

        lines.forEach(line -> {

            line = line.trim();
            String[] cardAndRest = line.split(":");

            String cardPart = cardAndRest[0].trim();
            int cardNumber = Integer.parseInt(cardPart.substring(4).trim());

            String rest = cardAndRest[1].trim();
            String[] leftAndRightOfCard = rest.split("\\|");

            String winningNumbers = leftAndRightOfCard[0].trim();
            String actualNumbers = leftAndRightOfCard[1].trim();

            String[] winningNumbersArray = winningNumbers.split(" ");
            ArrayList<String> winningNumberList = Arrays.stream(winningNumbersArray)
                    .collect(Collectors.toCollection(ArrayList::new));

            String[] actualNumbersArray = actualNumbers.split(" ");
            ArrayList<String> actualNumberList = Arrays.stream(actualNumbersArray)
                    .collect(Collectors.toCollection(ArrayList::new));

            Card card = new Card();
            card.setCardNumber(cardNumber);
            card.setWinningNumbers(winningNumberList);
            card.setActualNumbers(actualNumberList);

            cardList.add(card);
        });


        cardList.forEach(card -> {
            System.out.println("card: " + card.getCardNumber());
            System.out.println("winningNumbers: " + card.getWinningNumbers());
            System.out.println("actualNumbers: " + card.getActualNumbers());

        });
    }

    class Card {

        private int cardNumber;
        private ArrayList<String> winningNumbers;
        private ArrayList<String> actualNumbers;
        private int cardCopies = 1;

        public void addACardCopy() {
            cardCopies++;
        }
        public int getCardNumber() {
            return cardNumber;
        }
        public void setCardNumber(int cardNumber) {
            this.cardNumber = cardNumber;
        }
        public ArrayList<String> getWinningNumbers() {
            return winningNumbers;
        }
        public void setWinningNumbers(ArrayList<String> winningNumbers) {
            this.winningNumbers = winningNumbers;
        }
        public ArrayList<String> getActualNumbers() {
            return actualNumbers;
        }
        public void setActualNumbers(ArrayList<String> actualNumbers) {
            this.actualNumbers = actualNumbers;
        }
        public int getCardCopies() {
            return cardCopies;
        }
        public void setCardCopies(int cardCopies) {
            this.cardCopies = cardCopies;
        }

    }

    private String getInput() {

        return """
                        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                """;
    }
}
