import java.util.*;
import java.time.LocalDate;
import java.time.Period;

public class EMHASREGISTERPATIENT {

    private Scanner INPUT;
    private MAINSYSTEM mainsystem;

    public EMHASREGISTERPATIENT(Scanner INPUT, MAINSYSTEM mainsystem){
        this.INPUT = INPUT;
        this.mainsystem = mainsystem;
    }

    public void REGISTERPATIENTS(){

        String patientid;
        while (true) {
        System.out.println();
        System.out.print("Enter Patient ID: ");
        patientid = INPUT.nextLine().trim();

        if (!patientid.isEmpty()){
            break;
        } else {
            System.out.println();
            System.out.println("Patient ID cannot be empty.");
        }
    }

    // FIRST NAME
        String fname;
        while(true){
            System.out.println();
            System.out.print("Enter First Name: ");
            fname = INPUT.nextLine().trim();

            if(fname.matches("[a-zA-Z ]+")){
                break;
            } else {
                System.out.println();
                System.out.println("Letters only.");
            }
        }

        // LAST NAME
        String lname;
        while(true){
            System.out.println();
            System.out.print("Enter Last Name: ");
            lname = INPUT.nextLine().trim();

            if(lname.matches("[a-zA-Z ]+")){
                break;
            } else {
                System.out.println();
                System.out.println("Letters only.");
            }
        }

        // MIDDLE INITIAL
        String mi;
        while(true){
            System.out.println();
            System.out.print("Enter Middle Initial: ");
            mi = INPUT.nextLine().trim();

            if(mi.matches("[a-zA-Z]")){
                mi = mi.toUpperCase();
                break;
            } else {
                System.out.println();
                System.out.println("1 letter only.");
            }
        }

        // MONTH (MENU KEPT)
        int dob_month_option;
        while(true){
            try{
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
                System.out.print("Enter Month (1-12): ");
                dob_month_option = INPUT.nextInt();
                INPUT.nextLine();

                if(dob_month_option >= 1 && dob_month_option <= 12){
                    break;
                } else {
                    System.out.println();
                    System.out.println("1-12 only.");
                }

            } catch(Exception e){
                System.out.println();
                System.out.println("Numbers only.");
                INPUT.nextLine();
            }
        }

        // YEAR
        int dob_year;
        while(true){
            try{
                System.out.println();
                System.out.print("Enter Birth Year: ");
                dob_year = INPUT.nextInt();
                INPUT.nextLine();

                int currentYear = java.time.LocalDate.now().getYear();

                if(dob_year >= 1900 && dob_year <= currentYear){
                    break;
                } else {
                    System.out.println();
                    System.out.println("Invalid year.");
                }

            } catch(Exception e){
                System.out.println();
                System.out.println("Numbers only.");
                INPUT.nextLine();
            }
        }

        // DAY
        int dob_day;
        while(true){
            try{
                System.out.println();
                System.out.print("Enter Day: ");
                dob_day = INPUT.nextInt();
                INPUT.nextLine();

                int maxDays = 31;

                if(dob_month_option == 4 || dob_month_option == 6 ||
                dob_month_option == 9 || dob_month_option == 11){
                    maxDays = 30;
                } else if(dob_month_option == 2){
                    boolean leap = (dob_year % 4 == 0 && dob_year % 100 != 0) ||
                                (dob_year % 400 == 0);
                    maxDays = leap ? 29 : 28;
                }

                if(dob_day >= 1 && dob_day <= maxDays){
                    break;
                } else {
                    System.out.println();
                    System.out.println("Invalid day for selected month.");
                }

            } catch(Exception e){
                System.out.println();
                System.out.println("Numbers only.");
                INPUT.nextLine();
            }
        }

        int age;
        while (true) {
            try {
                System.out.println();
                System.out.print("Enter Age: ");
                age = INPUT.nextInt();
                INPUT.nextLine();

                if (age < 0 || age > 150) {
                    System.out.println();
                    System.out.println("Age must be 0-150 only.");
                    continue;
                }

                LocalDate birth = LocalDate.of(dob_year, dob_month_option, dob_day);
                LocalDate today = LocalDate.now();

                int computedAge = Period.between(birth, today).getYears();

                // EXACT validation using full date logic
                if (age == computedAge) {
                    break;
                } else {
                    System.out.println();
                    System.out.println("Age does not match birthdate.");
                }

            } catch (Exception e) {
                System.out.println();
                System.out.println("Numbers only.");
                INPUT.nextLine();
            }
        }

        // GENDER
        String gender;
        while(true){
            System.out.println();
            System.out.print("Enter Gender (M/F): ");
            String g = INPUT.nextLine().trim().toUpperCase();

            if(g.equals("M")){
                gender = "Male";
                break;
            } else if(g.equals("F")){
                gender = "Female";
                break;
            } else {
                System.out.println();
                System.out.println("M or F only.");
            }
        }

        

        // EMERGENCY CONTACT
        String emergencycontact;
        while(true){
            System.out.println();
            System.out.print("Enter Emergency Contact (11 digits): ");
            emergencycontact = INPUT.nextLine().trim();

            if(emergencycontact.matches("\\d{11}")){
                break;
            } else {
                System.out.println();
                System.out.println("Must be 11 digits.");
            }
        }

        String barangay;
        while(true){
            System.out.println();
            System.out.print("Enter Barangay: ");
            barangay = INPUT.nextLine().trim();

            if(!barangay.isEmpty()){
                break;
            } else {
                System.out.println();
                System.out.println("Barangay cannot be empty.");
            }
        }

        // CITY
        String city;
        while(true){
            System.out.println();
            System.out.print("Enter City: ");
            city = INPUT.nextLine().trim();

            if(city.matches("[a-zA-Z ]+")){
                break;
            } else {
                System.out.println();
                System.out.println("Letters only.");
            }
        }

        // PROVINCE
        String province;
        while(true){
            System.out.println();
            System.out.print("Enter Province: ");
            province = INPUT.nextLine().trim();

            if(province.matches("[a-zA-Z ]+")){
                break;
            } else {
                System.out.println();
                System.out.println("Letters only.");
            }
        }

        // BLOOD TYPE (MENU KEPT)
        int bloodtypeoption;
        while(true){
            try{
                System.out.println();
                System.out.println("==============================");
                System.out.println("|     BLOOD TYPE OPTIONS     |");
                System.out.println("==============================");
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
                bloodtypeoption = INPUT.nextInt();
                INPUT.nextLine();

                if(bloodtypeoption >= 1 && bloodtypeoption <= 8){
                    break;
                } else {
                    System.out.println();
                    System.out.println("1-8 only.");
                }

            } catch(Exception e){
                System.out.println();
                System.out.println("Numbers only.");
                INPUT.nextLine();
            }
        }

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
        }
        
