package me.academy.javaprogrammer.week10.exercise01;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

final class ListFiles {
    static List<Path> getListOfPaths(JFrame frame, Path dir, String fileExtension) {
        List<Path> list = new ArrayList<>();
        String globbing = "*." + fileExtension;
        try {
            Files.newDirectoryStream(dir, globbing).forEach(p->list.add(p));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return list;
    }
}
