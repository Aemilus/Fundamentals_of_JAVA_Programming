package me.academy.javaprogrammer.week10.exercise03;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

public final class ListPicturesFrame extends JFrame {
    private final JPanel rootPanel = new JPanel();

    private final JLabel pictureLabel = new JLabel();
    private ArrayList<ImageIcon> picturesList;
    private int indexPicture;

    private final JPanel buttonsPanel = new JPanel();
    private final JButton prevButton = new JButton("Previous");
    private final JButton nextButton = new JButton("Next");

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenuItem browseMenuItem = new JMenuItem("Browse");
    private final JMenu fileMenu = new JMenu("File");

    private final JFileChooser directoryChooser = new JFileChooser();
    private Path selectedDirectory;

    ListPicturesFrame() {
        super("List Pictures");
        initFrame();
        initComponents();
        initListeners();
        pack();
    }

    private void initFrame() {
        setPreferredSize(new Dimension(500, 400));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setLayout(new BorderLayout());
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        initMenuBar();
        initRootPanel();
        initButtonsPanel();
    }

    private void initMenuBar() {
        fileMenu.add(browseMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void initRootPanel() {
        rootPanel.setLayout(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createTitledBorder(""));
        rootPanel.add(pictureLabel, BorderLayout.CENTER);
        rootPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void initButtonsPanel() {
        buttonsPanel.setLayout(new GridLayout(1,2, 5, 5));
        buttonsPanel.add(prevButton);
        buttonsPanel.add(nextButton);
    }

    private void initListeners() {
        // user can select only directories
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        browseMenuItem.addActionListener(e -> {
            // open a dialog box to chose a directory
            int option = directoryChooser.showOpenDialog(this);
            // exit if nothing selected
            if (option != JFileChooser.APPROVE_OPTION) return;
            // get selected directory
            selectedDirectory = directoryChooser.getSelectedFile().toPath();
            // populate a list with all pictures found in selected directory
            picturesList = ListPictures.getListOfPictures(this, selectedDirectory);
            // do nothing if the list is empty
            if (picturesList.isEmpty()) return;
            // disable prevButton
            prevButton.setEnabled(false);
            // nextButton is enabled only if there are more than one picture in the list
            nextButton.setEnabled(picturesList.size() > 1);
            // display first picture in the list
            indexPicture = 0;
            pictureLabel.setIcon(picturesList.get(indexPicture));
        });

        // prevButton is disabled at startup
        prevButton.setEnabled(false);
        prevButton.addActionListener(e -> {
            // display the picture placed before current displayed picture in the list
            pictureLabel.setIcon(picturesList.get(--indexPicture));
            // enable/disable the navigation buttons
            updateNavigationButtons();
        });

        // nextButton is disabled at startup
        nextButton.setEnabled(false);
        nextButton.addActionListener(e -> {
            // display the picture placed after current displayed picture in the list
            pictureLabel.setIcon(picturesList.get(++indexPicture));
            // enable/disable the navigation buttons
            updateNavigationButtons();
        });
    }

    private void updateNavigationButtons() {
        // prevButton is disabled if we reached the start of the list
        prevButton.setEnabled(indexPicture > 0);
        // nextButton is disabled if we reached the end of the list
        nextButton.setEnabled(indexPicture + 1 < picturesList.size());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListPicturesFrame lpf = new ListPicturesFrame();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lpf.setVisible(true);
            }
        });
    }
}
