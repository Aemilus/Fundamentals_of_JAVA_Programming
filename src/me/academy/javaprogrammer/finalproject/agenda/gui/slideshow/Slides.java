package me.academy.javaprogrammer.finalproject.agenda.gui.slideshow;

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

public class Slides extends ArrayList<ImageIcon> {
    private final Dimension imgDimension;

    public Slides(Dimension imgDimension) throws URISyntaxException, IOException {
        this.imgDimension = imgDimension;

        Path imagesDir = Paths.get(Slides.class.getResource("img").toURI());
        Files.newDirectoryStream(imagesDir, "*.jpg").forEach(imgPath -> {
            try {
                ImageIcon img = getImageIcon(imgPath);
                this.add(img);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    private ImageIcon getImageIcon(Path imgPath) throws MalformedURLException {
        URL imgURL = imgPath.toUri().toURL();
        ImageIcon icon = new ImageIcon(imgURL);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance((int)imgDimension.getWidth(), (int)imgDimension.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}
