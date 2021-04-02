package me.academy.javaprogrammer.finalproject.agenda.gui.splashscreen;

import me.academy.javaprogrammer.finalproject.agenda.gui.menu.AboutPanel;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AgendaSplashScreen extends JWindow implements Runnable {
    private final JPanel logoPanel = new JPanel();
    private final JPanel rootPanel = new JPanel();
    private final JLabel logoImageLabel = new JLabel();
    private final JPanel applicationPanel = new AboutPanel();
    private final JProgressBar loadingProgressBar = new JProgressBar(0, 100);

    public AgendaSplashScreen() {
        initRootPanel();
        initWindow();
        pack();
        setLocationRelativeTo(null);
    }

    private void initWindow() {
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(500, 210));
        add(rootPanel, BorderLayout.CENTER);
        initLogoImagePanel();
    }

    private void initRootPanel() {
        loadingProgressBar.setStringPainted(true);
        loadingProgressBar.setForeground(Color.BLACK);
        loadingProgressBar.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        rootPanel.setLayout(new BorderLayout(10, 10));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 30));
        rootPanel.add(applicationPanel, BorderLayout.NORTH);
        rootPanel.add(loadingProgressBar, BorderLayout.CENTER);
    }

    private void initLogoImagePanel() {
        try {
            // get the logo image from disk
            Path imgDir = Paths.get(AgendaSplashScreen.class.getResource("img").toURI());
            Path imgFile = imgDir.resolve("logo.gif");
            ImageIcon logoImage = new ImageIcon(imgFile.toUri().toURL());
            // set image on the label component
            logoImageLabel.setIcon(logoImage);
            logoPanel.setLayout(new BorderLayout(10, 10));
            logoPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 0));
            logoPanel.add(logoImageLabel, BorderLayout.CENTER);
            // add the logo panel to splash screen window
            add(logoPanel, BorderLayout.WEST);
        } catch (URISyntaxException | MalformedURLException e) {
            // if the logo image is unavailable then we ignore the exceptions and no logo will be displayed on splash screen
        }
    }

    @Override
    public void run() {
        setVisible(true);
        int loadingProgress = 0;
        while (true) {
            try {
                loadingProgressBar.setValue(loadingProgress++);
                Thread.sleep(40);
                if (loadingProgress > 100) break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setVisible(false);
        dispose();
    }
}
