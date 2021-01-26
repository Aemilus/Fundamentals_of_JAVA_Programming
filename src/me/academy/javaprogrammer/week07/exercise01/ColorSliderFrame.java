package me.academy.javaprogrammer.week07.exercise01;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.net.URL;

public class ColorSliderFrame extends JFrame {
    // will put all components on this panel
    private final JPanel rootPanel = new JPanel();

    // panel for black to white slider
    private final JPanel bwPanel = new JPanel();
    private final JLabel bwLabel = new JLabel();
    private final JSlider bwSlider = new JSlider(0,255,0);

    // panel for RGB sliders
    private final JPanel rgbPanel = new JPanel();
    private final JLabel rgbLabel = new JLabel();
    private final JPanel rgbSlidersPanel = new JPanel();
    private final JSlider rSlider = new JSlider(0,255,32);
    private final JSlider gSlider = new JSlider(0,255,96);
    private final JSlider bSlider = new JSlider(0,255,172);

    public ColorSliderFrame() {
        super("Color Slider");
        initFrame();
        initComponents();
        pack();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(350, 650));
        setResizable(false);
        setLocationByPlatform(true);
        setLayout(new BorderLayout(0,10));
        // set icon
        URL iconURL = ColorSliderFrame.class.getResource("/me/academy/javaprogrammer/week07/exercise01/img/chameleon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
    }

    private void initComponents() {
        initBwPanel();
        initRgbPanel();
        initRootPanel();
        add(Box.createVerticalStrut(0), BorderLayout.NORTH);
        add(Box.createVerticalStrut(0), BorderLayout.SOUTH);
        add(Box.createHorizontalStrut(0), BorderLayout.EAST);
        add(Box.createHorizontalStrut(0), BorderLayout.WEST);
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initRootPanel() {
        rootPanel.setLayout(new GridLayout(2,1,0,10));
        rootPanel.add(bwPanel);
        rootPanel.add(rgbPanel);
    }

    private void initBwPanel() {
        bwPanel.setBorder(BorderFactory.createTitledBorder(bwPanel.getBorder(), "B2W slider", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        bwPanel.setLayout(new BorderLayout(10,10));
        initBwLabel();
        initBwSlider();
        bwPanel.add(bwLabel, BorderLayout.CENTER);
        bwPanel.add(bwSlider, BorderLayout.SOUTH);
    }

    private void initBwLabel() {
        bwLabel.setBackground(new Color(bwSlider.getValue()));
        bwLabel.setOpaque(true);
    }

    private void initBwSlider() {
        bwSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int bwValue = bwSlider.getValue();
                bwLabel.setBackground(new Color(bwValue, bwValue, bwValue));
            }
        });
    }

    private void initRgbPanel() {
        rgbPanel.setBorder(BorderFactory.createTitledBorder(rgbPanel.getBorder(), "RGB sliders", TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        rgbPanel.setLayout(new BorderLayout(10,10));
        initRgbLabel();
        initRgbSlidersPanel();
        rgbPanel.add(rgbLabel, BorderLayout.CENTER);
        rgbPanel.add(rgbSlidersPanel, BorderLayout.SOUTH);
    }

    private void initRgbLabel() {
        rgbLabel.setBackground(new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue()));
        rgbLabel.setOpaque(true);
    }

    private void initRgbSlidersPanel() {
        rgbSlidersPanel.setLayout(new BoxLayout(rgbSlidersPanel, BoxLayout.Y_AXIS));
        rgbSlidersPanel.add(rSlider);
        rgbSlidersPanel.add(gSlider);
        rgbSlidersPanel.add(bSlider);
        initRgbSlidersListener();
    }

    private void initRgbSlidersListener() {
        // change events coming from RGB sliders will be handled by same listener
        ChangeListener rgbSlidersListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                rgbLabel.setBackground(new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue()));
            }
        };

        rSlider.addChangeListener(rgbSlidersListener);
        gSlider.addChangeListener(rgbSlidersListener);
        bSlider.addChangeListener(rgbSlidersListener);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // set Nimbus look and feel
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        ColorSliderFrame colorSliderFrame = new ColorSliderFrame();

        // call setVisible() method on event-dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                colorSliderFrame.setVisible(true);
            }
        });
    }
}
