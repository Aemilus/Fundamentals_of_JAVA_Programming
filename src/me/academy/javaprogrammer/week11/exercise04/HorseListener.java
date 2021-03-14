package me.academy.javaprogrammer.week11.exercise04;

import java.util.EventListener;

interface HorseListener extends EventListener {
    void raceStarted(HorseEvent he);
    void raceProgress(HorseEvent he);
    void raceFinished(HorseEvent he);
}
