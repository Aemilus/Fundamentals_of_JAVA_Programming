package me.academy.javaprogrammer.finalproject.agenda.gui.slideshow;

import me.academy.javaprogrammer.finalproject.AgendaFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public final class SlideShowPanel extends JPanel implements Runnable {
    private final Frame agendaFrame;
    private final JLabel screenLabel = new JLabel();
    private boolean threadEnabled = true;
    private Slides slides;
    private Dimension slideDimension;

    public SlideShowPanel(Frame agendaFrame) {
        this.agendaFrame = agendaFrame;
        initScreenLabel();
        initSlides();
        initPanel();
        startSlideShowThread();
    }

    private void initPanel() {
        setLayout(new BorderLayout(10, 10));
        add(screenLabel, BorderLayout.CENTER);
    }

    private void initScreenLabel() {
        slideDimension = new Dimension(AgendaFrame.FRAME_WIDTH, AgendaFrame.FRAME_HEIGHT / 2);
        screenLabel.setPreferredSize(slideDimension);
    }

    private void initSlides() {
        try {
            slides = new Slides(slideDimension);
        } catch (IOException | URISyntaxException e) {
            JOptionPane.showMessageDialog(agendaFrame, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startSlideShowThread() {
        Thread slideShowThread = new Thread(this);
        slideShowThread.start();
    }

    @Override
    public void run() {
        int index = 0;
        while (threadEnabled) {
            ImageIcon img = slides.get(index++);
            index %= slides.size();
            screenLabel.setIcon(img);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        slides = null;
    }

    public void stopSlideShowThread() {
        threadEnabled = false;
    }
}
