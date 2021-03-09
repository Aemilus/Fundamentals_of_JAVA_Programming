package me.academy.javaprogrammer.week11.exercise03;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class HorseRacingFrame extends JFrame {
    private final int raceSize;
    private int countClosedThreads;

    private final JPanel rootPanel = new JPanel();
    private final JButton startRaceButton = new JButton("Start horse race!");
    private final List<HorsePanel> horsesList = new ArrayList<>();

    HorseRacingFrame(int raceSize) {
        super("Horse Racing");
        this.raceSize = raceSize;
        initFrame();
        initComponents();
        initListeners();
        pack();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setPreferredSize(new Dimension(500, 60 * raceSize)); // height is 60 pixel per component
        setLayout(new BorderLayout());
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        rootPanel.setLayout(new GridLayout(raceSize + 1, 1, 10, 10));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        for(int i = 0; i < raceSize; i++) {
            HorsePanel horsePanel = new HorsePanel(this, "Horse" + (i + 1));
            horsesList.add(horsePanel);
            rootPanel.add(horsesList.get(i));
        }

        rootPanel.add(startRaceButton);
    }

    private void initListeners() {
        startRaceButton.addActionListener(e -> {
            startRaceButton.setEnabled(false);
            countClosedThreads = 0;
            horsesList.stream().forEach(horse -> horse.getHorseProgressBar().setValue(0));
            horsesList.parallelStream().forEach(horse -> {
                Thread runningHorseThread = new Thread(horse, horse.getName());
                runningHorseThread.start();
            });
        });
    }

    public static void main(String[] args) {
        HorseRacingFrame hrf = new HorseRacingFrame(4);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                hrf.setVisible(true);
            }
        });
    }

    JButton getStartRaceButton() {
        return startRaceButton;
    }

    int getRaceSize() {
        return raceSize;
    }

    int getCountClosedThreads() {
        return countClosedThreads;
    }

    void incrementClosedThreads() {
        countClosedThreads++;
    }
}
