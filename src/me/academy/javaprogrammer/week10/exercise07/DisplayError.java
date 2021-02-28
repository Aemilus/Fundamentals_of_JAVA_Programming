package me.academy.javaprogrammer.week10.exercise07;

import javax.swing.*;

final class DisplayError {
    static void showError(JFrame frame, Exception ex) {
        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
