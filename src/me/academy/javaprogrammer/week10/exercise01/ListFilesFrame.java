package me.academy.javaprogrammer.week10.exercise01;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class ListFilesFrame extends JFrame {
    private final JPanel rootPanel = new JPanel();

    private final JPanel inputPanel = new JPanel();
    private final JPanel fileExtensionPanel = new JPanel();
    private final JTextField fileExtensionTextField = new JTextField();
    private final JPanel directoryPanel = new JPanel();
    private final JTextField directoryTextField = new JTextField();

    private final JPanel buttonsPanel = new JPanel();
    private final JButton browseButton = new JButton("Browse");
    private final JButton listButton = new JButton("List files");

//    private final JPanel listPanel = new JPanel();
    private final JScrollPane listPanel = new JScrollPane();
    private final DefaultListModel<Path> pathsListModel = new DefaultListModel<>();
    private final JList<Path> pathsList = new JList<>(pathsListModel);

    private final JFileChooser choseDirectory = new JFileChooser();
    private File selectedDirectory;

    public ListFilesFrame() {
        super("List Files");
        initFrame();
        initComponents();
        initListeners();
        pack();
    }

    private void initFrame() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));
        setResizable(false);
        setLocationByPlatform(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initRootPanel() {
        rootPanel.setLayout(new GridLayout(2,1));
        rootPanel.add(inputPanel);
        rootPanel.add(listPanel);
    }

    private void initComponents() {
        initInputPanel();
        initListPanel();
        initRootPanel();
    }

    private void initInputPanel() {
        initFileExtensionPanel();
        initDirectoryPanel();
        initButtonsPanel();

        inputPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(fileExtensionPanel);
        inputPanel.add(directoryPanel);
        inputPanel.add(buttonsPanel);
    }

    private void initFileExtensionPanel() {
        fileExtensionPanel.setBorder(BorderFactory.createTitledBorder("File extension"));
        fileExtensionPanel.setLayout(new BorderLayout());
        fileExtensionPanel.add(fileExtensionTextField, BorderLayout.CENTER);
    }

    private void initDirectoryPanel() {
        directoryPanel.setBorder(BorderFactory.createTitledBorder("Directory Path"));
        directoryPanel.setLayout(new BorderLayout());
        directoryPanel.add(directoryTextField, BorderLayout.CENTER);
    }

    private void initButtonsPanel() {
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.add(browseButton);
        buttonsPanel.add(listButton);
    }

    private void initListPanel() {
//        listPanel.setLayout(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("List of files"));
        listPanel.setViewportView(pathsList);
    }

    private void initListeners() {
        browseButton.addActionListener(e -> {
            choseDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = choseDirectory.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                selectedDirectory = choseDirectory.getSelectedFile();
                directoryTextField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        listButton.addActionListener(e -> {
            String globbing = "*." + fileExtensionTextField.getText();
            Path path = selectedDirectory.toPath();
            List<Path> list = ListFiles.getListOfPaths(this, path, globbing);
            pathsListModel.clear();
            list.forEach(p->pathsListModel.addElement(p));
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListFilesFrame lff = new ListFilesFrame();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lff.setVisible(true);
            }
        });
    }
}
