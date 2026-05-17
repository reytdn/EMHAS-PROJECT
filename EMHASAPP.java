import java.io.File;
import java.util.*;


public class EMHASAPP {
    public static void second(String Role, MAINSYSTEM mainsystem, Scanner INPUT) {

        while (true) {
            if (Role.equalsIgnoreCase("Admin")){
                System.out.println();
                System.out.println("========================================");
                System.out.println("|          EMHAS MENU SYSTEM           |");
                System.out.println("|======================================|");
                System.out.println("| 1. Register New Patient              |");
                System.out.println("| 2. Search Patient Record             |");
                System.out.println("| 3. Edit Patient Record               |");
                System.out.println("| 4. Emergency Record Access           |");
                System.out.println("| 5. View Emergency Access Logs        |");
                System.out.println("| 6. Register Ambulance/Hospital User  |");
                System.out.println("| 7. Return To Main Menu               |");
                System.out.println("========================================");
                System.out.println();
                System.out.print("Select Option: ");
                int Option = INPUT.nextInt();
                INPUT.nextLine();

                if (Option == 1){
                    EMHASREGISTERPATIENT registerpat = new EMHASREGISTERPATIENT(INPUT, mainsystem);
                    registerpat.REGISTERPATIENTS();
                }

                else if (Option == 2){

                }

                else if (Option == 3){

                }

                else if (Option == 4){

                }

                else if (Option == 5){
                    System.out.println();
                    System.out.print("Enter Patient ID to view logs: ");
                    String patientId = INPUT.nextLine();

                    List<String> logs = mainsystem.ACCESS_EMERGENCY(patientId);

                    if (logs.isEmpty()) {
                        System.out.println();
                        System.out.println("No records found for Patient ID: " + patientId);
                    } else {
                        // Show logs in console
                        for(String log : logs){
                            System.out.println();
                            System.out.println(log);
                        }

                        // Generate PDF with Arial font
                        EMHASEMERGENCYACCESS.ACCESSEMERGENCY(logs, patientId);

                        // Auto-open PDF
                        try {
                            File pdfFile = new File("logs_" + patientId + ".pdf");
                            if (pdfFile.exists()) {
                                java.awt.Desktop.getDesktop().open(pdfFile);
                            }
                        } catch (Exception ex) {
                            System.out.println();
                            System.out.println("Could not open PDF: " + ex.getMessage());
                        }
                    }

                }

                else if (Option == 6){
                    EMHASREGISTERUSER registeruser = new EMHASREGISTERUSER(INPUT, mainsystem);
                    registeruser.REGISTERPERSONNELS();

                }

                else if (Option == 7){
                    System.out.println();
                    System.out.println("Returning Back To Main Menu.....");
                    break;
                } else {
                    System.out.println();
                    System.out.println("Invalid Choice. Choose Options 1-7 Only.");
                    continue;
                }


            } else if (Role.equalsIgnoreCase("User")){
                System.out.println();
                System.out.println("========================================");
                System.out.println("|          EMHAS MENU SYSTEM           |");
                System.out.println("|======================================|");
                System.out.println("| 1. Register New Patient              |");
                System.out.println("| 2. Search Patient Record             |");
                System.out.println("| 3. Edit Patient Record               |");
                System.out.println("| 4. Emergency Record Access           |");
                System.out.println("| 5. Return To Main Menu               |");
                System.out.println("========================================");
                System.out.println();
                System.out.print("Select Option: ");
                int Option = INPUT.nextInt();
                INPUT.nextLine();

                if (Option == 1){
                    EMHASREGISTERPATIENT registerpat = new EMHASREGISTERPATIENT(INPUT, mainsystem);
                    registerpat.REGISTERPATIENTS();
                    
                }

                else if (Option == 2){

                }

                else if (Option == 3){

                }

                else if (Option == 4){
                    System.out.println();
                    System.out.print("Enter Patient ID to view logs: ");
                    String patientId = INPUT.nextLine();

                    List<String> logs = mainsystem.ACCESS_EMERGENCY(patientId);

                    if (logs.isEmpty()) {
                        System.out.println("No records found for Patient ID: " + patientId);
                    } else {
                        // Show logs in console
                        for(String log : logs){
                            System.out.println(log);
                        }

                        // Generate PDF with Arial font
                        EMHASEMERGENCYACCESS.ACCESSEMERGENCY(logs, patientId);

                        // Auto-open PDF
                        try {
                            File pdfFile = new File("logs_" + patientId + ".pdf");
                            if (pdfFile.exists()) {
                                java.awt.Desktop.getDesktop().open(pdfFile);
                            }
                        } catch (Exception ex) {
                            System.out.println("Could not open PDF: " + ex.getMessage());
                        }
                    }


                }

                else if (Option == 5){
                    System.out.println();
                    System.out.println("Returning Back To Main Menu.....");
                    break;
                } else {
                    System.out.println();
                    System.out.println("Invalid Choice. Choose Options 1-6 Only.");
                    continue;
                }
            }
        }
    }    
}
