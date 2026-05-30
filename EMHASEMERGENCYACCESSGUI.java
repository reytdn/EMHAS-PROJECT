import javax.swing.*;
import java.util.List;

public class EMHASEMERGENCYACCESSGUI extends JFrame {
    private MAINSYSTEM mainsystem;
    private String patientId;
    private String currentFullName;
    private String currentProfession;

    // ✅ Constructor now matches the call in EMHASAPPGUI
    public EMHASEMERGENCYACCESSGUI(MAINSYSTEM mainsystem, String patientId, String fullName, String profession) {
        this.mainsystem = mainsystem;
        this.patientId = patientId;
        this.currentFullName = fullName;
        this.currentProfession = profession;

        // Load patient details
        String[] details = mainsystem.GET_PATIENT_DETAILS(patientId);
        List<String> allergies = mainsystem.GET_ALLERGIES(patientId);
        List<String> conditions = mainsystem.GET_CONDITIONS(patientId);
        List<String> medications = mainsystem.GET_MEDICATIONS(patientId);
        List<String> familyHistory = mainsystem.GET_PEDIGREE(patientId);
        List<String> immunizations = mainsystem.GET_IMMUNIZATIONS(patientId);

        // Generate and open PDF
        EMHASEMERGENCYACCESS.ACCESSEMERGENCY(
                patientId,
                details[1], // name
                details[4], // blood type
                details[5], // emergency contact
                allergies,
                conditions,
                medications,
                familyHistory,
                immunizations
        );

        // Close the GUI window automatically after PDF is opened
        dispose();
    }
}
