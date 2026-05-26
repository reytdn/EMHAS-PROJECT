import java.util.*;

public class EMHASREGISTERPATIENT {

    private Scanner INPUT;
    private MAINSYSTEM mainsystem;

    public EMHASREGISTERPATIENT(Scanner INPUT, MAINSYSTEM mainsystem){
        this.INPUT = INPUT;
        this.mainsystem = mainsystem;
    }

    public void REGISTERPATIENTS(){



        // Patient ID
        System.out.println();
        System.out.print("Enter Patient ID: ");
        String patientid = INPUT.nextLine();

        // First Name
        System.out.println();
        System.out.print("Enter First Name: ");
        String fname = INPUT.nextLine();

        // Last Name
        System.out.println();
        System.out.print("Enter Last Name: ");
        String lname = INPUT.nextLine();

        // Middle Initial
        System.out.println();
        System.out.print("Enter Middle Initial: ");
        String mi = INPUT.nextLine();

        // Age
        System.out.println();
        System.out.print("Enter Age: ");
        int age = INPUT.nextInt();
        INPUT.nextLine();

        // Date of Birth - Month Menu
        System.out.println();
        System.out.println("====================================");
        System.out.println("|        BIRTH MONTH OPTIONS       |");
        System.out.println("|==================================|");
        System.out.println("| 1. January                       |");
        System.out.println("| 2. February                      |");
        System.out.println("| 3. March                         |");
        System.out.println("| 4. April                         |");
        System.out.println("| 5. May                           |");
        System.out.println("| 6. June                          |");
        System.out.println("| 7. July                          |");
        System.out.println("| 8. August                        |");
        System.out.println("| 9. September                     |");
        System.out.println("| 10. October                      |");
        System.out.println("| 11. November                     |");
        System.out.println("| 12. December                     |");
        System.out.println("====================================");
        System.out.println();
        System.out.print("Enter Birth Month (1-12): ");
        int dob_month_option = INPUT.nextInt();
        INPUT.nextLine();

        String dob_month = "";
        if (dob_month_option == 1){
            dob_month = "January";
        } else if (dob_month_option == 2){
            dob_month = "February";
        } else if (dob_month_option == 3){
            dob_month = "March";
        } else if (dob_month_option == 4){ 
            dob_month = "April";
        } else if (dob_month_option == 5){
            dob_month = "May";
        } else if (dob_month_option == 6){
            dob_month = "June";
        } else if (dob_month_option == 7){
            dob_month = "July";
        } else if (dob_month_option == 8){
            dob_month = "August";
        } else if (dob_month_option == 9){
            dob_month = "September";
        } else if (dob_month_option == 10){
            dob_month = "October";
        } else if (dob_month_option == 11){
            dob_month = "November";
        } else if (dob_month_option == 12){
            dob_month = "December";
        } else {
            System.out.println();
            System.out.println("Invalid choice. Please enter 1-12 only.");
            return;
        }

        System.out.println();
        System.out.print("Enter Birth Day: ");
        int dob_day = INPUT.nextInt();

        System.out.println();
        System.out.print("Enter Birth Year: ");
        int dob_year = INPUT.nextInt();
        INPUT.nextLine();

        // Gender choice
        System.out.println();
        System.out.print("Enter Gender (M/F): ");
        String genderChoice = INPUT.nextLine().trim().toUpperCase();
        String gender = "";
        if (genderChoice.equalsIgnoreCase("M")) {
            gender = "Male";
        } else if (genderChoice.equalsIgnoreCase("F")) {
            gender = "Female";
        } else {
            System.out.println();
            System.out.println("Invalid choice. Please enter M or F only.");
            return;
        }


        // Emergency Contact
        System.out.println();
        System.out.print("Enter Emergency Contact: ");
        String emergencycontact = INPUT.nextLine();

        // Address
        System.out.println();
        System.out.print("Enter Barangay: ");
        String barangay = INPUT.nextLine();

        System.out.println();
        System.out.print("Enter City: ");
        String city = INPUT.nextLine();

        System.out.println();
        System.out.print("Enter Province: ");
        String province = INPUT.nextLine();

        // Blood Type Options
        System.out.println();
        System.out.println("==============================");
        System.out.println("|     BLOOD TYPE OPTIONS     |");
        System.out.println("|============================|");
        System.out.println("| 1. O+                      |");
        System.out.println("| 2. O-                      |");
        System.out.println("| 3. A+                      |");
        System.out.println("| 4. A-                      |");
        System.out.println("| 5. B+                      |");
        System.out.println("| 6. B-                      |");
        System.out.println("| 7. AB+                     |");
        System.out.println("| 8. AB-                     |");
        System.out.println("==============================");
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

        // Allergies
        System.out.println();
        System.out.print("Enter how many allergies: ");
        int allergycount = INPUT.nextInt();
        INPUT.nextLine();
        List<String> allergies = new ArrayList<>();
        for (int i = 1; i <= allergycount; i++){
            System.out.println();
            System.out.print("Enter Allergy #" + i + ": ");
            allergies.add(INPUT.nextLine());
        }

        // Conditions
        System.out.println();
        System.out.print("Enter how many conditions: ");
        int conditioncount = INPUT.nextInt();
        INPUT.nextLine();
        List<String> conditions = new ArrayList<>();
        for (int i = 1; i <= conditioncount; i++){
            System.out.println();
            System.out.print("Enter Condition #" + i + ": ");
            conditions.add(INPUT.nextLine());
        }

        // Medications
        System.out.println();
        System.out.print("Enter how many medications: ");
        int medicationcount = INPUT.nextInt();
        INPUT.nextLine();
        List<String> medications = new ArrayList<>();
        for (int i = 1; i <= medicationcount; i++){
            System.out.println();
            System.out.print("Enter Medication #" + i + ": ");
            medications.add(INPUT.nextLine());
        }

        System.out.println();
        System.out.print("Enter how many Family Medical Conditions: ");
        int familycount = INPUT.nextInt();
        INPUT.nextLine();
        // Family Medical History
        List<String> familyhistory = new ArrayList<>();
        for (int i = 1; i <= familycount; i++){
            System.out.println();
            System.out.print("Enter Family Medical Condition #" + i + ": ");
            familyhistory.add(INPUT.nextLine());
        }
            
        System.out.println();
        System.out.print("Enter how many Immunizations: ");
        int immunizationcount = INPUT.nextInt();
        INPUT.nextLine();
        // Family Medical History
        List<String> immunization = new ArrayList<>();
        for (int i = 1; i <= immunizationcount; i++){
            System.out.println();
            System.out.print("Enter Immunization #" + i + ": ");
            immunization.add(INPUT.nextLine());
        }
            

        // Send data to main system
        boolean VALID = mainsystem.REGISTER_PATIENT(
            patientid, fname, lname, mi,
            dob_month, dob_day, dob_year, gender, age,
            emergencycontact, bloodtype,
            barangay, city, province,
            allergies, conditions, medications,
            familyhistory, immunization
        );

        if (VALID){
            System.out.println("Patient registered successfully!");
        } else {
            System.out.println("Failed to register patient.");
        }
    }
}
