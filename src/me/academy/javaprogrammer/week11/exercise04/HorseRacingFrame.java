package me.academy.javaprogrammer.week11.exercise04;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

final class HorseRacingFrame extends JFrame implements HorseListener {
    private final int horseRaceSize;
    private final HorseRace horseRace;
    private Horse betHorse;

    private final JPanel rootPanel = new JPanel();
    private final HorseRacePanel horseRacePanel;
    private final HorseRaceStandingsPanel horseRaceStandingsPanel;

    HorseRacingFrame(int horseRaceSize) {
        super("Horse Racing");
        this.horseRaceSize = horseRaceSize;
        this.horseRace = new HorseRace(horseRaceSize);
        this.horseRacePanel = new HorseRacePanel(horseRace, this);
        this.horseRaceStandingsPanel = new HorseRaceStandingsPanel(horseRace.getHorseRaceStandings());

        initComponents();
        initFrame();
        pack();
        setLocationRelativeTo(null);
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 350));
        setLayout(new BorderLayout());
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        rootPanel.setLayout(new GridLayout(1,2));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        rootPanel.add(horseRacePanel);
        rootPanel.add(horseRaceStandingsPanel);
    }

    void raceReset() {
        horseRaceStandingsPanel.setBetResult("You bet on " + betHorse.getName());
    }

    @Override
    public synchronized void raceStarted(HorseEvent he) {
        // while the horse is racing the progress bar is red
        he.getSource().getHorseProgressBar().setForeground(Color.RED);
    }

    @Override
    public synchronized void raceProgress(HorseEvent he) {
        System.out.println(Thread.currentThread().getName() + " updating race progress");
        horseRace.updateHorseRaceStandings();
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    horseRaceStandingsPanel.refreshTableModel();
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " updated race progress");
    }

    @Override
    public synchronized void raceFinished(HorseEvent he) {
        System.out.println(Thread.currentThread().getName() + " has reached finish line");
        // when the horse has reached finish line the progress bar is set to blue
        he.getSource().getHorseProgressBar().setForeground(Color.BLUE);
        horseRacePanel.incrementClosedThreadsCount();

        // verify if all horses have reached the finish line
        // next lines of code after this if are executed only if all horses have reached the finish line
        if (horseRacePanel.getClosedThreadsCount() != horseRaceSize) return;

        // verify if the horse on which the bet was placed is winner or not
        if (horseRace.getHorseRaceStandings().get(0).equals(betHorse)) {
            horseRaceStandingsPanel.setBetResult("WINNER");
        } else {
            horseRaceStandingsPanel.setBetResult("LOSE");
        }

        // Start Race button is re-enabled
        horseRacePanel.getStartRaceButton().setEnabled(true);
    }

    private void showHorseBettingDialog() {
        HorseBettingDialog hbd = new HorseBettingDialog(this, horseRace.getHorseRaceLineup());
        hbd.setVisible(true);
    }

    Horse getBetHorse() {
        return betHorse;
    }

    void setBetHorse(Horse betHorse) {
        this.betHorse = betHorse;
    }

    public static void main(String[] args) {
        HorseRacingFrame hrf = new HorseRacingFrame(4);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // display dialog box for user to select a horse to bet on
                hrf.showHorseBettingDialog();
                // if dialog is closed and user didn't select any horse to bet on then exit application with status 2
                if (hrf.getBetHorse() == null) System.exit(2);
                // display the frame where the horse race will take place
                hrf.setVisible(true);
            }
        });
    }
}
