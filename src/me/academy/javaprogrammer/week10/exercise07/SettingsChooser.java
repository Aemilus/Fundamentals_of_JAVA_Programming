package me.academy.javaprogrammer.week10.exercise07;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.prefs.Preferences;

final class SettingsChooser {
    // references a Preferences object for current user and this package
    static final Preferences PREFERENCES = Preferences.userNodeForPackage(CustomFrame.class);
    // key used to save the last directory where user navigated with FILE_CHOOSER
    static final String LAST_DIR_KEY = "LAST_DIR";
    // initialize FILE_CHOOSER current directory to getLastDir() returned value
    static final JFileChooser FILE_CHOOSER = new JFileChooser(getLastDir());

    static {
        // user should select only one TXT file in FILE_CHOOSER
        FILE_CHOOSER.setMultiSelectionEnabled(false);
        FILE_CHOOSER.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FILE_CHOOSER.setFileFilter(new FileNameExtensionFilter("TXT files", "txt"));
    }

    // get the last directory from registry via Preferences API
    // return null if no value associated with the key
    private static File getLastDir() {
        String lastDir = PREFERENCES.get(LAST_DIR_KEY, null);
        return lastDir == null ? null : new File(lastDir);
    }
}
