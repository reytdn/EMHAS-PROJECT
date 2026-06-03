import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class EMHASREGISTERPATIENTGUI extends JFrame {
    private MAINSYSTEM mainsystem;

    // Flags for confirm buttons
    private boolean allergiesConfirmed = false;
    private boolean conditionsConfirmed = false;
    private boolean medicationsConfirmed = false;
    private boolean familyConfirmed = false;
    private boolean immunizationsConfirmed = false;

    // Temporary lists
    private List<String> allergies = new ArrayList<>();
    private List<String> conditions = new ArrayList<>();
    private List<String> medications = new ArrayList<>();
    private List<String> familyHistory = new ArrayList<>();
    private List<String> immunizations = new ArrayList<>();

    public EMHASREGISTERPATIENTGUI(MAINSYSTEM mainsystem) {
        this.mainsystem = mainsystem;
        setTitle("Register Patient");
        setSize(600, 750);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10));

        // Patient ID
        JTextField patientIdField = new JTextField(15);

        // Name fields
        JTextField fnameField = new JTextField(15);
        JTextField lnameField = new JTextField(15);

        // MI scroll box with blank + A–Z
        String[] letters = new String[27];
        letters[0] = "";
        for (int i = 1; i <= 26; i++) letters[i] = String.valueOf((char) ('A' + (i - 1)));
        JComboBox<String> miBox = new JComboBox<>(letters);

        // DOB fields
        String[] months = {"January","February","March","April","May","June",
                           "July","August","September","October","November","December"};
        JComboBox<String> monthBox = new JComboBox<>(months);

        JComboBox<Integer> dayBox = new JComboBox<>();
        JComboBox<Integer> yearBox = new JComboBox<>();
        for (int y = 1900; y <= 2026; y++) yearBox.addItem(y);

        // Update days dynamically
        monthBox.addActionListener(e -> updateDays(dayBox, monthBox, yearBox));
        yearBox.addActionListener(e -> updateDays(dayBox, monthBox, yearBox));

        JTextField ageField = new JTextField(3);

        // Gender
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male","Female"});

        // Emergency Contact
        JTextField emergencyField = new JTextField(11);

        // Address
        JTextField barangayField = new JTextField(15);
        JTextField cityField = new JTextField(15);
        JTextField provinceField = new JTextField(15);

        // Blood type
        JComboBox<String> bloodBox = new JComboBox<>(new String[]{
            "O+","O-","A+","A-","B+","B-","AB+","AB-"
        });

        // Counts + confirm buttons
        JTextField allergyCountField = new JTextField(3);
        JButton confirmAllergyBtn = new JButton("Confirm");

        JTextField conditionCountField = new JTextField(3);
        JButton confirmConditionBtn = new JButton("Confirm");

        JTextField medicationCountField = new JTextField(3);
        JButton confirmMedicationBtn = new JButton("Confirm");

        JTextField familyCountField = new JTextField(3);
        JButton confirmFamilyBtn = new JButton("Confirm");

        JTextField immunizationCountField = new JTextField(3);
        JButton confirmImmunizationBtn = new JButton("Confirm");

        // Add components
        panel.add(new JLabel("Patient ID:")); panel.add(patientIdField); panel.add(new JLabel());
        panel.add(new JLabel("First Name:")); panel.add(fnameField); panel.add(new JLabel());
        panel.add(new JLabel("Last Name:")); panel.add(lnameField); panel.add(new JLabel());
        panel.add(new JLabel("Middle Initial:")); panel.add(miBox); panel.add(new JLabel());
        panel.add(new JLabel("Birth Month:")); panel.add(monthBox); panel.add(new JLabel());
        panel.add(new JLabel("Birth Day:")); panel.add(dayBox); panel.add(new JLabel());
        panel.add(new JLabel("Birth Year:")); panel.add(yearBox); panel.add(new JLabel());
        panel.add(new JLabel("Age:")); panel.add(ageField); panel.add(new JLabel());
        panel.add(new JLabel("Gender:")); panel.add(genderBox); panel.add(new JLabel());
        panel.add(new JLabel("Emergency Contact:")); panel.add(emergencyField); panel.add(new JLabel());
        panel.add(new JLabel("Barangay:")); panel.add(barangayField); panel.add(new JLabel());
        panel.add(new JLabel("City:")); panel.add(cityField); panel.add(new JLabel());
        panel.add(new JLabel("Province:")); panel.add(provinceField); panel.add(new JLabel());
        panel.add(new JLabel("Blood Type:")); panel.add(bloodBox); panel.add(new JLabel());

        panel.add(new JLabel("Allergy Count:")); panel.add(allergyCountField); panel.add(confirmAllergyBtn);
        panel.add(new JLabel("Condition Count:")); panel.add(conditionCountField); panel.add(confirmConditionBtn);
        panel.add(new JLabel("Medication Count:")); panel.add(medicationCountField); panel.add(confirmMedicationBtn);
        panel.add(new JLabel("Family History Count:")); panel.add(familyCountField); panel.add(confirmFamilyBtn);
        panel.add(new JLabel("Immunization Count:")); panel.add(immunizationCountField); panel.add(confirmImmunizationBtn);

        // Register button
        JButton okButton = new JButton("Register");
        okButton.setEnabled(false);

        // Confirm button actions
        confirmAllergyBtn.addActionListener(e -> {
            int count = parseCount(allergyCountField);
            allergies = collectList("Allergy", count);
            allergiesConfirmed = true;
            okButton.setEnabled(allConfirmed(patientIdField, fnameField, lnameField, ageField, emergencyField,
                                             barangayField, cityField, provinceField));
        });
        confirmConditionBtn.addActionListener(e -> {
            int count = parseCount(conditionCountField);
            conditions = collectList("Condition", count);
            conditionsConfirmed = true;
            okButton.setEnabled(allConfirmed(patientIdField, fnameField, lnameField, ageField, emergencyField,
                                             barangayField, cityField, provinceField));
        });
        confirmMedicationBtn.addActionListener(e -> {
            int count = parseCount(medicationCountField);
            medications = collectList("Medication", count);
            medicationsConfirmed = true;
            okButton.setEnabled(allConfirmed(patientIdField, fnameField, lnameField, ageField, emergencyField,
                                             barangayField, cityField, provinceField));
        });
        confirmFamilyBtn.addActionListener(e -> {
            int count = parseCount(familyCountField);
            familyHistory = collectList("Family History", count);
            familyConfirmed = true;
            okButton.setEnabled(allConfirmed(patientIdField, fnameField, lnameField, ageField, emergencyField,
                                             barangayField, cityField, provinceField));
        });
        confirmImmunizationBtn.addActionListener(e -> {
            int count = parseCount(immunizationCountField);
            immunizations = collectList("Immunization", count);
            immunizationsConfirmed = true;
            okButton.setEnabled(allConfirmed(patientIdField, fnameField, lnameField, ageField, emergencyField,
                                             barangayField, cityField, provinceField));
        });

        okButton.addActionListener(e -> {
            try {
                String patientId = patientIdField.getText().trim();
                String fname = fnameField.getText().trim();
                String lname = lnameField.getText().trim();
                String mi = (String) miBox.getSelectedItem();
                String month = (String) monthBox.getSelectedItem();
                int monthNumber = monthBox.getSelectedIndex() + 1;
                int day = (Integer) dayBox.getSelectedItem();
                int year = (Integer) yearBox.getSelectedItem();
                int age = Integer.parseInt(ageField.getText().trim());
                String gender = (String) genderBox.getSelectedItem();
                String emergency = emergencyField.getText().trim();
                String barangay = barangayField.getText().trim();
                String city = cityField.getText().trim();
                if (!city.toLowerCase().endsWith(" city")) city = city + " City";
                String province = provinceField.getText().trim();
                String blood = (String) bloodBox.getSelectedItem();

                // Validate age vs birthdate
                LocalDate birth = LocalDate.of(year, monthNumber, day);
                int computedAge = Period.between(birth, LocalDate.now()).getYears();
                if (age != computedAge) {
                    JOptionPane.showMessageDialog(this, "Age does not match birthdate!");
                    return;
                }

                boolean success = mainsystem.REGISTER_PATIENT(
                    patientId, fname, lname, mi,
                    month, day, year, gender, age,
                    emergency, blood,
                    barangay, city, province,
                    allergies, conditions, medications,
                    familyHistory, immunizations
                );

                                JOptionPane.showMessageDialog(this, success ? "Patient Registered Successfully!" : "Failed To Register Patient.");
                if (success) dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        });

        add(panel, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    // ✅ Helper: update days based on month/year (leap year logic)
    private void updateDays(JComboBox<Integer> dayBox, JComboBox<String> monthBox, JComboBox<Integer> yearBox) {
        dayBox.removeAllItems();
        int month = monthBox.getSelectedIndex() + 1;
        int year = (Integer) yearBox.getSelectedItem();
        int maxDays = 31;

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDays = 30;
        } else if (month == 2) {
            boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            maxDays = leap ? 29 : 28;
        }
        for (int d = 1; d <= maxDays; d++) dayBox.addItem(d);
    }

    // ✅ Helper: parse count safely
    private int parseCount(JTextField field) {
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    // ✅ Helper: collect list items
    private List<String> collectList(String label, int count) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String item = JOptionPane.showInputDialog(this, "Enter " + label + " " + i + ":");
            if (item != null && !item.trim().isEmpty()) {
                list.add(item.trim());
            } else {
                JOptionPane.showMessageDialog(this, label + " " + i + " cannot be empty.");
                i--; // retry same index
            }
        }
        return list;
    }

    //  Helper: check if all requirements are confirmed
    private boolean allConfirmed(JTextField patientId, JTextField fname, JTextField lname,
                                 JTextField age, JTextField emergency,
                                 JTextField barangay, JTextField city, JTextField province) {
        return !patientId.getText().trim().isEmpty()
                && !fname.getText().trim().isEmpty()
                && !lname.getText().trim().isEmpty()
                && !age.getText().trim().isEmpty()
                && emergency.getText().trim().matches("\\d{11}")
                && !barangay.getText().trim().isEmpty()
                && city.getText().trim().matches("[a-zA-Z\\-\\' ]+")
                && province.getText().trim().matches("[a-zA-Z\\-\\' ]+")
                && allergiesConfirmed && conditionsConfirmed && medicationsConfirmed
                && familyConfirmed && immunizationsConfirmed;
    }

}
