package me.academy.javaprogrammer.finalproject.agenda.gui.menu;

import me.academy.javaprogrammer.finalproject.AgendaFrame;
import me.academy.javaprogrammer.finalproject.agenda.gui.slideshow.SlideShowPanel;

import javax.swing.*;

public final class HelpMenu extends JMenu {
    private final AgendaFrame agendaFrame;
    private final AboutPanel aboutPanel = new AboutPanel();

    private final JMenuItem registerMenuItem = new JMenuItem("Register");
    private final JMenuItem aboutMenuItem = new JMenuItem("About");

    public HelpMenu(AgendaFrame agendaFrame) {
        super("Help");
        this.agendaFrame = agendaFrame;
        initMenu();
        initListeners();
    }

    private void initMenu() {
        add(registerMenuItem);
        add(aboutMenuItem);
    }

    private void initListeners() {
        registerMenuItem.addActionListener(event -> {
            // user is prompted to enter a registration code
            String registrationCode = JOptionPane.showInputDialog(
                    agendaFrame,
                    "Enter registration code:",
                    "Register",
                    JOptionPane.INFORMATION_MESSAGE
            );
            // if user pressed cancel then do nothing
            if (registrationCode == null) return;
            // if code is invalid then do nothing
            if (!registrationCode.equals("l3tm31n")) return;
            // if valid then set the application to "full" mode in below 4 steps
            // 1. stop the commercials slide show thread
            SlideShowPanel slideShowPanel = agendaFrame.getSlideShowPanel();
            slideShowPanel.stopSlideShowThread();
            // 2. enable open and save menu items
            FileMenu fileMenu = agendaFrame.getAgendaMenuBar().getFileMenu();
            fileMenu.getOpenMenuItem().setEnabled(true);
            fileMenu.getSaveMenuItem().setEnabled(true);
            // 3. remove the slide show panel and redraw frame
            agendaFrame.getRootPanel().remove(slideShowPanel);
            agendaFrame.pack();
            agendaFrame.repaint();
            // 4. set shareware mode flag to false
            agendaFrame.setAgendaFrameSharewareMode(false);
        });

        aboutMenuItem.addActionListener(event -> {
            JOptionPane.showConfirmDialog(
                    agendaFrame, aboutPanel,
                    "About",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }
}
