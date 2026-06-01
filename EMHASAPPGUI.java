import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EMHASAPPGUI {
    public static void showMenu(String role, MAINSYSTEM mainsystem, String fullName, String profession) {

        JFrame frame = new JFrame("EMHAS Menu - " + role);
        frame.setSize(1100, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Banner
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel mediRushLabel = new JLabel("<html><b><font color='red'>MEDI</font> <font color='blue'>RUSH!</font></b></html>");
        mediRushLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        topPanel.add(mediRushLabel, BorderLayout.WEST);

        JLabel title = new JLabel("Welcome " + fullName + " (" + role + ")", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(title, BorderLayout.CENTER);

        // Search bar (top right)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField searchField = new JTextField(15);
        JButton searchBtn = new JButton("Search Patient");
        searchPanel.add(new JLabel("Enter Patient ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        topPanel.add(searchPanel, BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);

        // Patient list
        String[] columns = {"Patient ID", "Name", "Age", "Gender"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        // Initial load
        List<String[]> patients = mainsystem.GET_PATIENT_LIST();
        for (String[] row : patients) model.addRow(row);

        // ✅ Refresh button on top-left of patient list
        JPanel listTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton refreshBtn = new JButton("Refresh");
        listTopPanel.add(refreshBtn);

        // Wrap patient list + refresh button together
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(listTopPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Details panel
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane detailsScroll = new JScrollPane(detailsArea);
        detailsScroll.setPreferredSize(new Dimension(350, 0));

        // Search button action
        searchBtn.addActionListener(e -> {
            String patientId = searchField.getText().trim();
            if (patientId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a Patient ID.");
                return;
            }

            List<String> logs = mainsystem.SEARCH_PATIENT(patientId);
            detailsArea.setText("");
            if (logs.isEmpty()) {
                detailsArea.setText("No details found for Patient ID: " + patientId);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("============================================\n");
                sb.append("             PATIENT DETAILS\n");
                sb.append("============================================\n");
                for (String log : logs) {
                    sb.append(log).append("\n");
                }
                sb.append("============================================\n");
                detailsArea.setText(sb.toString());

                // Log search access
                mainsystem.LOG_ACCESS(fullName, profession, patientId, "Search Patient Record");
            }
        });

        // Buttons (bottom row, aligned horizontally)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        JButton editBtn = new JButton("Edit Selected Patient");
        JButton registerBtn = new JButton("Register New Patient");
        JButton logsBtn = new JButton("View Access Logs");
        JButton emergencyBtn = new JButton("Emergency Access");
        JButton registerUserBtn = new JButton("Register User");
        JButton backBtn = new JButton("Return To Main Menu");

        // Edit action
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String patientId = (String) model.getValueAt(row, 0);
                new EMHASEDITPATIENTGUI(mainsystem, patientId, fullName, profession);
                mainsystem.LOG_ACCESS(fullName, profession, patientId, "Edit Patient Record");
            } else {
                JOptionPane.showMessageDialog(frame, "Select a patient first.");
            }
        });

        registerBtn.addActionListener(e -> new EMHASREGISTERPATIENTGUI(mainsystem));

        logsBtn.addActionListener(e -> new EMHASVIEWACCESSLOGSGUI(mainsystem, fullName, profession));

        // Emergency action
        emergencyBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String patientId = (String) model.getValueAt(row, 0);
                new EMHASEMERGENCYACCESSGUI(mainsystem, patientId, fullName, profession);
                mainsystem.LOG_ACCESS(fullName, profession, patientId, "Emergency Record Access");
            } else {
                JOptionPane.showMessageDialog(frame, "Select a patient first.");
            }
        });

        registerUserBtn.addActionListener(e -> new EMHASREGISTERUSERGUI(mainsystem));
        backBtn.addActionListener(e -> frame.dispose());

        // ✅ Refresh action
        refreshBtn.addActionListener(e -> {
            model.setRowCount(0); // clear table
            List<String[]> refreshedPatients = mainsystem.GET_PATIENT_LIST();
            for (String[] row : refreshedPatients) {
                model.addRow(row);
            }
            JOptionPane.showMessageDialog(frame, "Patient list refreshed!");
        });

        // Role-based buttons
        if (role.equalsIgnoreCase("Admin")) {
            buttonPanel.add(editBtn);
            buttonPanel.add(registerBtn);
            buttonPanel.add(logsBtn);
            buttonPanel.add(emergencyBtn);
            buttonPanel.add(registerUserBtn);
            buttonPanel.add(backBtn);
        } else {
            buttonPanel.add(editBtn);
            buttonPanel.add(registerBtn);
            buttonPanel.add(emergencyBtn);
            buttonPanel.add(backBtn);
        }

        frame.add(centerPanel, BorderLayout.CENTER); // patient list + refresh button
        frame.add(detailsScroll, BorderLayout.EAST);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
