package me.academy.javaprogrammer.week10.exercise07;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.TreeMap;

import static me.academy.javaprogrammer.week10.exercise07.SettingsChooser.*;

final class ImportSettings {

    static TreeMap<String, String> getSettings() throws IOException {
        // create a tree with settings set to default
        TreeMap<String, String> settingsTree = getDefaultSettings();

        // open dialog box to select the file storing the settings
        int option = FILE_CHOOSER.showOpenDialog(null);
        // save the last directory where user navigated
        PREFERENCES.put(LAST_DIR_KEY, FILE_CHOOSER.getCurrentDirectory().getAbsolutePath());
        // if no file selected then return the default settings
        if (option != JFileChooser.APPROVE_OPTION ) return settingsTree;
        // parse settings file and update settings tree
        Files.lines(FILE_CHOOSER.getSelectedFile().toPath())
                .filter(line -> line.contains("=") && line.trim().length() > 2)
                .forEach(line -> {
                    int indexOfEqualSign = line.indexOf("=");
                    String key = line.substring(0, indexOfEqualSign).trim();
                    String value = line.substring(indexOfEqualSign + 1).trim();
                    settingsTree.put(key, value);
                });
        // return setting tree with updated values read from settings file
        return settingsTree;
    }

    private static TreeMap<String, String> getDefaultSettings() {
        TreeMap<String, String> settingsTree = new TreeMap<>();
        settingsTree.put("frameTitle", "Default Title");
        settingsTree.put("locationX", "400");
        settingsTree.put("locationY", "200");
        settingsTree.put("frameWidth", "500");
        settingsTree.put("frameHeight", "300");
        settingsTree.put("buttonText", "Default Button Text");
        settingsTree.put("hiddenMessage", "Default secret.");
        return settingsTree;
    }
}
