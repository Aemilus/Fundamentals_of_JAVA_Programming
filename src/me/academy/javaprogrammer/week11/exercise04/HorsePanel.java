package me.academy.javaprogrammer.week11.exercise04;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class HorsePanel extends JPanel implements Runnable {
    private final List<HorseListener> horseListeners = new ArrayList<>();

    private final Horse horse;
    private final JLabel horseLabel = new JLabel();
    private final JProgressBar horseProgressBar = new JProgressBar(0, 100);

    Horse getHorse() {
        return horse;
    }

    JProgressBar getHorseProgressBar() {
        return horseProgressBar;
    }

    HorsePanel(Horse horse) {
        this.horse = horse;
        initComponents();
        initPanel();
    }

    private void initComponents() {
        horseLabel.setText(horse.getName());
        horseProgressBar.setStringPainted(true);
    }

    private void initPanel() {
        setName("Panel " + horse.getName());
        setLayout(new BorderLayout(10, 10));
        add(horseLabel, BorderLayout.WEST);
        add(horseProgressBar, BorderLayout.CENTER);
    }

    void addHorseListener(HorseListener hl) {
        horseListeners.add(hl);
    }

    void removeHorseListener(HorseListener hl) {
        horseListeners.remove(hl);
    }

    @Override
    public void run() {
        // notify listeners that this horse has start has started
        notifyHorseListeners(HorseEventType.START_RACE);
        // the horse runs until it reaches the finish line
        while (horseProgressBar.getValue() < horseProgressBar.getMaximum()) {
            try {
                Thread.sleep(horse.race());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // horse has traveled 1% of track in this period
            horseProgressBar.setValue(horse.getPercentageOfRaceTrackTraveled());
            // update standings table
            notifyHorseListeners(HorseEventType.RACE_PROGRESS);
        }
        // notify listeners that horse has reached finish line
        notifyHorseListeners(HorseEventType.FINISH_RACE);
    }

    private synchronized void notifyHorseListeners(HorseEventType het) {
        System.out.println(Thread.currentThread().getName() + " notifying horse listeners of " + het.toString() + " event");
        switch (het) {
            case START_RACE:
                horseListeners.forEach(hl -> hl.raceStarted(new HorseEvent(this)));
                break;
            case RACE_PROGRESS:
                horseListeners.forEach(hl -> hl.raceProgress(new HorseEvent(this)));
                break;
            case FINISH_RACE:
                horseListeners.forEach(hl -> hl.raceFinished(new HorseEvent(this)));
                break;
        }
    }
}
