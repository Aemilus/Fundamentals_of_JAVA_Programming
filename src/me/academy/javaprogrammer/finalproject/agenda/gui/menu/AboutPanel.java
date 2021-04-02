package me.academy.javaprogrammer.finalproject.agenda.gui.menu;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {
    private final JLabel applicationNameLabel = new JLabel("Agenda");
    private final JLabel applicationAuthorLabel = new JLabel("by github.com/aemilus");
    private final JLabel applicationVersionLabel = new JLabel("v1.0");

    public AboutPanel() {
        initComponents();
        initPanel();
    }

    private void initPanel() {
        setLayout(new GridLayout(3,1));
        add(applicationNameLabel);
        add(applicationVersionLabel);
        add(applicationAuthorLabel);
    }

    private void initComponents() {
        applicationNameLabel.setFont(new Font(applicationNameLabel.getFont().toString(), Font.BOLD, 28));
        applicationVersionLabel.setFont(new Font(applicationVersionLabel.getFont().toString(), Font.BOLD, 16));
    }
}
