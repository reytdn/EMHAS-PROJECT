import java.util.*;

public class EMHASVIEWACCESSLOGS {

    private MAINSYSTEM mainsystem;

    public EMHASVIEWACCESSLOGS(MAINSYSTEM mainsystem) {

        this.mainsystem = mainsystem;
    }

    public void SHOW_ALL_LOGS() {
        List<String> logs = mainsystem.GET_ALL_ACCESS_LOGS();

        if (logs.isEmpty()) {
            System.out.println("\nNo emergency access logs found.");
        } else {
            System.out.println("\n===== Emergency Access Logs =====");
            System.out.println();
            for (String log : logs) {
                System.out.println(log);
            }
        }
    }

    
}
