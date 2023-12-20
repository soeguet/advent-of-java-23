package com.soeguet.day06;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Day_06_02
 */
public class Day_06_02 {

   public Day_06_02() {
      System.out.println("henlo");
      String[] split = getInput().trim().split("\\n");
      String[] time = split[0].trim().split("\\s+");
      String[] distance = split[1].trim().split("\\s+");

      Round round = new Round();
      StringBuilder timeBuilder = new StringBuilder();
      StringBuilder distanceBuilder = new StringBuilder();

      for (int i = 1; i < time.length; i++) {

         timeBuilder.append(time[i]);
      }

      for (int i = 1; i < distance.length; i++) {

         distanceBuilder.append(distance[i]);
      }

      round.setTime(Long.parseLong(timeBuilder.toString()));
      round.setDistance(Long.parseLong(distanceBuilder.toString()));

      for (int i = 0; i < round.getTime(); i++) {

         long accelaration = i;
         long travelingTime = round.getTime() - i;

         if (round.getDistance() < accelaration * travelingTime) {
            round.incrementPossibilityCounter();
         }
      }

      System.out.println("count: " + round.getPossibilityCount());
   }

   class Round {
      private long time;
      private long distance;
      AtomicLong winningPossibility = new AtomicLong(0);

      public long getTime() {
         return time;
      }

      public void setTime(long time) {
         this.time = time;
      }

      public long getDistance() {
         return distance;
      }

      public void setDistance(long distance) {
         this.distance = distance;
      }

      public void incrementPossibilityCounter() {
         winningPossibility.getAndIncrement();
      }

      public long getPossibilityCount() {

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
