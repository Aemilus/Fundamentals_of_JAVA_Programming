package me.academy.javaprogrammer.week11.exercise02;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SlideShowFrame extends JFrame {
    private final JPanel rootPanel = new JPanel();

    private final JLabel displayLabel = new JLabel();
    private final List<ImageIcon> slides = Slides.getList();
    private SlideShowThread slideShowThread;

    private final JPanel buttonsPanel = new JPanel();
    private final JButton startButton = new JButton("Start");
    private final SpinnerNumberModel frameRateSpinnerModel = new SpinnerNumberModel(500, 100, 3000, 100);
    private final JSpinner frameRateSpinner = new JSpinner(frameRateSpinnerModel);
    private final JButton stopButton = new JButton("Stop");

    private final JLabel statusLabel = new JLabel("Press Start. Status of slide-show thread will be displayed here.");

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
        buttonsPanel.setLayout(new GridLayout(1,3));
        buttonsPanel.add(startButton);
        buttonsPanel.add(frameRateSpinner);
        buttonsPanel.add(stopButton);
    }

    private void initRootPanel() {
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(displayLabel, BorderLayout.CENTER);
        rootPanel.add(buttonsPanel, BorderLayout.SOUTH);
        rootPanel.add(statusLabel, BorderLayout.NORTH);
    }

    private void initListeners() {
        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            if (slideShowThread != null) {
                synchronized (slideShowThread) {
                    // if below line is uncommented then waitInterruptedException is thrown
                    // this causes slide-show thread to not resume from where was put on hold but to start from next loop iteration instead
//                    slideShowThread.interrupt();
                    slideShowThread.notify();
                }
                return;
            }

            slideShowThread = new SlideShowThread(slides, displayLabel, statusLabel, (Integer)(frameRateSpinnerModel.getValue()));
            slideShowThread.start();
        });

        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> {
            stopButton.setEnabled(false);
            // sleepInterruptedException is thrown
            slideShowThread.interrupt();
            startButton.setEnabled(true);
        });

        frameRateSpinner.addChangeListener(e -> {
            slideShowThread.setSleep((Integer)(frameRateSpinnerModel.getValue()));
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
