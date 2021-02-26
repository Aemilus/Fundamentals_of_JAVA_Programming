package me.academy.javaprogrammer.week10.exercise03;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

final class ListPictures {
    private static ArrayList<Path> getListOfPicturesPaths(JFrame frame, Path dir) {
        ArrayList<Path> list = new ArrayList<>();
        try {
            Files.newDirectoryStream(dir, "*.jpg").forEach(p->list.add(p));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

    static ArrayList<ImageIcon> getListOfPictures(JFrame frame, Path dir) {
        ArrayList<Path> picturePaths = getListOfPicturesPaths(frame, dir);
        ArrayList<ImageIcon> imgList = new ArrayList<>();
        picturePaths.stream().forEach(p -> {
            try {
                ImageIcon img = new ImageIcon(p.toUri().toURL());
                imgList.add(img);
            } catch (MalformedURLException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return imgList;
    }
}
