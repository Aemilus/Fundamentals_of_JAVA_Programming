package me.academy.javaprogrammer.finalproject.agenda.gui.menu;

import me.academy.javaprogrammer.finalproject.AgendaFrame;

import javax.swing.*;

public final class AgendaMenuBar extends JMenuBar {
    private final FileMenu fileMenu;
    private final HelpMenu helpMenu;

    public AgendaMenuBar(AgendaFrame agendaFrame) {
        fileMenu = new FileMenu(agendaFrame);
        helpMenu = new HelpMenu(agendaFrame);
        initMenuBar();
    }

    private void initMenuBar() {
        add(fileMenu);
        add(helpMenu);
    }

    public FileMenu getFileMenu() {
        return fileMenu;
    }
}
