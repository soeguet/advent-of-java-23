package com.soeguet.day05;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Day_05_02
 */
public class Day_05_02 {

    public Day_05_02() {
        ArrayList<Long> seedList = new ArrayList<>();
        AtomicLong shortestDistance = new AtomicLong(0);

        Almanac almanac = new Almanac();

        String input = getInput();
        String[] almanacSplit = input.split("\n\n");

        for (String sections : almanacSplit) {

            // [1] set up seeds
            if (sections.trim().contains(": ")) {

                String[] split = sections.trim().split(": ");

                String seeds = split[1].trim();
                String[] splitSeeds = seeds.split(" ");

                long seedStart = 0;
                long seedRange = 0;
                for (int i = 2; i < splitSeeds.length + 2; i++) {

                    if (i % 2 == 0) {

                        seedStart = Long.parseLong(splitSeeds[i - 2].trim());

                    } else {

                        seedRange = Long.parseLong(splitSeeds[i - 2].trim());

                        for (int j = 0; j < seedRange; j++) {
                            seedList.add(seedStart + j);
                        }

                        seedRange = 0;
                        seedStart = 0;
                    }
                }
            }

            // [2] set up maps
            if (sections.trim().contains(":\n")) {

                String[] mappingWithTitle = sections.trim().split(":\n");

                String title = mappingWithTitle[0].trim();

                Map map = new Map();
                map.setName(title);

                String mapping = mappingWithTitle[1].trim();

                String[] mappingSplit = mapping.split("\n");

                // [2.1] add entries to maps
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

        }

        // [3] check for shortest distance with virtual threads
        ExecutorService executorService = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor();
        seedList.forEach(seed -> executorService.submit(() -> {

            long distanceLong = almanac.decodeSeedLocaton(seed);

            if (shortestDistance.get() == 0 || distanceLong < shortestDistance.get()) {
                shortestDistance.set(distanceLong);
            }
        }));

        System.out.println("shortest distance: " + shortestDistance.get());
    }

    class Almanac {

        java.util.ArrayList<Map> mapList = new java.util.ArrayList<>();

        public long decodeSeedLocaton(long seed) {

            AtomicLong seedSequence = new AtomicLong(seed);
            AtomicLong newValue = new AtomicLong(seed);

            // map part
            mapList.forEach(
                    map -> {

                        // entry part
                        map.getEntries().forEach(entry -> {

                            if (seedSequence.get() == newValue.get()) {

                                long convertValue = entry.convertValue(seedSequence.get());

                                newValue.set(convertValue);
                            }
                        });

                        seedSequence.set(newValue.get());
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

                long convertedSeedValue = destinationRangeStart + delta;

                return convertedSeedValue;
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
                        seeds: 1044452533 40389941 3710737290 407166728 1552449232 639689359 3327654041 26912583 3440484265 219136668 1126550158 296212400 2332393052 229950158 200575068 532702401 4163696272 44707860 3067657312 45353528

                seed-to-soil map:
                1953514507 1808056938 198190267
                3721110896 2006247205 109062451
                1046714200 3920284337 339096560
                965003502 2389762347 16609477
                1946017538 563731936 3971811
                2151704774 1532707249 120594830
                1385810760 3525158 560206778
                1949989349 0 3525158
                3830173347 2115309656 274452691
                0 567703747 902873308
                2272299604 2471473045 1448811292
                902873308 1470577055 62130194
                981612979 2406371824 65101221
                4104626038 1653302079 154754859

                soil-to-fertilizer map:
                131659986 0 297180572
                86587273 297180572 45072713
                1582904051 2761968868 319965883
                2557550592 597853135 123775334
                3043086384 1215510442 38848367
                2396751907 437054450 160798685
                2705233686 1278266569 337852698
                2681325926 1254358809 23907760
                437054450 1616119267 1145849601
                0 342253285 86587273
                1902869934 721628469 493881973

                fertilizer-to-water map:
                467642512 2808058179 217483684
                2526131270 1651737021 215776571
                1565499377 511818960 6051670
                3343480207 1867513592 514593
                2798489883 3734797658 14675956
                3857697742 2589503758 214253670
                815171875 1987566640 37566751
                247326358 0 171803249
                2084427309 467642512 44176448
                2958404070 2075919098 113827599
                3343994800 3944730705 350236591
                2741907841 605954073 56582042
                1964888854 1868028185 119538455
                2128603757 3025541863 190296071
                0 171803249 247326358
                981208417 1018163709 584290960
                3339179456 2803757428 4300751
                3072231669 789607411 154371749
                685126196 2025133391 41962236
                852738626 2067095627 8823471
                1698622343 943979160 74184549
                1772806892 3215837934 192081962
                3694231391 3749473614 163466351
                3258394158 1602454669 49282352
                1571551047 662536115 127071296
                4071951412 2366487874 223015884
                2318899828 3407919896 207231442
                2813165839 2310334853 24650075
                877578219 3631167460 103630198
                2837815914 2189746697 120588156
                727088432 517870630 88083443
                861562097 3615151338 16016122
                3307676510 2334984928 31502946
                3226603418 3912939965 31790740

                water-to-light map:
                643811272 144875370 639662384
                3197353303 4064611747 230355549
                2428135249 2178155112 85507014
                2589388740 2163836443 14318669
                2641064681 3116679588 24929315
                1947728375 0 54427824
                2665993996 3141608903 36865900
                1660679363 2591737738 11969671
                3807776623 3220207966 248700485
                347509555 54427824 90447546
                0 2336205674 208134837
                3722035899 3726277139 85740724
                2002156199 1341605129 169698758
                4056477108 3563548228 162728911
                208134837 1511303887 139374718
                2513642263 1710884259 75746477
                2702859896 3468908451 18878500
                3427708852 3812017863 252593884
                1283473656 1786630736 377205707
                2736208594 2641064681 461144709
                2238047311 784537754 190087938
                1720046261 974625692 167476460
                1672649034 2544340511 47397227
                4219206019 3487786951 75761277
                1887522721 1650678605 60205654
                3680302736 3178474803 41733163
                510500649 1208294506 133310623
                437957101 2263662126 72543548
                2721738396 3102209390 14470198
                2171854957 1142102152 66192354

                light-to-temperature map:
                523464332 2485592576 7655734
                1306479498 3401177698 44583163
                72682795 2493248310 40062854
                1351062661 10924544 136936144
                1950759356 374811960 103408928
                1628354042 1079719374 5733623
                4278168745 4150271912 16798551
                1634087665 867706665 116014557
                112745649 147860688 85469942
                1487998805 1432016565 15767141
                46512214 3323202074 26170581
                198215591 1822839586 82352133
                1503765946 1905191719 124588096
                2320905962 233330630 141481330
                34455944 855650395 12056270
                945527000 2533311164 65423823
                1010950823 1238767929 68991034
                2089724509 1085452997 153314932
                4150271912 4228943070 66024226
                2923124195 1307758963 124257602
                3063630615 2029779815 155675415
                2243039441 3263624618 59076159
                0 2185455230 23531400
                1079941857 1447783706 219106104
                4216296138 4167070463 7178707
                3219306030 2208986630 276605946
                3814380111 3322700777 501297
                23531400 0 10924544
                280567724 2598734987 242896608
                3501210985 2841631595 313169126
                2462387292 478220888 358639145
                2054168284 3349372655 35556225
                1750102222 1666889810 91833237
                1299047961 3451860467 7431537
                3047381797 3384928880 16248818
                3495911976 1758723047 5299009
                2827126043 983721222 95998152
                4223474845 4174249170 54693900
                2302115600 836860033 18790362
                2821026437 3445760861 6099606
                531120066 3459292004 355589404
                1841935459 3154800721 108823897
                886709470 1764022056 58817530

                temperature-to-humidity map:
                2018290756 3339896462 24416138
                2758197106 1920240630 127305443
                1435979012 3212189099 87605129
                1769136092 2998937118 64911939
                3618781885 4020815132 64754456
                2463559257 2553699148 87957011
                3338671715 2945268602 53668516
                1346451950 4085569588 31754584
                2333003205 2227115482 79041240
                3021969750 3172189648 39999451
                3240436817 2192137296 17307942
                1418308768 2209445238 17670244
                357589270 447365191 170229577
                2551516268 2811272034 55795229
                2412044445 3120674836 51514812
                2155360081 4117324172 177643124
                3888375100 1524919566 145384982
                550058871 314116828 30128840
                2885502549 2867067263 78201339
                2963703888 2344871065 24585358
                4157684401 1782957735 137282895
                4033760082 3736328042 120993236
                3257744759 2142598135 45143696
                3392340231 3063849057 56825779
                2042706894 1670304548 112653187
                1523584141 3515198209 20686539
                341412066 830731292 16177204
                580187711 723815388 106915904
                3883979635 2187741831 4395465
                1378206534 3299794228 40102234
                764927838 617594768 20034273
                3449166010 2641656159 169615875
                1544270680 3857321278 163493854
                2988289246 2108917631 33680504
                687103615 645991165 77824223
                1707764534 2047546073 61371558
                527818847 344245668 22240024
                4154753318 2341939982 2931083
                793324235 393780930 53584261
                1834048031 2369456423 184242725
                314116828 366485692 27295238
                784962111 637629041 8362124
                2607311497 3364312600 150885609
                3683536341 3535884748 200443294
                3061969201 1346451950 178467616
                3302888455 2306156722 35783260

                humidity-to-location map:
                2289569155 2295914330 132474891
                3336845004 3857582618 604013
                137201536 465156883 67111840
                0 54072971 10834440
                2613787395 3979038278 315929018
                2929716413 2499280923 31840158
                2492935748 3858186631 120851647
                3337449017 2531121081 957518279
                1119175103 926319928 393815858
                220477639 0 54072971
                2975037529 1934106855 18186723
                2422044046 2428389221 70891702
                1934106855 3488639360 355462300
                867655257 658635819 251519846
                467405785 64907411 400249472
                204313376 910155665 16164263
                274550610 1320135786 192855175
                10834440 532268723 126367096
                2993224252 1952293578 343620752
                2961556571 3844101660 13480958
                        """;
    }

    private String getInput2() {
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
