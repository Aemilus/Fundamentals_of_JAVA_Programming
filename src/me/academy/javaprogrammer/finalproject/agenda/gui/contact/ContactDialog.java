package me.academy.javaprogrammer.finalproject.agenda.gui.contact;

import javax.swing.*;
import java.awt.*;

public abstract class ContactDialog extends JDialog {
    private final ContactPanel contactPanel = new ContactPanel();

    public ContactDialog(Frame owner, String title) {
        super(owner, title, true);
        initDialog();
        initListeners();
        pack();
        setLocationRelativeTo(null);
    }

    private void initDialog() {
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(300, 450));
        setResizable(false);
        add(contactPanel, BorderLayout.CENTER);
    }

    public final ContactPanel getContactPanel() {
        return contactPanel;
    }

    public abstract void initListeners();
}
