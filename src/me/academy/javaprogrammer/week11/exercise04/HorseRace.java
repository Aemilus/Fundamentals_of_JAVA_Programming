package me.academy.javaprogrammer.week11.exercise04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class HorseRace {
    /**
     * list of horses at line-up before start of race
     */
    private final List<Horse> horseRaceLineup;

    /**
     * sorted list of horses standings during race
     * - before start of race the horse lineup and horse standings lists are identical
     * - during race, the horse leading in the race becomes the first element in this list
     * - when the race has finished the first element in this list is the winning horse
     */
    private final List<Horse> horseRaceStandings;

    HorseRace(int horseRaceSize) {
        horseRaceLineup = new ArrayList<>(horseRaceSize);
        horseRaceStandings = new ArrayList<>(horseRaceSize);
        // fill the lists
        for(int i = 0; i < horseRaceSize; i++) {
            Horse horse = Horse.getRandomInstance(i);
            horseRaceLineup.add(horse);
            horseRaceStandings.add(horse);
        }
    }

    synchronized void updateHorseRaceStandings() {
        System.out.println(Thread.currentThread().getName() + " sorting horse race standings");
        Collections.sort(horseRaceStandings);
        System.out.println(Thread.currentThread().getName() + " sorted horse race standings");
    }

    List<Horse> getHorseRaceLineup() {
        return horseRaceLineup;
    }

    List<Horse> getHorseRaceStandings() {
        return horseRaceStandings;
    }
}
