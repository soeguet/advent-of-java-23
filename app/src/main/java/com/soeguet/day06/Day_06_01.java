package com.soeguet.day06;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Day_06_01
 *
 * 1ms += 1mm/ms
 *
 * 0ms -> 0mm/ms -> 7s -> 0mm
 * 1ms -> 1ms/ms -> 6s -> 6mm
 * 2ms -> 2ms/ms -> 5s -> 10mmm
 * 3ms -> 3ms/ms -> 4s -> 12mm
 * 4ms -> 4ms/ms -> 3s -> 12mm
 * 5ms -> 5ms/ms -> 2s -> 10mm
 * 6ms -> 6ms/ms -> 1s -> 6mm
 *
 */
public class Day_06_01 {

   public Day_06_01() {

      System.out.println("henlo");
      String[] split = getInput().trim().split("\\n");
      String[] time = split[0].trim().split("\\s+");
      String[] distance = split[1].trim().split("\\s+");

      ArrayList<Round> rounds = new ArrayList<>();

      for (int i = 1; i < time.length; i++) {

         Round round = new Round();
         round.time = Integer.parseInt(time[i]);
         round.distance = Integer.parseInt(distance[i]);
         rounds.add(round);
      }

      rounds.forEach(round -> {

         int timeRound = round.getTime();
         int distanceRound = round.getDistance();

         for (int i = 0; i < timeRound; i++) {

            int accelaration = i;
            int travelingTime = timeRound - i;

            if (distanceRound < accelaration * travelingTime) {
               round.incrementPossibilityCounter();
            }
         }
      });

      int count = rounds.stream().mapToInt(Round::getPossibilityCount).reduce(1, (a, b) -> a * b);

      System.out.println("count: " + count);
   }

   class Round {
      int time;
      int distance;
      AtomicInteger winningPossibility = new AtomicInteger(0);

      public int getTime() {
         return time;
      }

      public void setTime(int time) {
         this.time = time;
      }

      public int getDistance() {
         return distance;
      }

      public void setDistance(int distance) {
         this.distance = distance;
      }

      public void incrementPossibilityCounter() {
         winningPossibility.getAndIncrement();
      }

      public int getPossibilityCount() {

         return winningPossibility.get();
      }

   }

   private String getInput() {

      return """
            Time:        53     89     76     98
            Distance:   313   1090   1214   1201
                  """;
   }

   private String getInput1() {

      return """
               Time:      7  15   30
               Distance:  9  40  200
            """;
   }

}
