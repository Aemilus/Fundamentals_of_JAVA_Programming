package me.academy.javaprogrammer.week10.exercise05;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class SplitFile {
    private final JFileChooser fileChooser = new JFileChooser();

    SplitFile() {
        initFileChooser();
    }

    private void initFileChooser() {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("TXT files", "txt"));
    }

    public void launch() throws IOException {
        int option = fileChooser.showOpenDialog(null);
        if (option != JFileChooser.APPROVE_OPTION) throw new NullPointerException("No file selected");
        File selectedFile = fileChooser.getSelectedFile();
        split(selectedFile);
    }

    private void split(File inputFile) throws IOException {
        // prepare input stream and keep track of file cursor position;
        // with each byte read from input stream the cursor is incremented
        FileInputStream fis = new FileInputStream(inputFile);
        long cursor = 0;

        // write first half of input file into first output file;
        // close first file output stream when done
        File outputFile1 = new File(inputFile.getPath().concat(".part1"));
        FileOutputStream fos1 = new FileOutputStream(outputFile1);
        while (inputFile.length() / 2 > cursor) {
            fos1.write(fis.read());
            cursor++;
        }
        fos1.close();

        // write the second half of input file into second output file;
        // close second file output stream when done
        File outputFile2 = new File(inputFile.getPath().concat(".part2"));
        FileOutputStream fos2 = new FileOutputStream(outputFile2);
        while (cursor < inputFile.length()) {
            fos2.write(fis.read());
            cursor++;
        }
        fos2.close();

        // close input stream
        fis.close();
    }

    public static void main(String[] args) {
        SplitFile sf = new SplitFile();
        try {
            sf.launch();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
