package me.academy.javaprogrammer.week11.exercise01;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;

public class SlideShowFrame extends JFrame {
    private final JPanel rootPanel = new JPanel();

    private final JLabel displayLabel = new JLabel();
    private final List<ImageIcon> slides = Slides.getList();
    private Timer slideShowTimer;

    private final JPanel buttonsPanel = new JPanel();
    private final JButton startButton = new JButton("Start");
    private final JButton stopButton = new JButton("Stop");

    public SlideShowFrame() {
        super("Slide Show");
        initFrame();
        initComponents();
        initListeners();
        pack();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setPreferredSize(new Dimension(450, 350));
        setResizable(false);
        setLayout(new BorderLayout());
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        initButtonsPanel();
        initRootPanel();
    }

    private void initButtonsPanel() {
        buttonsPanel.setLayout(new GridLayout(1,2));
        buttonsPanel.add(startButton);
        buttonsPanel.add(stopButton);
    }

    private void initRootPanel() {
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(displayLabel, BorderLayout.CENTER);
        rootPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void initListeners() {
        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            SlideShowTimerTask slideShowTimerTask = new SlideShowTimerTask(displayLabel, slides);
            slideShowTimer = new Timer("SlideShowThread", true);
            slideShowTimer.scheduleAtFixedRate(slideShowTimerTask, 100, 2000);
            stopButton.setEnabled(true);
        });

        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> {
            stopButton.setEnabled(false);
            slideShowTimer.cancel();
            slideShowTimer.purge();
            startButton.setEnabled(true);
        });
    }

    public static void main(String[] args) {
        SlideShowFrame ssf = new SlideShowFrame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ssf.setVisible(true);
            }
        });
    }
}
