package me.academy.javaprogrammer.week08.exercise01;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonsListFrame extends JFrame {
    // panel where a Person's details are managed
    private final JPanel managePersonPanel = new JPanel();
    private final JTextField personNameTextField = new JTextField();
    private final SpinnerNumberModel personAgeModel = new SpinnerNumberModel(0,0,125,1);
    private final JSpinner personAgeSpinner = new JSpinner(personAgeModel);
    private final ButtonGroup personGenders = new ButtonGroup();
    private final JRadioButton maleRadioButton = new JRadioButton("M");
    private final JRadioButton femaleRadioButton = new JRadioButton("F");
    private final JCheckBox marriedCheckBox = new JCheckBox();
    // buttons to do actions with Person objects
    private final JButton addPersonButton = new JButton("Add");
    private final JButton modifyPersonButton = new JButton("Modify");
    private final JButton clearPersonButton = new JButton("Clear");
    private final JButton deletePersonButton = new JButton("Delete");
    // panel to display the list of Persons
    private final JPanel listPanel = new JPanel();
    private final JScrollPane listScrollPanel = new JScrollPane();
    private final List<Person> personsList = new ArrayList<>();
    private final DefaultListModel<Person> personsListModel = new DefaultListModel<>();
    private final JList<Person> personsJList = new JList<>(personsListModel);

    private final JPanel sortingButtonsPanel = new JPanel();
    private final ButtonGroup sortingRadioButtonsGroup = new ButtonGroup();
    private final JRadioButton sortByNameRadioButton = new JRadioButton("by name");
    private final JRadioButton sortByAgeRadioButton = new JRadioButton("by age");
    private final JCheckBox reverseSortingCheckBox = new JCheckBox("reverse");

    private final JPanel rootPanel = new JPanel();

    private static final Comparator<Person> byAge = Person::sortByAge;

    public PersonsListFrame() {
        super("Persons List");
        initFrame();
        initComponents();
        initListeners();
        pack();
    }

    private void initFrame() {
        setPreferredSize(new Dimension(600,600));
        setLocationByPlatform(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15,10));
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
        initListPanel();
        initManagePersonPanel();
        rootPanel.setLayout(new GridLayout(1,2));
        rootPanel.add(listPanel);
        rootPanel.add(managePersonPanel);
    }

    private void initListPanel() {
        listPanel.setLayout(new BorderLayout());

        initListScrollPanel();
        initSortingButtonsPanel();
        listPanel.add(listScrollPanel, BorderLayout.CENTER);
        listPanel.add(sortingButtonsPanel, BorderLayout.SOUTH);
    }

    private void initListScrollPanel() {
        listScrollPanel.setBorder(BorderFactory.createTitledBorder("List of Persons"));
        listScrollPanel.setViewportView(personsJList);
    }

    private void initSortingButtonsPanel() {
        sortingButtonsPanel.setLayout(new GridLayout(3,1));
        sortingButtonsPanel.setBorder(BorderFactory.createTitledBorder("Sort"));
        sortingRadioButtonsGroup.add(sortByNameRadioButton);
        sortingRadioButtonsGroup.add(sortByAgeRadioButton);
        sortingButtonsPanel.add(sortByNameRadioButton);
        sortingButtonsPanel.add(sortByAgeRadioButton);
        sortingButtonsPanel.add(reverseSortingCheckBox);
    }

    private void initManagePersonPanel() {
        managePersonPanel.setLayout(new GridLayout(6,1));
        managePersonPanel.setBorder(BorderFactory.createTitledBorder("Manage Person"));

        initPersonNamePanel();
        initPersonAgeSpinner();
        initPersonGendersPanel();
        initMaritalStatusPanel();

        managePersonPanel.add(deletePersonButton);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,3));
        buttonsPanel.add(addPersonButton);
        buttonsPanel.add(modifyPersonButton);
        buttonsPanel.add(clearPersonButton);
        managePersonPanel.add(buttonsPanel);
    }

    private void initPersonNamePanel() {
        JPanel personNamePanel = new JPanel();
        personNamePanel.setLayout(new BorderLayout());
        personNamePanel.add(personNameTextField, BorderLayout.CENTER);
        personNamePanel.setBorder(BorderFactory.createTitledBorder("Name"));
        managePersonPanel.add(personNamePanel);
    }

    private void initPersonAgeSpinner() {
        personAgeSpinner.setBorder(BorderFactory.createTitledBorder("Age"));
        managePersonPanel.add(personAgeSpinner);
    }

    private void initPersonGendersPanel() {
        JPanel personGendersPanel = new JPanel();
        personGendersPanel.setLayout(new GridLayout(2,1));
        personGenders.add(maleRadioButton);
        personGenders.add(femaleRadioButton);
        personGendersPanel.add(maleRadioButton);
        personGendersPanel.add(femaleRadioButton);
        personGendersPanel.setBorder(BorderFactory.createTitledBorder("Gender"));
        managePersonPanel.add(personGendersPanel);
    }

    private void initMaritalStatusPanel() {
        JPanel maritalStatusPanel = new JPanel();
        maritalStatusPanel.setBorder(BorderFactory.createTitledBorder("Married"));
        maritalStatusPanel.setLayout(new GridLayout(1,1));
        maritalStatusPanel.add(marriedCheckBox);
        managePersonPanel.add(maritalStatusPanel);
    }

    private void initListeners() {
        addPersonButton.addActionListener(e -> {
            Person p = parseInputPerson();
            if (p == null) {
                JOptionPane.showMessageDialog(this,"Invalid person details.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            personsList.add(p);

            refreshPersonsListModel();
        });

        modifyPersonButton.addActionListener(e -> {
            // get the Person selected in list; do nothing if no person was selected
            int selection = personsJList.getSelectedIndex();
            Person p = personsJList.getSelectedValue();
            if (p == null) {
                JOptionPane.showMessageDialog(this,"No person selected.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // instantiate a new Person object with the arguments given in panel; if the arguments are not valid then show warning
            Person m = parseInputPerson();
            if (m == null) {
                JOptionPane.showMessageDialog(this,"Invalid person details.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // update selected Person object with the arguments that were given in panel
            p.setName(m.getName());
            p.setAge(m.getAge());
            p.setGender(m.getGender());
            p.setMarried(m.isMarried());

            refreshPersonsListModel();
            personsJList.setSelectedIndex(selection);
        });

        clearPersonButton.addActionListener(e -> {
            personNameTextField.setText(null);
            personAgeSpinner.setValue(0);
            personGenders.clearSelection();
            marriedCheckBox.setSelected(false);
        });

        deletePersonButton.addActionListener(e -> {
            int[] selection = personsJList.getSelectedIndices();
            for (int i : selection) {
                Person pSelected = personsListModel.get(i);
                for (int j = 0; j < personsList.size(); j++) {
                    if (pSelected.getId() == personsList.get(j).getId()) {
                        personsList.remove(j);
                        break;
                    }
                }
            }

            refreshPersonsListModel();
        });

        sortByNameRadioButton.addActionListener(e -> {
            if (!sortByNameRadioButton.isSelected()) return;
            Collections.sort(personsList);
            if (reverseSortingCheckBox.isSelected()) {
                Collections.reverse(personsList);
            }

            refreshPersonsListModel();
        });

        sortByAgeRadioButton.addActionListener(e -> {
            if (!sortByAgeRadioButton.isSelected()) return;
            personsList.sort(byAge);
            if (reverseSortingCheckBox.isSelected()) {
                Collections.reverse(personsList);
            }

            refreshPersonsListModel();
        });

        reverseSortingCheckBox.addActionListener(e -> {
            Collections.reverse(personsList);

            refreshPersonsListModel();
        });
    }

    private Person parseInputPerson() {
        String gender = null;
        if (maleRadioButton.isSelected()) gender = maleRadioButton.getText();
        if (femaleRadioButton.isSelected()) gender = femaleRadioButton.getText();

        return Person.getInstance(personNameTextField.getText(), (Integer) personAgeSpinner.getValue(), gender, marriedCheckBox.isSelected());
    }

    private void refreshPersonsListModel() {
        personsListModel.clear();
        for(Person p : personsList) {
            personsListModel.addElement(p);
        }
        personsJList.setModel(personsListModel);
    }

    public static void main(String[] args) throws Exception {
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
            }
        }

        JFrame personsListFrame = new PersonsListFrame();

        // launch on event dispatching thread using lambda expression
        SwingUtilities.invokeLater(() -> personsListFrame.setVisible(true));
    }
}
