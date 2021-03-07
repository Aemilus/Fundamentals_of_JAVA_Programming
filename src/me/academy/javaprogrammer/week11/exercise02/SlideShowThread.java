package me.academy.javaprogrammer.week11.exercise02;

import javax.swing.*;
import java.util.List;

public class SlideShowThread extends Thread {
    private final List<ImageIcon> slides;
    private final JLabel displayLabel;
    private final JLabel statusLabel;
    private int sleep;
    private int index = 0;

    SlideShowThread(List<ImageIcon> slides, JLabel displayLabel, JLabel statusLabel, int sleep) {
        super("SlideShowThread");
        this.slides = slides;
        this.displayLabel = displayLabel;
        this.statusLabel = statusLabel;
        this.sleep = sleep;
    }

    void setSleep(int sleep) {
        this.sleep = sleep;
    }

    @Override
    synchronized public void run() {
        statusLabel.setText(this.getName() + " has started.");
        while (true) {
            ImageIcon img = slides.get(index++);
            index %= slides.size();
            displayLabel.setIcon(img);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException sleepInterruptedException) {
                try {
                    statusLabel.setText(this.getName() + " is pausing.");
                    this.wait();
                    statusLabel.setText(this.getName() + " is resuming.");
                } catch (InterruptedException waitInterruptedException) {
                    statusLabel.setText(this.getName() + " pausing interrupted.");
                    continue;
                }
            }
        }
    }
}
