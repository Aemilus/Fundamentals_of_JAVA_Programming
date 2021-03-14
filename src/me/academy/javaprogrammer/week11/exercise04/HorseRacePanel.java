package me.academy.javaprogrammer.week11.exercise04;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

final class HorseRacePanel extends JPanel {
    private final HorseRace horseRace;
    private final int horseRaceSize;
    private final HorseListener horseListener;

    private final JScrollPane horseRaceScrollPane = new JScrollPane();
    private final JPanel horsesPanel = new JPanel();
    private List<HorsePanel> horsePanels;
    private final JButton startRaceButton = new JButton("Start horse race!");
    private int closedThreadsCount;

    HorseRacePanel(HorseRace horseRace, HorseListener horseListener) {
        this.horseRace = horseRace;
        this.horseRaceSize = horseRace.getHorseRaceLineup().size();
        this.horseListener = horseListener;

        initHorsePanels();
        initHorsesPanel();
        initHorseRaceScrollPane();
        initListeners();
        initComponents();
    }

    private void initHorsePanels() {
        horsePanels = new ArrayList<>(horseRaceSize);
        horseRace.getHorseRaceLineup().forEach(horse -> {
            HorsePanel horsePanel = new HorsePanel(horse);
            horsePanel.addHorseListener(horseListener);
            horsePanels.add(horsePanel);
        });
    }

    private void initHorsesPanel() {
        horsesPanel.setLayout(new GridLayout(horseRaceSize, 1, 10, 10));
        horsesPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        horsePanels.forEach(horsePanel -> horsesPanel.add(horsePanel));
    }

    private void initHorseRaceScrollPane() {
        horseRaceScrollPane.setViewportView(horsesPanel);
        horseRaceScrollPane.setBorder(BorderFactory.createEmptyBorder());
    }

    private void initListeners() {
        startRaceButton.addActionListener(e -> {
            startRaceButton.setEnabled(false);
            ((HorseRacingFrame)horseListener).raceReset();
            closedThreadsCount = 0;
            horsePanels.forEach(horsePanel -> {
                horsePanel.getHorse().moveToStartOfTrack();
                horsePanel.getHorseProgressBar().setValue(horsePanel.getHorse().getPercentageOfRaceTrackTraveled());
            });
            horsePanels.parallelStream().forEach(horsePanel -> {
                Thread horsePanelThread = new Thread(horsePanel, "Thread " + horsePanel.getName());
                horsePanelThread.start();
            });
        });
    }

    private void initComponents() {
        setLayout(new BorderLayout(10,10));
        add(horseRaceScrollPane, BorderLayout.CENTER);
        add(startRaceButton, BorderLayout.SOUTH);
    }

    JButton getStartRaceButton() {
        return startRaceButton;
    }

    int getClosedThreadsCount() {
        return closedThreadsCount;
    }

    void incrementClosedThreadsCount() {
        closedThreadsCount++;
    }
}