        // LIST INPUT SAFE (NO BREAK WHEN LETTER OR EMPTY)
        List<String> allergies = new ArrayList<>();
        int allergycount;

        while (true) {
            try {
                System.out.println();
                System.out.print("How many allergies: ");
                allergycount = INPUT.nextInt();
                INPUT.nextLine();

                if (allergycount >= 0) {
                    break;
                } else {
                    System.out.println();
                    System.out.println("Must be 0 or more.");
                }

            } catch (Exception e) {
                System.out.println();
                System.out.println("Numbers only.");
                INPUT.nextLine(); 
            }
        }

        for (int i = 1; i <= allergycount; i++) {
            while (true) {
                System.out.println();
                System.out.print("Allergy #" + i + ": ");
                String a = INPUT.nextLine().trim();

                if (!a.isEmpty()) {
                    allergies.add(a);
                    break;
                } else {
                    System.out.println();
                    System.out.println("Cannot be empty.");
                }
            }
        }

        List<String> conditions = new ArrayList<>();
        int conditioncount;

        while (true) {
            try {
                System.out.println();
                System.out.print("How many conditions: ");
                conditioncount = INPUT.nextInt();
                INPUT.nextLine();

                if (conditioncount >= 0) {
                    break;
                } else {
                    System.out.println();
                    System.out.println("Must be 0 or more.");
                }

            } catch (Exception e) {
                System.out.println();
                System.out.println("Numbers only.");
                INPUT.nextLine();
            }
        }

