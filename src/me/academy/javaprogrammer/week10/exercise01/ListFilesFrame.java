package me.academy.javaprogrammer.week10.exercise01;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

public final class ListFilesFrame extends JFrame {
    // inputPanel and buttonsPanel will be placed on below rootPanel
    private final JPanel rootPanel = new JPanel();

    // place all the components for gathering user input on inputPanel
    private final JPanel inputPanel = new JPanel();
    private final JPanel fileExtensionPanel = new JPanel();
    private final JTextField fileExtensionTextField = new JTextField();
    private final JPanel chosenDirectoryPanel = new JPanel();
    private final JTextField chosenDirectoryTextField = new JTextField();
    private File selectedDirectory;

    // place all JButtons on below panel
    // on press of browseButton the directoryChooser open dialog will be shown
    private final JPanel buttonsPanel = new JPanel();
    private final JButton browseButton = new JButton("Browse");
    private final JFileChooser directoryChooser = new JFileChooser();
    private final JButton listFilesButton = new JButton("List files");

    // a JScrollPane to display the JList of files matching the extension on listFilesButton press
    private final JScrollPane listPanel = new JScrollPane();
    private final DefaultListModel<Path> pathsListModel = new DefaultListModel<>();
    private final JList<Path> pathsList = new JList<>(pathsListModel);

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
        initChosenDirectoryPanel();
        initButtonsPanel();

        inputPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(fileExtensionPanel);
        inputPanel.add(chosenDirectoryPanel);
        inputPanel.add(buttonsPanel);
    }

    private void initFileExtensionPanel() {
        fileExtensionPanel.setBorder(BorderFactory.createTitledBorder("File extension"));
        fileExtensionPanel.setLayout(new BorderLayout());
        fileExtensionPanel.add(fileExtensionTextField, BorderLayout.CENTER);
    }

    private void initChosenDirectoryPanel() {
        chosenDirectoryPanel.setBorder(BorderFactory.createTitledBorder("Directory Path"));
        chosenDirectoryPanel.setLayout(new BorderLayout());
        chosenDirectoryPanel.add(chosenDirectoryTextField, BorderLayout.CENTER);
    }

    private void initButtonsPanel() {
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.add(browseButton);
        buttonsPanel.add(listFilesButton);
    }

    private void initListPanel() {
        listPanel.setBorder(BorderFactory.createTitledBorder("List of files"));
        listPanel.setViewportView(pathsList);
    }

    private void initListeners() {
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        browseButton.addActionListener(e -> {
            int option = directoryChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                selectedDirectory = directoryChooser.getSelectedFile();
                chosenDirectoryTextField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        listFilesButton.addActionListener(e -> {
            if (selectedDirectory == null) return;
            String globbing = "*." + fileExtensionTextField.getText().trim();
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
