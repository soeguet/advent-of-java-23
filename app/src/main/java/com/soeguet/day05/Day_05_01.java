package com.soeguet.day05;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Day_05_01
 */
public class Day_05_01 {

    public Day_05_01() {

        ArrayList<Long> seedList = new ArrayList<Long>();
        AtomicLong shortestDistance = new AtomicLong(0);

        Almanac almanac = new Almanac();
        System.out.println(almanac);

        String input = getInput();
        String[] almanacSplit = input.split("\n\n");

        for (String sections : almanacSplit) {

            // set up maps
            if (sections.trim().contains(":\n")) {

                String[] mappingWithTitle = sections.trim().split(":\n");

                String title = mappingWithTitle[0].trim();

                Map map = new Map();
                map.setName(title);

                String mapping = mappingWithTitle[1].trim();

                String[] mappingSplit = mapping.split("\n");

                for (String mapEntry : mappingSplit) {

                    Entry entry = new Entry();

                    String[] mappingValues = mapEntry.trim().split(" ");

                    long destinationRangeStart = Long.parseLong(mappingValues[0].trim());
                    long sourceRangeStart = Long.parseLong(mappingValues[1].trim());
                    long rangeLength = Long.parseLong(mappingValues[2].trim());

                    entry.setDestinationRangeStart(destinationRangeStart);
                    entry.setSourceRangeStart(sourceRangeStart);
                    entry.setRangeLength(rangeLength);

                    map.addEntry(entry);
                }

                almanac.addMap(map);

            }

            // set up seeds
            if (sections.trim().contains(": ")) {

                String[] split = sections.trim().split(": ");

                String seeds = split[1].trim();
                String[] splitSeeds = seeds.split(" ");

                for (String seedNumberString : splitSeeds) {
                    seedList.add(Long.parseLong(seedNumberString.trim()));
                }
            }

            // check for shortest distance
            seedList.forEach(seed -> {

                long distanceLong = almanac.decodeSeedLocaton(seed);

                // System.out.println("seed: " + seed + " distance: " + distanceLong);

                if (shortestDistance.get() == 0 || distanceLong < shortestDistance.get()) {
                    shortestDistance.set(distanceLong);
                }
            });

        }

        System.out.println("shortest distance: " + shortestDistance.get());
    }

    class Almanac {

        java.util.ArrayList<Map> mapList = new java.util.ArrayList<Map>();

        public long decodeSeedLocaton(long seed) {

            AtomicLong seedSequence = new AtomicLong(seed);
            AtomicLong newValue = new AtomicLong(seed);

            // map part
            mapList.forEach(map -> {

                // entry part
                map.getEntries().forEach(entry -> {

                    System.out.println("");

                    if (seedSequence.get() == newValue.get()) {

                        long convertValue = entry.convertValue(seedSequence.get());

                        newValue.set(convertValue);
                    }
                });
            });

            return newValue.get();
        }

        public java.util.ArrayList<Map> getMapList() {
            return mapList;
        }

        public void setMapList(java.util.ArrayList<Map> mapList) {
            this.mapList = mapList;
        }

        public void addMap(Map map) {
            if (this.mapList == null) {
                this.mapList = new java.util.ArrayList<Map>();
            }
            this.mapList.add(map);
        }

        @Override
        public String toString() {
            return "Almanac [mapList=" + mapList + "]";
        }
    }

    class Map {

        @Override
        public String toString() {
            return "Map [name=" + name + ", entries=" + entries + "]";
        }

        String name;
        java.util.ArrayList<Entry> entries;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public java.util.ArrayList<Entry> getEntries() {
            return entries;
        }

        public void setEntries(java.util.ArrayList<Entry> entries) {
            this.entries = entries;
        }

        public void addEntry(Entry entry) {
            if (this.entries == null) {
                this.entries = new java.util.ArrayList<Entry>();
            }
            this.entries.add(entry);
        }
    }

    class Entry {

        @Override
        public String toString() {
            return "Entry [destinationRangeStart=" + destinationRangeStart + ", sourceRangeStart=" + sourceRangeStart
                    + ", rangeLength=" + rangeLength + "]";
        }

        long destinationRangeStart;
        long sourceRangeStart;
        long rangeLength;

        public long convertValue(long value) {

            if (value >= sourceRangeStart && value < sourceRangeStart + rangeLength) {

                long delta = value - sourceRangeStart;

                return destinationRangeStart + delta;
            }
            return value;
        }

        public long getDestinationRangeStart() {
            return destinationRangeStart;
        }

        public void setDestinationRangeStart(long destinationRangeStart) {
            this.destinationRangeStart = destinationRangeStart;
        }

        public long getSourceRangeStart() {
            return sourceRangeStart;
        }

        public void setSourceRangeStart(long sourceRangeStart) {
            this.sourceRangeStart = sourceRangeStart;
        }

        public long getRangeLength() {
            return rangeLength;
        }

        public void setRangeLength(long rangeLength) {
            this.rangeLength = rangeLength;
        }

    }

    private String getInput() {
        return """
                seeds: 79 14 55 13

                seed-to-soil map:
                50 98 2
                52 50 48

                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15

                fertilizer-to-water map:
                49 53 8
                0 11 42
                42 0 7
                57 7 4

                water-to-light map:
                88 18 7
                18 25 70

                light-to-temperature map:
                45 77 23
                81 45 19
                68 64 13

                temperature-to-humidity map:
                0 69 1
                1 0 69

                humidity-to-location map:
                60 56 37
                56 93 4
                """;
    }
}
