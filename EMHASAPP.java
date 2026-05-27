import java.util.*;

public class EMHASAPP {
    // Updated signature: now accepts username, full name, and profession
    public static void second(String Role, MAINSYSTEM mainsystem, Scanner INPUT,
                               String currentFullName, String currentUserProfession) {

        while (true) {
            if (Role.equalsIgnoreCase("Admin")) {
                mainsystem.SHOW_PATIENTS();
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

                int Option = -1;
                boolean valid = false;

                // Error trap loop for Admin menu
                while (!valid) {
                    System.out.println();
                    System.out.print("Choose Option: ");
                    try {
                        Option = INPUT.nextInt();
                        INPUT.nextLine(); // clear buffer
                        if (Option >= 1 && Option <= 7) {
                            valid = true;
                        } else {
                            System.out.println();
                            System.out.println("Invalid Choice. Please enter 1-7 only.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("Invalid input! Please enter numbers only (1-7).");
                        INPUT.nextLine(); // clear invalid input
                    }
                }

                if (Option == 1) {
                    EMHASREGISTERPATIENT registerpat = new EMHASREGISTERPATIENT(INPUT, mainsystem);
                    registerpat.REGISTERPATIENTS();

                } else if (Option == 2) {
                    EMHASSEARCHPATIENT searchpatient = new EMHASSEARCHPATIENT(mainsystem, INPUT);
                    String patientId = searchpatient.SEARCHPATIENT();
                    mainsystem.LOG_ACCESS(currentFullName, currentUserProfession, patientId, "Search Patient Record");

                } else if (Option == 3) {
                    EMHASEDITPATIENT editpatient = new EMHASEDITPATIENT(mainsystem, INPUT);
                    String patientId = editpatient.EDITPATIENT();
                    mainsystem.LOG_ACCESS(currentFullName, currentUserProfession, patientId, "Edit Patient Record");

                } else if (Option == 4) {
                    System.out.println();
                    System.out.print("Enter Patient ID To View Details: ");
                    String patientId = INPUT.nextLine();
                    if (!mainsystem.IS_PATIENT_REGISTERED(patientId)) {
                        System.out.println("Error: Patient ID " + patientId + " is not registered.");
                    } else {
                        PatientRecord record = mainsystem.ACCESS_EMERGENCY(patientId);
                        if (record == null) {
                        System.out.println("No records found for Patient ID: " + patientId);
                        } else {
                        EMHASEMERGENCYACCESS.ACCESSEMERGENCY(
                        patientId,
                        record.getName(),
                        record.getBloodType(),
                        record.getEmergencyContact(),
                        record.getAllergies(),
                        record.getConditions(),
                        record.getMedications(),
                        record.getFamilyHistory(),
                        record.getImmunizations()
                        );
                        mainsystem.LOG_ACCESS(currentFullName, currentUserProfession, patientId, "Emergency Record Access");
                        }

                    }

                } else if (Option == 5) {
                    System.out.println();
                    System.out.print("Enter Patient ID To View Acccess Logs: ");
                    String patientId = INPUT.nextLine().trim();
                    if (patientId.isEmpty()) {
                        System.out.println();
                        System.out.println("Patient ID cannot be empty.");
                    } else {
                        EMHASVIEWACCESSLOGS viewLogs = new EMHASVIEWACCESSLOGS(mainsystem);
                        viewLogs.SHOW_LOGS_FOR_PATIENT(patientId);
                    }
                } else if (Option == 6) {
                    EMHASREGISTERUSER registeruser = new EMHASREGISTERUSER(INPUT, mainsystem);
                    registeruser.REGISTERPERSONNELS();
                } else if (Option == 7) {
                    System.out.println();
                    System.out.println("Returning Back To Main Menu.....");
                    break;
                }

            } else if (Role.equalsIgnoreCase("User")) {
                mainsystem.SHOW_PATIENTS();
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

                int Option = -1;
                boolean valid = false;

                // Error trap loop for User menu
                while (!valid) {
                    System.out.println();
                    System.out.print("Choose Option: ");
                    try {
                        Option = INPUT.nextInt();
                        INPUT.nextLine(); // clear buffer
                        if (Option >= 1 && Option <= 5) {
                            valid = true;
                        } else {
                            System.out.println();
                            System.out.println("Invalid Choice. Please enter 1-5 only.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("Invalid Choice. Please enter 1-5 only.");
                        INPUT.nextLine(); // clear invalid input
                    }
                }

                if (Option == 1) {
                    EMHASREGISTERPATIENT registerpat = new EMHASREGISTERPATIENT(INPUT, mainsystem);
                    registerpat.REGISTERPATIENTS();

                } else if (Option == 2) {
                    EMHASSEARCHPATIENT searchpatient = new EMHASSEARCHPATIENT(mainsystem, INPUT);
                    String patientId = searchpatient.SEARCHPATIENT();
                    mainsystem.LOG_ACCESS(currentFullName, currentUserProfession, patientId, "Search Patient Record");

                } else if (Option == 3) {
                    EMHASEDITPATIENT editpatient = new EMHASEDITPATIENT(mainsystem, INPUT);
                    String patientId = editpatient.EDITPATIENT();
                    mainsystem.LOG_ACCESS(currentFullName, currentUserProfession, patientId, "Edit Patient Record");

                } else if (Option == 4) {
                    System.out.println();
                    System.out.print("Enter Patient ID To View Details: ");
                    String patientId = INPUT.nextLine();
                    if (!mainsystem.IS_PATIENT_REGISTERED(patientId)) {
                        System.out.println("Error: Patient ID " + patientId + " is not registered.");
                    } else {
                        PatientRecord record = mainsystem.ACCESS_EMERGENCY(patientId);
                        if (record == null) {
                        System.out.println("No records found for Patient ID: " + patientId);
                        } else {
                        EMHASEMERGENCYACCESS.ACCESSEMERGENCY(
                        patientId,
                        record.getName(),
                        record.getBloodType(),
                        record.getEmergencyContact(),
                        record.getAllergies(),
                        record.getConditions(),
                        record.getMedications(),
                        record.getFamilyHistory(),
                        record.getImmunizations()
                        );
                        mainsystem.LOG_ACCESS(currentFullName, currentUserProfession, patientId, "Emergency Record Access");
                        }

                    }

                } else if (Option == 5) {
                    System.out.println();
                    System.out.println("Returning Back To Main Menu.....");
                    break;
                }
            }
        }
    }
}
