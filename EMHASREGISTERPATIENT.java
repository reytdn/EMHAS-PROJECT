import java.util.*;

public class EMHASREGISTERPATIENT {
 
    // scanner for user input
    private Scanner INPUT;
 
    // reference to main system where patient data is stored
    private MAINSYSTEM mainsystem;
 
    // constructor to connect scanner and main system
    public EMHASREGISTERPATIENT(Scanner INPUT, MAINSYSTEM mainsystem){
        this.INPUT = INPUT;
        this.mainsystem = mainsystem;
    }
 
    // method for registering a patient
    public void REGISTERPATIENTS(){
 
        // input patient id
        System.out.println();
        System.out.print("Enter Patient ID: ");
        String patientid = INPUT.nextLine();
 
        // input first name
        System.out.println();
        System.out.print("Enter First Name: ");
        String fname = INPUT.nextLine();
 
        // input last name
        System.out.println();
        System.out.print("Enter Last Name: ");
        String lname = INPUT.nextLine();
 
        // input middle initial
        System.out.println();
        System.out.print("Enter Middle Initial: ");
        String mi = INPUT.nextLine();
 
        // input emergency contact
        System.out.println();
        System.out.print("Enter Emergency Contact: ");
        String emergencycontact = INPUT.nextLine();
 
        // show blood type options (all blood types andito)
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
 
        // choose blood type
        System.out.println();
        System.out.print("Enter Blood Type: ");
        int bloodtypeoption = INPUT.nextInt();
        INPUT.nextLine(); // clear buffer
 
        // store blood type
        String bloodtype = "";
 
        // assign blood type based on option
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
 
            // invalid input
            System.out.println();
            System.out.println("Invalid Choice. Choose Options 1-8 Only.");
            return;
        }  
 
        // input number of allergies
        System.out.println();
        System.out.print("Enter how many allergies: ");
        int allergycount = INPUT.nextInt();
        INPUT.nextLine();
 
        // store allergies list
        List<String> allergies = new ArrayList<>();
 
        // loop for allergy input
        for (int i = 1; i < allergycount + 1; i++){
            System.out.println();
            System.out.print("Enter allergy #" + i + ": ");
            allergies.add(INPUT.nextLine());
        }
 
        // input number of conditions
        System.out.println();
        System.out.print("Enter how many conditions: ");
        int conditioncount = INPUT.nextInt();
        INPUT.nextLine();
 
        // store conditions list
        List<String> conditions = new ArrayList<>();
 
        // loop for condition input
        for (int i = 1; i < conditioncount + 1; i++){
            System.out.println();
            System.out.print("Enter condition #" + i + ": ");
            conditions.add(INPUT.nextLine());
        }
 
        // input number of medications
        System.out.println();
        System.out.print("Enter how many medication: ");
        int medicationcount = INPUT.nextInt();
        INPUT.nextLine();
 
        // store medications list
        List<String> medications = new ArrayList<>();
 
        // loop for medication input
        for (int i = 1; i < medicationcount + 1; i++){
            System.out.println();
            System.out.print("Enter medication #" + i + ": ");
            medications.add(INPUT.nextLine());
        }
 
        // send data to main system for registration
        boolean VALID = mainsystem.REGISTER_PATIENT(
            patientid, fname, lname, mi, emergencycontact, bloodtype, allergies, conditions, medications
        );
 
        // check if registration is successful
        if (VALID){
            System.out.println();
            System.out.println("Patient registered successfully!");
        } else {
            System.out.println();
            System.out.println("Failed to register patient.");
        }
    }
}