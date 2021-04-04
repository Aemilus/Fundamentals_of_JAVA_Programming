package me.academy.javaprogrammer.finalproject;

import me.academy.javaprogrammer.finalproject.agenda.gui.contacts.ContactsAdministrationPanel;
import me.academy.javaprogrammer.finalproject.agenda.gui.menu.AgendaMenuBar;
import me.academy.javaprogrammer.finalproject.agenda.gui.slideshow.SlideShowPanel;
import me.academy.javaprogrammer.finalproject.agenda.gui.splashscreen.AgendaSplashScreen;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class AgendaFrame extends JFrame {
    // reference to singleton
    private static AgendaFrame _singletonAgendaFrame;
    // frame width and height
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 900;
    // application starts in shareware mode
    boolean agendaFrameSharewareMode = true;
    // application menu bar
    private final AgendaMenuBar agendaMenuBar = new AgendaMenuBar(this);
    // on root panel all the rest of panel components will be placed
    private final JPanel rootPanel = new JPanel();
    // panel where commercials are displayed in slideshow manner when in "shareware" mode
    private final SlideShowPanel slideShowPanel = new SlideShowPanel(this);
    // panel where contacts and related buttons/filters/sorters are displayed
    private final ContactsAdministrationPanel contactsAdministrationPanel = new ContactsAdministrationPanel(this);

    private AgendaFrame() {
        super("Agenda");
        initMenu();
        initRootPanel();
        initFrame();
        pack();
        setLocationRelativeTo(null);
    }

    public static synchronized AgendaFrame getInstance() {
        if (_singletonAgendaFrame == null) {
            _singletonAgendaFrame = new AgendaFrame();
        }
        return _singletonAgendaFrame;
    }

    private void initFrame() {
        initFrameIcon();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        add(rootPanel, BorderLayout.CENTER);
    }

    private void initFrameIcon() {
        try {
            Path iconsDir = Paths.get(AgendaFrame.class.getResource("agenda").toURI());
            Path iconFile = iconsDir.resolve("gui").resolve("icons").resolve("phone-book.png");
            ImageIcon imageIcon = new ImageIcon(iconFile.toUri().toURL());
            setIconImage(imageIcon.getImage());
        } catch (URISyntaxException | MalformedURLException e) {
            // if the icon is unavailable then ignore this and the java default will be used
        }
    }

    private void initMenu() {
        setJMenuBar(agendaMenuBar);
    }

    private void initRootPanel() {
        rootPanel.setLayout(new BorderLayout(10, 10));
        rootPanel.add(slideShowPanel, BorderLayout.NORTH);
        rootPanel.add(contactsAdministrationPanel, BorderLayout.CENTER);
    }

    public ContactsAdministrationPanel getContactsAdministrationPanel() {
        return contactsAdministrationPanel;
    }

    public SlideShowPanel getSlideShowPanel() {
        return slideShowPanel;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public AgendaMenuBar getAgendaMenuBar() {
        return agendaMenuBar;
    }

    public boolean isAgendaFrameSharewareMode() {
        return agendaFrameSharewareMode;
    }

    public void setAgendaFrameSharewareMode(boolean applicationMode) {
        this.agendaFrameSharewareMode = applicationMode;
    }

    public static void main(String[] args) {
        // set desired look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exception) {
            // if desired L&F is unavailable then do nothing
        }
        // run splash screen on a separate thread
        AgendaSplashScreen agendaSplashScreen = new AgendaSplashScreen();
        Thread loadingThread = new Thread(agendaSplashScreen);
        loadingThread.start();
        // wait for splash screen thread to finish
        try {
            loadingThread.join();
        } catch (InterruptedException e) {
            // if current thread is disturbed then exit application
            return;
        }
        // display the agenda application
        AgendaFrame af = AgendaFrame.getInstance();
        SwingUtilities.invokeLater(() -> af.setVisible(true));
    }
}