        for (int i = 1; i <= conditioncount; i++) {
            while (true) {
                System.out.println();
                System.out.print("Condition #" + i + ": ");
                String c = INPUT.nextLine().trim();

                if (!c.isEmpty()) {
                    conditions.add(c);
                    break;
                } else {
                    System.out.println();
                    System.out.println("Cannot be empty.");
                }
            }
        }


        // ================= MEDICATIONS =================
        List<String> medications = new ArrayList<>();
        int medicationcount;

        while (true) {
            try {
                System.out.println();
                System.out.print("How many medications: ");
                medicationcount = INPUT.nextInt();
                INPUT.nextLine();

                if (medicationcount >= 0) {
                    break;
                } else {
                    System.out.println();
                    System.out.println("Must be 0 or more.");
                }

            } catch (Exception e) {
                System.out.println();
                System.out.println("Numbers only.");
                INPUT.nextLine();
            }
        }

        for (int i = 1; i <= medicationcount; i++) {
            while (true) {
                System.out.println();
                System.out.print("Medication #" + i + ": ");
                String m = INPUT.nextLine().trim();

                if (!m.isEmpty()) {
                    medications.add(m);
                    break;
                } else {
                    System.out.println();
                    System.out.println("Cannot be empty.");
                }
            }
        }


        // ================= FAMILY HISTORY =================
        List<String> familyhistory = new ArrayList<>();
        int familycount;

        while (true) {
            try {
                System.out.println();
                System.out.print("How many family history: ");
                familycount = INPUT.nextInt();
                INPUT.nextLine();

                if (familycount>= 0) {
                    break;
                } else {
                    System.out.println();
                    System.out.println("Must be 0 or more.");
                }

            } catch (Exception e) {
                System.out.println();
                System.out.println("Numbers only.");
                INPUT.nextLine();
            }
        }

        for (int i = 1; i <= familycount; i++) {
            while (true) {
                System.out.println();
                System.out.print("Family history #" + i + ": ");
                String f = INPUT.nextLine().trim();

                if (!f.isEmpty()) {
                    familyhistory.add(f);
                    break;
                } else {
                    System.out.println();
                    System.out.println("Cannot be empty.");
                }
            }
        }

        // ================= IMMUNIZATION =================
        List<String> immunization = new ArrayList<>();
        int immunizationcount;

        while (true) {
            try {
                System.out.println();
                System.out.print("How many immunizations: ");
                immunizationcount = INPUT.nextInt();
                INPUT.nextLine();

                if (immunizationcount >= 0) {
                    break;
                } else {
                    System.out.println();
                    System.out.println("Must be 0 or more.");
                }

            } catch (Exception e) {
                System.out.println();
                System.out.println("Numbers only.");
                INPUT.nextLine();
            }
        }

        for (int i = 1; i <= immunizationcount; i++) {
            while (true) {
                System.out.println();
                System.out.print("Immunization #" + i + ": ");
                String im = INPUT.nextLine().trim();

                if (!im.isEmpty()) {
                    immunization.add(im);
                    break;
                } else {
                    System.out.println();
                    System.out.println("Cannot be empty.");
                }
            }
        }

        // SEND
        boolean VALID = mainsystem.REGISTER_PATIENT(
            patientid, fname, lname, mi,
            "", dob_day, dob_year, gender, age,
            emergencycontact, bloodtype,
            barangay, city, province,
            allergies, conditions, medications,
            familyhistory, immunization
        );

        if(VALID){
            System.out.println("Patient registered successfully!");
        } else {
            System.out.println("Failed to register patient.");
        }
    }
}