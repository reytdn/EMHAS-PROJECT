import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EMHASVIEWACCESSLOGSGUI extends JFrame {
    private MAINSYSTEM mainsystem;
    private String currentFullName;
    private String currentProfession;

    // ✅ Declare models and tables at class level
    private JTable patientTable, logsTable;
    private DefaultTableModel patientModel, logsModel;

    public EMHASVIEWACCESSLOGSGUI(MAINSYSTEM mainsystem, String fullName, String profession) {
        this.mainsystem = mainsystem;
        this.currentFullName = fullName;
        this.currentProfession = profession;

        setTitle("Access Logs - " + currentProfession);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ✅ Log immediately when Admin or user opens the logs panel
        mainsystem.LOG_ACCESS(
            currentFullName,
            currentProfession,
            "N/A", // no patient ID for viewing logs
            "View Access Logs"
        );

        // Patient list table
        patientModel = new DefaultTableModel(new String[]{"Patient ID", "Name"}, 0);
        patientTable = new JTable(patientModel);
        JScrollPane patientScroll = new JScrollPane(patientTable);

        // Logs table
        logsModel = new DefaultTableModel(new String[]{
            "ID", "Full Name", "Profession", "Patient ID", "Action", "Timestamp"
        }, 0);
        logsTable = new JTable(logsModel);
        JScrollPane logsScroll = new JScrollPane(logsTable);

        // Split pane: patients on left, logs on right
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, patientScroll, logsScroll);
        splitPane.setDividerLocation(300);
        add(splitPane, BorderLayout.CENTER);

        // Load patients into list
        loadPatients();

        // Add mouse listener for row selection
        patientTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = patientTable.getSelectedRow();
                if (row >= 0) {
                    String patientId = (String) patientModel.getValueAt(row, 0);
                    loadLogsForPatient(patientId);
                }
            }
        });

        setVisible(true);
    }

    private void loadPatients() {
        patientModel.setRowCount(0);
        List<String[]> patients = mainsystem.GET_PATIENT_LIST(); 
        if (patients == null || patients.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No patients found.");
            return;
        }
        for (String[] p : patients) {
            patientModel.addRow(p);
        }
    }

    private void loadLogsForPatient(String patientId) {
        logsModel.setRowCount(0);
        List<String[]> logs = mainsystem.GET_ACCESS_LOGS_FOR_PATIENT(patientId);

        if (logs == null || logs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No access logs found for Patient ID: " + patientId);
            return;
        }

        for (String[] log : logs) {
            logsModel.addRow(log);
        }
    }
}
