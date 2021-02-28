package me.academy.javaprogrammer.week10.exercise07;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.TreeMap;

public class CustomFrame extends JFrame {
    private final TreeMap<String, String> settingsTree;
    private final JPanel rootPanel = new JPanel();
    private JButton button;

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("File");
    private final JMenuItem exportMenuItem = new JMenuItem("Export");

    public CustomFrame() throws IOException {
        // store the settings into a TreeMap object
        // use the (key, value) pairs across application to initialize the frame and it's components
        this.settingsTree = ImportSettings.getSettings();
        initFrame();
        initMenus();
        initComponents();
        initListeners();
        pack();
    }

    private void initFrame() {
        setTitle(settingsTree.get("frameTitle"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Integer.parseInt(settingsTree.get("frameWidth")), Integer.parseInt(settingsTree.get("frameHeight"))));
        setLocation(Integer.parseInt(settingsTree.get("locationX")), Integer.parseInt(settingsTree.get("locationY")));
    }

    private void initMenus() {
        fileMenu.add(exportMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void initComponents() {
        initRootPanel();
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initRootPanel() {
        rootPanel.setLayout(new BorderLayout());
        button = new JButton(settingsTree.get("buttonText"));
        rootPanel.add(button, BorderLayout.CENTER);
    }

    private void initListeners() {
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, settingsTree.get("hiddenMessage"), "Hidden message", JOptionPane.INFORMATION_MESSAGE);
        });

        exportMenuItem.addActionListener(e -> {
            try {
                settingsTree.put("locationX", String.valueOf((int) this.getLocation().getX()));
                settingsTree.put("locationY", String.valueOf((int) this.getLocation().getY()));
                settingsTree.put("frameWidth", String.valueOf(this.getWidth()));
                settingsTree.put("frameHeight", String.valueOf(this.getHeight()));
                ExportSettings.exportSettings(this, settingsTree);
            } catch (IOException ex) {
                DisplayError.showError(this, ex);
            }
        });
    }

    public static void main(String[] args) {
        try {
            CustomFrame cf = new CustomFrame();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    cf.setVisible(true);
                }
            });
        } catch (Exception ex) {
            DisplayError.showError(null, ex);
        }
    }
}
