package me.academy.javaprogrammer.week10.exercise07;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

import static me.academy.javaprogrammer.week10.exercise07.SettingsChooser.*;

final class ExportSettings {
    static void exportSettings(JFrame frame, TreeMap<String, String> settingsTree) throws IOException {
        // open save dialog box and select where file si to be saved
        int option = SettingsChooser.FILE_CHOOSER.showSaveDialog(frame);
        // save the last directory where user navigated
        PREFERENCES.put(LAST_DIR_KEY, FILE_CHOOSER.getCurrentDirectory().getAbsolutePath());
        // if the dialog box is closed then do nothing; current settings are not exported
        if (option != JFileChooser.APPROVE_OPTION) return;
        // export settings
        FileOutputStream fos = new FileOutputStream(FILE_CHOOSER.getSelectedFile());
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        settingsTree.forEach((k,v) -> {
            try {
                osw.write(k.concat("=").concat(v));
                osw.write(System.lineSeparator());
            } catch (IOException ex) {
                DisplayError.showError(frame, ex);
            }
        });
        // export completed; closing streams
        osw.close();
        fos.close();
    }
}
