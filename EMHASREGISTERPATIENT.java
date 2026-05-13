import java.util.*;

public class EMHASREGISTERPATIENT {
    private Scanner INPUT;
    private MAINSYSTEM mainsystem;

    public EMHASREGISTERPATIENT(Scanner INPUT, MAINSYSTEM mainsystem){
        this.INPUT = INPUT;
        this.mainsystem = mainsystem;
    }

    public void REGISTERPATIENTS(){
        System.out.println();
        System.out.print("Enter Patient ID: ");
        String patientid = INPUT.nextLine();

        System.out.println();
        System.out.print("Enter First Name: ");
        String fname = INPUT.nextLine();

        System.out.println();
        System.out.print("Enter Last Name: ");
        String lname = INPUT.nextLine();

        System.out.println();
        System.out.print("Enter Middle Initial: ");
        String mi = INPUT.nextLine();

        System.out.println();
        System.out.print("Enter Emergency Contact: ");
        String emergencycontact = INPUT.nextLine();

        System.out.println();
        System.out.println("========================================");
        System.out.println("|          BLOOD TYPE OPTIONS          |");
        System.out.println("|======================================|");
        System.out.println("|       1. O+                          |");
        System.out.println("|       2. O-                          |");
        System.out.println("|       3. A+                          |");
        System.out.println("|       4. A-                          |");
        System.out.println("|       5. B+                          |");
        System.out.println("|       6. B-                          |");
        System.out.println("|       7. AB+                         |");
        System.out.println("|       8. AB-                         |");
        System.out.println("========================================");

        System.out.println();
        System.out.print("Enter Blood Type: ");
        int bloodtypeoption = INPUT.nextInt();
        INPUT.nextLine();

        String bloodtype = "";
        if (bloodtypeoption == 1){
            bloodtype = "O+";
        } else if (bloodtypeoption == 2){
            bloodtype = "O-";
        } else if (bloodtypeoption == 3){
            bloodtype = "A+";
        } else if (bloodtypeoption == 4){
            bloodtype = "A-";
        } else if (bloodtypeoption == 5){
            bloodtype = "B+";
        } else if (bloodtypeoption == 6){
            bloodtype = "B-";
        } else if (bloodtypeoption == 7){
            bloodtype = "AB+";
        } else if (bloodtypeoption == 8){
            bloodtype = "AB-";
        } else {
            System.out.println();
            System.out.println("Invalid Choice. Choose Options 1-8 Only.");
            return;
        }   

        System.out.println();
        System.out.print("Enter how many allergies: ");
        int allergycount = INPUT.nextInt();
        INPUT.nextLine();
        List<String> allergies = new ArrayList<>();
        for (int i = 1; i < allergycount + 1; i++){
            System.out.println();
            System.out.print("Enter allergy #" + i + ": ");
            allergies.add(INPUT.nextLine());
        }

        System.out.println();
        System.out.print("Enter how many conditions: ");
        int conditioncount = INPUT.nextInt();
        INPUT.nextLine();
        List<String> conditions = new ArrayList<>();
        for (int i = 1; i < conditioncount + 1; i++){
            System.out.println();
            System.out.print("Enter condition #" + i + ": ");
            conditions.add(INPUT.nextLine());
        }

        System.out.println();
        System.out.print("Enter how many medication: ");
        int medicationcount = INPUT.nextInt();
        INPUT.nextLine();
        List<String> medications = new ArrayList<>();
        for (int i = 1; i < medicationcount + 1; i++){
            System.out.println();
            System.out.print("Enter medication #" + i + ": ");
            medications.add(INPUT.nextLine());

        }

        boolean VALID = mainsystem.REGISTER_PATIENT(patientid, fname, lname, mi, emergencycontact, bloodtype, allergies, conditions, medications);

        if (VALID){
            System.out.println();
            System.out.println("Patient registered successfully!");
        } else {
            System.out.println();
            System.out.println("Failed to register patient.");
        }
    }
}
