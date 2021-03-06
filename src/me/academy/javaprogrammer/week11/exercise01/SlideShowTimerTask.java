package me.academy.javaprogrammer.week11.exercise01;

import javax.swing.*;
import java.util.List;
import java.util.TimerTask;

final class SlideShowTimerTask extends TimerTask {
    private final JLabel displayLabel;
    private final List<ImageIcon> slides;
    private int index = 0;

    SlideShowTimerTask(JLabel displayLabel, List<ImageIcon> list) {
        this.displayLabel = displayLabel;
        this.slides = list;
    }

    @Override
    public void run() {
        ImageIcon img = slides.get(index++);
        index %= slides.size();
        displayLabel.setIcon(img);
    }
}
