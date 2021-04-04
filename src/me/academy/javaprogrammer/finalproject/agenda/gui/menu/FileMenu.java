package me.academy.javaprogrammer.finalproject.agenda.gui.menu;

import me.academy.javaprogrammer.finalproject.AgendaFrame;
import me.academy.javaprogrammer.finalproject.agenda.core.contacts.Contacts;

import javax.swing.*;
import java.io.*;
import java.util.prefs.Preferences;

public final class FileMenu extends JMenu {
    private final AgendaFrame agendaFrame;
    private Thread autoSaveThread;

    private final JFileChooser fileChooser = new JFileChooser();
    private final Preferences preferences = Preferences.userNodeForPackage(AgendaFrame.class);
    private final JMenuItem openMenuItem = new JMenuItem("Open");
    private final JMenuItem saveMenuItem = new JMenuItem("Save");
    private final JMenuItem exitMenuItem = new JMenuItem("Exit");

    public FileMenu(AgendaFrame agendaFrame) {
        super("File");
        this.agendaFrame = agendaFrame;
        initComponents();
        initMenu();
        initListeners();
    }

    private void initMenu() {
        add(openMenuItem);
        add(saveMenuItem);
        add(exitMenuItem);
    }

    private void initComponents() {
        if (agendaFrame.isAgendaFrameSharewareMode()) {
            saveMenuItem.setEnabled(false);
            openMenuItem.setEnabled(false);
        }
    }

    private void initListeners() {
        openMenuItem.addActionListener(event -> {
            try {
                fileChooser.setCurrentDirectory(getFileChooserPreferences());
                int option = fileChooser.showOpenDialog(agendaFrame);
                saveFileChooserPreferences(fileChooser.getCurrentDirectory());
                if (option != JFileChooser.APPROVE_OPTION) return;
                stopAutoSaveThread();
                File openFile = fileChooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(openFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                agendaFrame.getContactsAdministrationPanel().setContacts((Contacts) ois.readObject());
                ois.close();
                fis.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(agendaFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        saveMenuItem.addActionListener(event -> {
            File saveFile = null;
            try {
                fileChooser.setCurrentDirectory(getFileChooserPreferences());
                int option = fileChooser.showSaveDialog(agendaFrame);
                saveFileChooserPreferences(fileChooser.getCurrentDirectory());
                if (option != JFileChooser.APPROVE_OPTION) return;
                saveFile = fileChooser.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(saveFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(agendaFrame.getContactsAdministrationPanel().getContacts());
                oos.close();
                fos.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(agendaFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            // once the contacts list has been saved on the disk
            // then every 1 minute the list will automatically be saved
            stopAutoSaveThread();
            final File autoSaveFile = saveFile;
            autoSaveThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Thread.sleep(60000);
                            FileOutputStream fos = new FileOutputStream(autoSaveFile);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            Contacts contacts = agendaFrame.getContactsAdministrationPanel().getContacts();
                            // acquire lock on contacts list object so as to when we write it on disk
                            // then no other thread can bring changes to the object
                            synchronized (contacts) {
                                oos.writeObject(contacts);
                            }
                            oos.close();
                            fos.close();
                            // if thread was interrupted while a saving was in progress then exit thread after saving completes
                            if (autoSaveThread.isInterrupted()) return;
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(agendaFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (InterruptedException e) {
                        // if interrupted while thread was sleeping then close thread
                    }
                }
            });
            // start auto-save thread
            autoSaveThread.start();
        });

        exitMenuItem.addActionListener(event -> {
            int choice = JOptionPane.showConfirmDialog(agendaFrame, "Unsaved changes will be lost. Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (choice == 0) {
                System.exit(0);
            }
        });
    }

    private void saveFileChooserPreferences(File currentDir) {
        preferences.put("currentDir", currentDir.getAbsolutePath());
    }

    private File getFileChooserPreferences() {
        String currentPrefDir = preferences.get("currentDir", null);
        return currentPrefDir == null ? null : new File(currentPrefDir);
    }

    private void stopAutoSaveThread() {
        if (autoSaveThread != null) {
            // if auto-save thread is running then stop it first
            autoSaveThread.interrupt();
            // wait for autos-save thread to stop
            try {
                autoSaveThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public JMenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    public JMenuItem getSaveMenuItem() {
        return saveMenuItem;
    }
}
