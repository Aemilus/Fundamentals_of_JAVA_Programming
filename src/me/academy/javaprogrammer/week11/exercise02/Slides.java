package me.academy.javaprogrammer.week11.exercise02;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

final class Slides {
    // a list of images initialized in the static block
    private static final List<ImageIcon> SLIDES = new ArrayList<>();

    static {
        try {
            // images are placed in img directory
            Path imagesDir = Paths.get(Slides.class.getResource("img").toURI());
            // for each image in the images directory add it to the list
            Files.newDirectoryStream(imagesDir, "*.jpg").forEach(imgPath -> {
                try {
                    ImageIcon img = getImageIcon(imgPath);
                    SLIDES.add(img);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    static private ImageIcon getImageIcon(Path imgPath) throws MalformedURLException {
        URL imgURL = imgPath.toUri().toURL();
        ImageIcon icon = new ImageIcon(imgURL);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(450,350,Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    static List<ImageIcon> getList() {
        if (SLIDES.isEmpty()) throw new IllegalArgumentException("No slides available.");
        return SLIDES;
    }
}
