import javax.swing.*;

public class EMHASSEARCHPATIENTGUI extends JFrame {
    private MAINSYSTEM mainsystem;

    public EMHASSEARCHPATIENTGUI(MAINSYSTEM mainsystem) {
        this.mainsystem = mainsystem;
        setTitle("Search Patient");
        setSize(400, 300);
        setLocationRelativeTo(null);

        String patientId = JOptionPane.showInputDialog(this, "Enter Patient ID:");
        if (patientId != null && !patientId.trim().isEmpty()) {
            // Show patient details
            JOptionPane.showMessageDialog(this, mainsystem.SEARCH_PATIENT(patientId).toString());

            // ✅ Log the access attempt only if patientId is valid
            mainsystem.LOG_ACCESS(
                mainsystem.GET_CURRENT_FULLNAME(),   // comes from login
                mainsystem.GET_CURRENT_PROFESSION(), // comes from login
                patientId,
                "Search Patient Record"
            );
        }
    }
}
