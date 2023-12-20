package com.soeguet.day06;

import java.util.ArrayList;

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
      String[] split = getInput().trim().split("/n");
      String[] time = split[0].trim().split(" ");
      // String[] distance = split[1].trim().split(" ");
      //
      // ArrayList<Round> rounds = new ArrayList<>();
      //
      // for (int i = 1; i < time.length; i++) {
      //    
      //    Round round = new Round();
      //    round.time = Integer.parseInt(time[i]);
      //    round.distance = Integer.parseInt(distance[i]);
      //    rounds.add(round);
      // }
      //
      // System.out.println(rounds);
   }

   class Round {
      int time;
      int distance;
      ArrayList<Integer> winningSeconds = new ArrayList<>();
   }

      
   private String getInput() {

      return """
         Time:      7  15   30
         Distance:  9  40  200
      """;
   }
   
   
}
