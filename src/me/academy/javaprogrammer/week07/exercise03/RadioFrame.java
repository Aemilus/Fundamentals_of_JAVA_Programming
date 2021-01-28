package me.academy.javaprogrammer.week07.exercise03;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class RadioFrame extends JFrame {
    private static final int MAX_VOLUME = 100;
    private static final ImageIcon iconPowerOff;
    private static final ImageIcon iconPowerOn;

    private final Radio radioDevice = new Radio(MAX_VOLUME);

    private final JPanel rootPanel = new JPanel();
    private final JButton powerButton = new JButton();
    private final JLabel radioStationLabel = new JLabel();
    private final JButton nextStationButton = new JButton();
    private final JButton prevStationButton = new JButton();
    private final JLabel volumeLabel = new JLabel();
    private final JButton increaseVolumeButton = new JButton();
    private final JButton decreaseVolumeButton = new JButton();

    static {
        // initialize iconPowerOff
        URL iconURL = RadioFrame.class.getResource("/me/academy/javaprogrammer/week07/exercise03/img/power-button-off.png");
        ImageIcon icon = new ImageIcon(iconURL);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        iconPowerOff = new ImageIcon(resizedImg);
    }

    static {
        // initialize iconPowerOn
        URL iconURL = RadioFrame.class.getResource("/me/academy/javaprogrammer/week07/exercise03/img/power-button-on.png");
        ImageIcon icon = new ImageIcon(iconURL);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        iconPowerOn = new ImageIcon(resizedImg);
    }

    public RadioFrame() {
        super("Radio");
        initFrame();
        initComponents();
        pack();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5,5));
        setPreferredSize(new Dimension(280,150));
        setResizable(false);
        setLocationByPlatform(true);
        // set icon
        URL iconURL = RadioFrame.class.getResource("/me/academy/javaprogrammer/week07/exercise03/img/radio.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
    }

    private void initComponents() {
        initRootPanel();
        add(Box.createVerticalStrut(0), BorderLayout.NORTH);
        add(Box.createVerticalStrut(0), BorderLayout.SOUTH);
        add(Box.createHorizontalStrut(0), BorderLayout.EAST);
        add(Box.createHorizontalStrut(0), BorderLayout.WEST);
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initRootPanel() {
        rootPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2,2,2);

        initRadioStationLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(radioStationLabel, gbc);

        initPowerButton();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        rootPanel.add(powerButton, gbc);

        initPrevStationButton();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        rootPanel.add(prevStationButton, gbc);

        initNextStationButton();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        rootPanel.add(nextStationButton, gbc);

        initDecreaseVolumeButton();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        rootPanel.add(decreaseVolumeButton, gbc);

        initVolumeLabel();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        rootPanel.add(volumeLabel, gbc);

        initIncreaseVolumeButton();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        rootPanel.add(increaseVolumeButton, gbc);
    }

    private void initRadioStationLabel() {
        radioStationLabel.setPreferredSize(new Dimension(200,30));
        radioStationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        radioStationLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, Color.BLACK));
        radioStationLabel.setBackground(Color.GRAY);
        radioStationLabel.setForeground(Color.ORANGE);
        radioStationLabel.setOpaque(true);
    }

    private void initPowerButton() {
        powerButton.setMargin(new Insets(0,0,0,0));
        powerButton.setFocusable(false);
        // set icon
        powerButton.setIcon(iconPowerOff);
        // add action listener
        powerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioDevice.isPoweredOn()) {
                    radioDevice.turnOff();
                    radioStationLabel.setText(null);
                    volumeLabel.setText(null);
                    powerButton.setIcon(iconPowerOff);
                } else {
                    radioDevice.turnOn();
                    powerButton.setIcon(iconPowerOn);
                    volumeLabel.setText(String.valueOf(radioDevice.getVolume()));
                    radioStationLabel.setText(radioDevice.getCurrentRadioStation());
                }
            }
        });
    }

    private void initPrevStationButton() {
        prevStationButton.setMargin(new Insets(0,0,0,0));
        prevStationButton.setFocusable(false);
        // set icon
        URL iconURL = RadioFrame.class.getResource("/me/academy/javaprogrammer/week07/exercise03/img/backwards.png");
        ImageIcon icon = new ImageIcon(iconURL);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        prevStationButton.setIcon(new ImageIcon(resizedImg));
        // set action listener
        prevStationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioDevice.previousRadioStation();
                if (radioDevice.isPoweredOn()){
                    radioStationLabel.setText(String.valueOf(radioDevice.getCurrentRadioStation()));
                }
            }
        });
    }

    private void initNextStationButton() {
        nextStationButton.setMargin(new Insets(0,0,0,0));
        nextStationButton.setFocusable(false);
        // set icon
        URL iconURL = RadioFrame.class.getResource("/me/academy/javaprogrammer/week07/exercise03/img/forwards.png");
        ImageIcon icon = new ImageIcon(iconURL);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        nextStationButton.setIcon(new ImageIcon(resizedImg));
        // set action listener
        nextStationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioDevice.nextRadioStation();
                if (radioDevice.isPoweredOn()){
                    radioStationLabel.setText(String.valueOf(radioDevice.getCurrentRadioStation()));
                }
            }
        });
    }

    private void initDecreaseVolumeButton() {
        decreaseVolumeButton.setMargin(new Insets(0,0,0,0));
        decreaseVolumeButton.setFocusable(false);
        // set icon
        URL iconURL = RadioFrame.class.getResource("/me/academy/javaprogrammer/week07/exercise03/img/volume-down.png");
        ImageIcon icon = new ImageIcon(iconURL);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        decreaseVolumeButton.setIcon(new ImageIcon(resizedImg));
        // set action listener
        decreaseVolumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioDevice.decreaseVolume();
                if (radioDevice.isPoweredOn()){
                    volumeLabel.setText(String.valueOf(radioDevice.getVolume()));
                }
            }
        });
    }

    private void initVolumeLabel() {
        volumeLabel.setPreferredSize(new Dimension(40,30));
        volumeLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, Color.BLACK));
        volumeLabel.setBackground(Color.GRAY);
        volumeLabel.setForeground(Color.ORANGE);
        volumeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        volumeLabel.setOpaque(true);
    }

    private void initIncreaseVolumeButton() {
        increaseVolumeButton.setMargin(new Insets(0,0,0,0));
        increaseVolumeButton.setFocusable(false);
        // set icon
        URL iconURL = RadioFrame.class.getResource("/me/academy/javaprogrammer/week07/exercise03/img/volume-up.png");
        ImageIcon icon = new ImageIcon(iconURL);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        increaseVolumeButton.setIcon(new ImageIcon(resizedImg));
        // set action listener
        increaseVolumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioDevice.increaseVolume();
                if (radioDevice.isPoweredOn()){
                    volumeLabel.setText(String.valueOf(radioDevice.getVolume()));
                }
            }
        });
    }

    public static void main(String[] args) {
        RadioFrame radioFrame = new RadioFrame();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                radioFrame.setVisible(true);
            }
        });
    }
}
