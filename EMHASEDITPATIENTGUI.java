import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class EMHASEDITPATIENTGUI extends JFrame {
    private MAINSYSTEM mainsystem;
    private String patientId;
    private JTable basicTable, criticalTable;
    private DefaultTableModel basicModel, criticalModel;
    private JLabel patientIdLabel;
    private String currentFullName;
    private String currentProfession;

    public EMHASEDITPATIENTGUI(MAINSYSTEM mainsystem, String patientId, String currentFullName, String currentProfession) {
        this.mainsystem = mainsystem;
        this.patientId = patientId;
        this.currentFullName = currentFullName;
        this.currentProfession = currentProfession;    

        setTitle("Edit Patient - " + patientId);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        patientIdLabel = new JLabel("Patient ID: " + patientId);
        patientIdLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftPanel.add(patientIdLabel);

        // Basic details table
        basicModel = new DefaultTableModel(new String[]{"Field", "Value"}, 0);
        basicTable = new JTable(basicModel);
        refreshBasicDetails();
        leftPanel.add(new JScrollPane(basicTable));

        leftPanel.add(Box.createVerticalStrut(5));

        // Critical data table with 5 columns
        criticalModel = new DefaultTableModel(new String[]{
            "Allergies", "Conditions", "Medications", "Pedigrees", "Immunizations"
        }, 0);
        refreshCriticalData();
        criticalTable = new JTable(criticalModel);
        criticalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        criticalTable.setCellSelectionEnabled(true);
        leftPanel.add(new JScrollPane(criticalTable));

        // Right panel with menu buttons
        JPanel rightPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        JButton addBtn = new JButton("Add Critical Data");
        JButton deleteBtn = new JButton("Delete Critical Data");
        JButton updateBtn = new JButton("Update Patient Data");
        JButton updateCriticalBtn = new JButton("Update Critical Data");
        JButton backBtn = new JButton("Back To Main Menu");

        addBtn.addActionListener(e -> showAddDialog());
        deleteBtn.addActionListener(e -> showDeleteDialog());
        updateBtn.addActionListener(e -> showUpdateDialog());
        updateCriticalBtn.addActionListener(e -> showUpdateCriticalDialog());
        backBtn.addActionListener(e -> dispose());

        rightPanel.add(addBtn);
        rightPanel.add(deleteBtn);
        rightPanel.add(updateBtn);
        rightPanel.add(updateCriticalBtn);
        rightPanel.add(backBtn);

        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        setVisible(true);
    }

    private void refreshBasicDetails() {
        basicModel.setRowCount(0);
        String[] details = mainsystem.GET_PATIENT_DETAILS(patientId);

        patientIdLabel.setText("Patient ID: " + details[0]);

        basicModel.addRow(new Object[]{"Name", details[1]});
        basicModel.addRow(new Object[]{"Birthdate", details[2] + " (Age: " + details[3] + ")"});
        basicModel.addRow(new Object[]{"Gender", details[4]});
        basicModel.addRow(new Object[]{"Blood Type", details[5]});
        basicModel.addRow(new Object[]{"Emergency Contact", details[6]});
        basicModel.addRow(new Object[]{"Address", details[7]});
    }

    


    private void refreshCriticalData() {
        criticalModel.setRowCount(0);
        List<String> allergies = mainsystem.GET_ALLERGIES(patientId);
        List<String> conditions = mainsystem.GET_CONDITIONS(patientId);
        List<String> medications = mainsystem.GET_MEDICATIONS(patientId);
        List<String> pedigrees = mainsystem.GET_PEDIGREE(patientId);
        List<String> immunizations = mainsystem.GET_IMMUNIZATIONS(patientId);

        int maxRows = Math.max(allergies.size(),
                        Math.max(conditions.size(),
                        Math.max(medications.size(),
                        Math.max(pedigrees.size(), immunizations.size()))));

        for (int i = 0; i < maxRows; i++) {
            criticalModel.addRow(new Object[]{
                i < allergies.size() ? allergies.get(i) : "",
                i < conditions.size() ? conditions.get(i) : "",
                i < medications.size() ? medications.get(i) : "",
                i < pedigrees.size() ? pedigrees.get(i) : "",
                i < immunizations.size() ? immunizations.get(i) : ""
            });
        }
    }

    private void showUpdateCriticalDialog() {
        int row = criticalTable.getSelectedRow();
        int col = criticalTable.getSelectedColumn();
        if (row < 0 || col < 0) {
            JOptionPane.showMessageDialog(this, "Select a cell to update.");
            return;
        }

        String category = criticalTable.getColumnName(col);
        String oldValue = (String) criticalModel.getValueAt(row, col);

        String newValue = JOptionPane.showInputDialog(this,
                "Enter new value for " + category + ":", oldValue);

        if (newValue == null || newValue.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "New value cannot be empty.");
            return;
        }

        if (category.equals("Allergies")) mainsystem.UPDATE_ALLERGIES(oldValue, newValue);
        else if (category.equals("Conditions")) mainsystem.UPDATE_CONDITIONS(oldValue, newValue);
        else if (category.equals("Medications")) mainsystem.UPDATE_MEDICATIONS(oldValue, newValue);
        else if (category.equals("Pedigrees")) mainsystem.UPDATE_PEDIGREE(oldValue, newValue);
        else if (category.equals("Immunizations")) mainsystem.UPDATE_IMMUNIZATIONS(oldValue, newValue);

        refreshCriticalData();
    }

    private void showAddDialog() {
        String[] categories = {"Allergy", "Condition", "Medication", "Family History", "Immunization"};
        String choice = (String) JOptionPane.showInputDialog(this, "Select Category:", "Add Critical Data",
                JOptionPane.QUESTION_MESSAGE, null, categories, categories[0]);
        if (choice == null) return;

        String input = JOptionPane.showInputDialog(this, "Enter new " + choice + ":");
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, choice + " cannot be empty.");
            return;
        }

        if (choice.equals("Allergy")) mainsystem.ADD_ALLERGY(patientId, input);
        else if (choice.equals("Condition")) mainsystem.ADD_CONDITION(patientId, input);
        else if (choice.equals("Medication")) mainsystem.ADD_MEDICATION(patientId, input);
        else if (choice.equals("Family History")) mainsystem.ADD_FAMILY_HISTORY(patientId, input);
        else if (choice.equals("Immunization")) mainsystem.ADD_IMMUNIZATION(patientId, input);

        refreshCriticalData();
    }


    private void showDeleteDialog() {
        int row = criticalTable.getSelectedRow();
        int col = criticalTable.getSelectedColumn();
        if (row < 0 || col < 0) {
            JOptionPane.showMessageDialog(this, "Select a cell to delete.");
            return;
        }

        String category = criticalTable.getColumnName(col);
        String value = (String) criticalModel.getValueAt(row, col);

        if (value == null || value.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No value selected.");
            return;
        }

        if (category.equals("Allergies")) mainsystem.DELETE_ALLERGY(patientId, value);
        else if (category.equals("Conditions")) mainsystem.DELETE_CONDITION(patientId, value);
        else if (category.equals("Medications")) mainsystem.DELETE_MEDICATION(patientId, value);
        else if (category.equals("Pedigrees")) mainsystem.DELETE_FAMILY_HISTORY(patientId, value);
        else if (category.equals("Immunizations")) mainsystem.DELETE_IMMUNIZATION(patientId, value);

        refreshCriticalData();
    }

    private void showUpdateDialog() {
        int row = basicTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a basic detail row to update.");
            return;
        }
        String field = (String) basicModel.getValueAt(row, 0);

        if (field.equals("Name")) {
            updateName();
        } else if (field.equals("Address")) {
            updateAddress();
        } else if (field.equals("Birthdate")) {
            updateBirthdate();
        } else if (field.equals("Gender")) {
            updateGender();
        } else if (field.equals("Blood Type")) {
            updateBloodType();
        } else if (field.equals("Emergency Contact")) {
            String oldValue = (String) basicModel.getValueAt(row, 1);
            String newValue = JOptionPane.showInputDialog(this, "Enter new Emergency Contact:", oldValue);
            if (newValue != null && !newValue.trim().isEmpty()) {
                mainsystem.UPDATE_EMERGENCYCONTACT(patientId, newValue);
                refreshBasicDetails();
            }
        }
    }

    private void updateName() {
        JTextField fnameField = new JTextField();
        JTextField lnameField = new JTextField();

        // ✅ MI scroll box A–Z + blank option
        String[] letters = new String[27];
        letters[0] = ""; // blank option for no middle initial
        for (int i = 1; i <= 26; i++) {
            letters[i] = String.valueOf((char) ('A' + (i - 1)));
        }
        JComboBox<String> miBox = new JComboBox<>(letters);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("First Name:")); panel.add(fnameField);
        panel.add(new JLabel("Middle Initial:")); panel.add(miBox);
        panel.add(new JLabel("Last Name:")); panel.add(lnameField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Name", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String fname = fnameField.getText().trim();
            String mi = (String) miBox.getSelectedItem(); // always one letter or blank
            String lname = lnameField.getText().trim();

            if (fname.isEmpty() || lname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "First and Last Name must be filled.");
                return;
            }

            // ✅ Update in mainsystem
            mainsystem.UPDATE_NAME(patientId, fname, mi, lname);

            // ✅ Refresh table after update
            refreshBasicDetails();
        }
    }


    private void updateBloodType() {
        String[] bloodTypes = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
        JComboBox<String> bloodBox = new JComboBox<>(bloodTypes);

        int result = JOptionPane.showConfirmDialog(this, bloodBox, "Update Blood Type", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String newBloodType = (String) bloodBox.getSelectedItem();
            mainsystem.UPDATE_BLOODTYPE(patientId, newBloodType);
            refreshBasicDetails();
        }
    }

    private void updateGender() {
        JRadioButton maleBtn = new JRadioButton("Male");
        JRadioButton femaleBtn = new JRadioButton("Female");
        ButtonGroup group = new ButtonGroup();
        group.add(maleBtn);
        group.add(femaleBtn);

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(maleBtn);
        panel.add(femaleBtn);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Gender", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (maleBtn.isSelected()) {
                mainsystem.UPDATE_GENDER(patientId, "Male");
            } else if (femaleBtn.isSelected()) {
                mainsystem.UPDATE_GENDER(patientId, "Female");
            }
            refreshBasicDetails();
        }
    }

    private void updateAddress() {
        JTextField barangayField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField provinceField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Barangay:")); panel.add(barangayField);
        panel.add(new JLabel("City:")); panel.add(cityField);
        panel.add(new JLabel("Province:")); panel.add(provinceField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Address", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String barangay = barangayField.getText().trim();
            String city = cityField.getText().trim();
            String province = provinceField.getText().trim();

            if (barangay.isEmpty() || city.isEmpty() || province.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            // ✅ Append " City" to city
            if (!city.endsWith(" City")) {
                city = city + " City";
            }

            mainsystem.UPDATE_ADDRESS(patientId, barangay, city, province);
            refreshBasicDetails();
        }
    }

    private void updateBirthdate() {
        String[] months = {"January","February","March","April","May","June",
                        "July","August","September","October","November","December"};
        JComboBox<String> monthBox = new JComboBox<>(months);
        JTextField dayField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField ageField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0,2,5,5));
        panel.add(new JLabel("Birth Month:")); panel.add(monthBox);
        panel.add(new JLabel("Birth Day:")); panel.add(dayField);
        panel.add(new JLabel("Birth Year:")); panel.add(yearField);
        panel.add(new JLabel("Age:")); panel.add(ageField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Birthdate & Age", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int dob_month_option = monthBox.getSelectedIndex() + 1; // ✅ store numeric month
                int dob_day = Integer.parseInt(dayField.getText().trim());
                int dob_year = Integer.parseInt(yearField.getText().trim());
                int age = Integer.parseInt(ageField.getText().trim());

                int currentYear = LocalDate.now().getYear();
                if (dob_year < 1900 || dob_year > currentYear) {
                    JOptionPane.showMessageDialog(this, "Invalid Year.");
                    return;
                }

                int maxDays = 31;
                if (dob_month_option == 4 || dob_month_option == 6 ||
                    dob_month_option == 9 || dob_month_option == 11) {
                    maxDays = 30;
                } else if (dob_month_option == 2) {
                    boolean leap = (dob_year % 4 == 0 && dob_year % 100 != 0) || (dob_year % 400 == 0);
                    maxDays = leap ? 29 : 28;
                }
                if (dob_day < 1 || dob_day > maxDays) {
                    JOptionPane.showMessageDialog(this, "Invalid Day for Selected Month.");
                    return;
                }

                if (age < 0 || age > 150) {
                    JOptionPane.showMessageDialog(this, "Age must be between 0 and 150.");
                    return;
                }

                LocalDate birth = LocalDate.of(dob_year, dob_month_option, dob_day);
                LocalDate today = LocalDate.now();
                int computedAge = Period.between(birth, today).getYears();

                if (age == computedAge) {
                    // ✅ Save numeric month to DB
                    mainsystem.UPDATE_BIRTHDATE(patientId, String.valueOf(dob_month_option), dob_day, dob_year);
                    mainsystem.UPDATE_AGE(patientId, age);
                    JOptionPane.showMessageDialog(this, "Update Successful");
                    refreshBasicDetails();
                } else {
                    JOptionPane.showMessageDialog(this, "Entered Age does not match computed Age from Birthdate.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid Input! Numbers Only.");
            }
        }
    }
}
    
