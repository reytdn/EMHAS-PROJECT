import java.util.*;

public class EMHASVIEWACCESSLOGS {

    private MAINSYSTEM mainsystem;

    public EMHASVIEWACCESSLOGS(MAINSYSTEM mainsystem) {
        this.mainsystem = mainsystem;
    }

    public void SHOW_LOGS_FOR_PATIENT(String patientId) {
        // get all logs from MAINSYSTEM
        List<String> logs = mainsystem.GET_ALL_ACCESS_LOGS();

        if (logs == null || logs.isEmpty()) {
            System.out.println();
            System.out.println("No Emergency Access Logs Found.");
        } else {
            System.out.println();
            System.out.println("===== Emergency Access Logs for Patient ID: " + patientId + " =====");
            System.out.println();
            boolean found = false;
            for (String log : logs) {
                // filter only logs that match the patientId
                if (log.contains("Accessed Patient ID: " + patientId)) {
                    System.out.println(log);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No Logs Found For Patient ID: " + patientId);
            }
        }
    }
}
