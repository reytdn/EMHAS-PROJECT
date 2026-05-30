import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class EMHASREGISTERPATIENTGUI extends JFrame {
    private MAINSYSTEM mainsystem;

    public EMHASREGISTERPATIENTGUI(MAINSYSTEM mainsystem) {
        this.mainsystem = mainsystem;
        setTitle("Register Patient");
        setSize(500, 700);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Patient ID
        JTextField patientIdField = new JTextField(15);

        // Name fields
        JTextField fnameField = new JTextField(15);
        JTextField lnameField = new JTextField(15);

        // MI scroll box with blank + A–Z
        String[] letters = new String[27];
        letters[0] = "";
        for (int i = 1; i <= 26; i++) {
            letters[i] = String.valueOf((char) ('A' + (i - 1)));
        }
        JComboBox<String> miBox = new JComboBox<>(letters);

        // DOB fields
        String[] months = {"January","February","March","April","May","June",
                           "July","August","September","October","November","December"};
        JComboBox<String> monthBox = new JComboBox<>(months);
        JTextField dayField = new JTextField(2);
        JTextField yearField = new JTextField(4);
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

        // Counts
        JTextField allergyCountField = new JTextField(3);
        JTextField conditionCountField = new JTextField(3);
        JTextField medicationCountField = new JTextField(3);
        JTextField familyCountField = new JTextField(3);
        JTextField immunizationCountField = new JTextField(3);

        // Add components
        panel.add(new JLabel("Patient ID:")); panel.add(patientIdField);
        panel.add(new JLabel("First Name:")); panel.add(fnameField);
        panel.add(new JLabel("Last Name:")); panel.add(lnameField);
        panel.add(new JLabel("Middle Initial:")); panel.add(miBox);
        panel.add(new JLabel("Birth Month:")); panel.add(monthBox);
        panel.add(new JLabel("Birth Day:")); panel.add(dayField);
        panel.add(new JLabel("Birth Year:")); panel.add(yearField);
        panel.add(new JLabel("Age:")); panel.add(ageField);
        panel.add(new JLabel("Gender:")); panel.add(genderBox);
        panel.add(new JLabel("Emergency Contact:")); panel.add(emergencyField);
        panel.add(new JLabel("Barangay:")); panel.add(barangayField);
        panel.add(new JLabel("City:")); panel.add(cityField);
        panel.add(new JLabel("Province:")); panel.add(provinceField);
        panel.add(new JLabel("Blood Type:")); panel.add(bloodBox);
        panel.add(new JLabel("Allergy Count:")); panel.add(allergyCountField);
        panel.add(new JLabel("Condition Count:")); panel.add(conditionCountField);
        panel.add(new JLabel("Medication Count:")); panel.add(medicationCountField);
        panel.add(new JLabel("Family History Count:")); panel.add(familyCountField);
        panel.add(new JLabel("Immunization Count:")); panel.add(immunizationCountField);

        // Confirm dialog with disabled OK until valid
        JButton okButton = new JButton("Register");
        okButton.setEnabled(false); // disabled initially

        // ✅ Real-time validation listener
        KeyAdapter validator = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Validator call
            okButton.setEnabled(isFormValid(patientIdField, fnameField, lnameField,
                dayField, yearField, ageField, emergencyField,
                barangayField, cityField, provinceField,
                allergyCountField, conditionCountField,
                medicationCountField, familyCountField,
                immunizationCountField));


            }
        };

        // Attach validator to required fields
        patientIdField.addKeyListener(validator);
        fnameField.addKeyListener(validator);
        lnameField.addKeyListener(validator);
        dayField.addKeyListener(validator);
        yearField.addKeyListener(validator);
        ageField.addKeyListener(validator);
        emergencyField.addKeyListener(validator);
        barangayField.addKeyListener(validator);
        cityField.addKeyListener(validator);
        provinceField.addKeyListener(validator);

        okButton.addActionListener(e -> {
            try {
                String patientId = patientIdField.getText().trim();
                String fname = fnameField.getText().trim();
                String lname = lnameField.getText().trim();
                String mi = (String) miBox.getSelectedItem();
                String month = (String) monthBox.getSelectedItem();
                int monthNumber = monthBox.getSelectedIndex() + 1;  
                int day = Integer.parseInt(dayField.getText().trim());
                int year = Integer.parseInt(yearField.getText().trim());
                int age = Integer.parseInt(ageField.getText().trim());
                String gender = (String) genderBox.getSelectedItem();
                String emergency = emergencyField.getText().trim();
                String barangay = barangayField.getText().trim();
                String city = cityField.getText().trim();
                    if (!city.toLowerCase().endsWith(" city")) {
                        city = city + " City";
                    }
                String province = provinceField.getText().trim();
                String blood = (String) bloodBox.getSelectedItem();

                // Validate age vs birthdate
                LocalDate birth = LocalDate.of(year, monthNumber, day);
                int computedAge = Period.between(birth, LocalDate.now()).getYears();
                if (age != computedAge) {
                    JOptionPane.showMessageDialog(this, "Age does not match birthdate!");
                    return;
                }

                // Validate emergency contact
                if (!emergency.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(this, "Emergency contact must be exactly 11 digits!");
                    return;
                }

                // Collect lists
                int allergyCount = parseCount(allergyCountField);
                int conditionCount = parseCount(conditionCountField);
                int medicationCount = parseCount(medicationCountField);
                int familyCount = parseCount(familyCountField);
                int immunizationCount = parseCount(immunizationCountField);

                List<String> allergies = collectList("Allergy", allergyCount);
                List<String> conditions = collectList("Condition", conditionCount);
                List<String> medications = collectList("Medication", medicationCount);
                List<String> familyHistory = collectList("Family History", familyCount);
                List<String> immunizations = collectList("Immunization", immunizationCount);

                boolean success = mainsystem.REGISTER_PATIENT(
                    patientId, fname, lname, mi,
                    month , day, year, gender, age,
                    emergency, blood,
                    barangay, city, province,
                    allergies, conditions, medications,
                    familyHistory, immunizations
                );

                if (success) {
                    JOptionPane.showMessageDialog(this, "Patient Registered Successfully!");
                    dispose(); // ✅ Auto-close panel
                } else {
                    JOptionPane.showMessageDialog(this, "Failed To Register Patient.");
                }

                JOptionPane.showMessageDialog(this, success ? "Patient Registered Successfully!" : "Failed To Register Patient.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        });

        add(panel, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    // ✅ Helper: check if required fields are filled
    private boolean isFormValid(JTextField patientId, JTextField fname, JTextField lname,
                            JTextField day, JTextField year, JTextField age,
                            JTextField emergency, JTextField barangay,
                            JTextField city, JTextField province,
                            JTextField allergyCount, JTextField conditionCount,
                            JTextField medicationCount, JTextField familyCount,
                            JTextField immunizationCount) {
        return !patientId.getText().trim().isEmpty()
                && !fname.getText().trim().isEmpty()
                && !lname.getText().trim().isEmpty()
                && !day.getText().trim().isEmpty()
                && !year.getText().trim().isEmpty()
                && !age.getText().trim().isEmpty()
                && emergency.getText().trim().matches("\\d{11}")
                && !barangay.getText().trim().isEmpty()
                && city.getText().trim().matches("[a-zA-Z\\-\\' ]+")
                && province.getText().trim().matches("[a-zA-Z\\-\\' ]+")
                && !allergyCount.getText().trim().isEmpty()
                && !conditionCount.getText().trim().isEmpty()
                && !medicationCount.getText().trim().isEmpty()
                && !familyCount.getText().trim().isEmpty()
                && !immunizationCount.getText().trim().isEmpty();
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
                // If user cancels or leaves blank, enforce non-empty
                JOptionPane.showMessageDialog(this, label + " " + i + " cannot be empty.");
                i--; // retry same index
            }
        }
        return list;
    }
}
