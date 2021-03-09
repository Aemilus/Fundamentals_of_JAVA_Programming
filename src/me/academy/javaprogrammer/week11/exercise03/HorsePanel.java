package me.academy.javaprogrammer.week11.exercise03;

import javax.swing.*;
import java.awt.*;

final class HorsePanel extends JPanel implements Runnable {
    private final HorseRacingFrame horseRacingFrame;

    private final JLabel horseNameLabel;
    private final JProgressBar horseProgressBar = new JProgressBar(0, 100);

    HorsePanel(HorseRacingFrame hrf, String horseName) {
        setName(horseName);
        setLayout(new BorderLayout(10, 10));
        this.horseNameLabel = new JLabel(getName());
        this.horseRacingFrame = hrf;
        add(horseNameLabel, BorderLayout.WEST);
        add(horseProgressBar, BorderLayout.CENTER);
        horseProgressBar.setStringPainted(true);
    }

    JProgressBar getHorseProgressBar() {
        return horseProgressBar;
    }

    @Override
    public void run() {
        horseProgressBar.setForeground(Color.RED);
        // the horse runs until it reaches the finish line
        while (horseProgressBar.getValue() < horseProgressBar.getMaximum()) {
            try {
                Thread.sleep((int)(Math.random() * 200 + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            horseProgressBar.setValue(horseProgressBar.getValue() + 1);
        }
        // the horse has reached finish line
        horseProgressBar.setForeground(Color.BLUE);
        horseRacingFrame.incrementClosedThreads();
        // if this is the last horse in the race and has reached finish line then
        if (horseRacingFrame.getCountClosedThreads() == horseRacingFrame.getRaceSize()) {
            horseRacingFrame.getStartRaceButton().setEnabled(true);
        }
    }
}
