package me.academy.javaprogrammer.week10.exercise04;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public final class InspectBmpFile {
    private final JFileChooser bmpFileChooser = new JFileChooser();

    InspectBmpFile() {
        initBmpFileChooser();
    }

    private void initBmpFileChooser() {
        // when the JFIleChooser open file dialog box opens the user is able to select only a single bmp file type
        bmpFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        bmpFileChooser.setMultiSelectionEnabled(false);
        bmpFileChooser.setFileFilter(new FileNameExtensionFilter("BMP image files", "bmp"));
    }

    private File getSelectedBmpFile() {
        int option = bmpFileChooser.showOpenDialog(null);
        if (option != JFileChooser.APPROVE_OPTION) throw new NullPointerException("No BMP file was selected");
        return bmpFileChooser.getSelectedFile();
    }

    private BmpFileDimensions getBmpFileDimensions(File bmpFIle) throws IOException {
        FileInputStream fis = new FileInputStream(bmpFIle);
        DataInputStream dis = new DataInputStream(fis);
        dis.skipBytes(18);
        int width  = dis.read() + 256 * dis.read() + 256 * 256 * dis.read() + 256 * 256 * 256 * dis.read();
        int height = dis.read() + 256 * dis.read() + 256 * 256 * dis.read() + 256 * 256 * 256 * dis.read();
        dis.close();
        fis.close();
        return new BmpFileDimensions(width, height);
    }

    public void launch() throws IOException {
        File selectedBmpFile = getSelectedBmpFile();
        BmpFileDimensions selectedBmpFileDimensions = getBmpFileDimensions(selectedBmpFile);
        String message = "Dimensions of selected BMP file are: " + selectedBmpFileDimensions.toString() + ".";
        JOptionPane.showMessageDialog(null, message, "Inspect BMP file", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        InspectBmpFile ibf = new InspectBmpFile();
        try {
            ibf.launch();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
