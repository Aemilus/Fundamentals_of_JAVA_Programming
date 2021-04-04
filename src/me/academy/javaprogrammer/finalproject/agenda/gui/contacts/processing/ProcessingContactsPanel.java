package me.academy.javaprogrammer.finalproject.agenda.gui.contacts.processing;

import me.academy.javaprogrammer.finalproject.agenda.gui.contacts.processing.filter.FilterContactsPanel;
import me.academy.javaprogrammer.finalproject.agenda.gui.contacts.processing.sort.SortContactsPanel;

import javax.swing.*;
import java.awt.*;

public class ProcessingContactsPanel extends JPanel {
    private final FilterContactsPanel filterContactsPanel = new FilterContactsPanel();
    private final SortContactsPanel sortContactsPanel = new SortContactsPanel();

    public ProcessingContactsPanel() {
        setLayout(new GridLayout(2, 1, 10, 10));
        add(filterContactsPanel, BorderLayout.NORTH);
        add(sortContactsPanel, BorderLayout.SOUTH);
    }

    public FilterContactsPanel getFilterContactsPanel() {
        return filterContactsPanel;
    }

    public SortContactsPanel getSortContactsPanel() {
        return sortContactsPanel;
    }
}
