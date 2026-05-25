import java.util.*;

public class EMHASEDITPATIENT {

    private Scanner INPUT;
    private MAINSYSTEM mainsystem;

    public EMHASEDITPATIENT(MAINSYSTEM mainsystem, Scanner INPUT) {
        this.mainsystem = mainsystem;
        this.INPUT = INPUT;
    }
        
    public void EDITPATIENT() {
        System.out.println();
        System.out.print("Enter Patient ID to Edit: ");
        String patientId = INPUT.nextLine();
        while (true) {
            
            System.out.println();
            System.out.println("========================================");
            System.out.println("|          EMHAS EDIT CHOICES          |");
            System.out.println("|======================================|");
            System.out.println("| 1. Add Patient Critical Data         |");
            System.out.println("| 2. Delete Patient Critical Data      |");
            System.out.println("| 3. Update Patient Data               |");
            System.out.println("| 4. Back To Main Menu                 |");
            System.out.println("========================================");
            System.out.println();
            System.out.print("Choose Option: ");
            int option = INPUT.nextInt();

            if (option == 1) {
                while (true) {
                    System.out.println();
                    System.out.println("========================================");
                    System.out.println("|       ADD PATIENT CRITICAL DATA      |");
                    System.out.println("|======================================|");
                    System.out.println("| 1. Allergies                         |");
                    System.out.println("| 2. Conditions                        |");
                    System.out.println("| 3. Medications                       |");
                    System.out.println("| 4. Family Medical History            |");
                    System.out.println("| 5. Immunizations                     |");
                    System.out.println("| 6. Back To EMHAS Edit Choices        |");
                    System.out.println("========================================");
                    System.out.println();
                    System.out.print("Choose Category: ");
                    int addchoice = INPUT.nextInt();
                    INPUT.nextLine();

                    if (addchoice == 6) break; // ✅ go back

                    List<String> list = new ArrayList<>();
                    String type = "";

                    if (addchoice == 1) {
                        list = mainsystem.GET_ALLERGIES(patientId);
                        type = "Allergy";
                    } else if (addchoice == 2) {
                        list = mainsystem.GET_CONDITIONS(patientId);
                        type = "Condition";
                    } else if (addchoice == 3) {
                        list = mainsystem.GET_MEDICATIONS(patientId);
                        type = "Medication";
                    } else if (addchoice == 4) {
                        list = mainsystem.GET_PEDIGREE(patientId);
                        type = "Family History";
                    } else if (addchoice == 5) {
                        list = mainsystem.GET_IMMUNIZATIONS(patientId);
                        type = "Immunization";
                    } else {
                        System.out.println("Invalid Choice. Choose Options 1-6 Only.");
                        continue;
                    }

                    System.out.println();
                    System.out.println("Current " + type + "s:");
                    System.out.println("--------------------------------");
                    if (list.isEmpty()) {
                        System.out.println("No records found.");
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + ". " + list.get(i));
                        }
                    }

                    // Now add new entry
                    if (addchoice == 1) {
                        System.out.print("Input New Allergy: ");
                        String allergy = INPUT.nextLine();
                        mainsystem.ADD_ALLERGY(patientId, allergy);
                    } else if (addchoice == 2) {
                        System.out.print("Input New Condition: ");
                        String condition = INPUT.nextLine();
                        mainsystem.ADD_CONDITION(patientId, condition);
                    } else if (addchoice == 3) {
                        System.out.print("Input New Medication: ");
                        String medication = INPUT.nextLine();
                        mainsystem.ADD_MEDICATION(patientId, medication);
                    } else if (addchoice == 4) {
                        System.out.print("Input New Family Medical History: ");
                        String history = INPUT.nextLine();
                        mainsystem.ADD_FAMILY_HISTORY(patientId, history);
                    } else if (addchoice == 5) {
                        System.out.print("Input New Immunization: ");
                        String vaccine = INPUT.nextLine();
                        mainsystem.ADD_IMMUNIZATION(patientId, vaccine);
                    }
                    System.out.println("Successfully Added!");
                }

            } else if (option == 2) {
                while (true) {
                    System.out.println();
                    System.out.println("========================================");
                    System.out.println("|     DELETE PATIENT CRITICAL DATA     |");
                    System.out.println("========================================");
                    System.out.println("| 1. Allergies                         |");
                    System.out.println("| 2. Conditions                        |");
                    System.out.println("| 3. Medications                       |");
                    System.out.println("| 4. Family Medical History            |");
                    System.out.println("| 5. Immunizations                     |");
                    System.out.println("| 6. Back To EMHAS Edit Choices        |");
                    System.out.println("========================================");
                    System.out.println();
                    System.out.print("Choose Category: ");
                    int deletechoice = INPUT.nextInt();
                    INPUT.nextLine();

                    if (deletechoice == 6) break; // ✅ go back

                    List<String> list = new ArrayList<>();
                    String type = "";

                    if (deletechoice == 1) {
                        list = mainsystem.GET_ALLERGIES(patientId);
                        type = "Allergy";
                    } else if (deletechoice == 2) {
                        list = mainsystem.GET_CONDITIONS(patientId);
                        type = "Condition";
                    } else if (deletechoice == 3) {
                        list = mainsystem.GET_MEDICATIONS(patientId);
                        type = "Medication";
                    } else if (deletechoice == 4) {
                        list = mainsystem.GET_PEDIGREE(patientId);
                        type = "Family History";
                    } else if (deletechoice == 5) {
                        list = mainsystem.GET_IMMUNIZATIONS(patientId);
                        type = "Immunization";
                    } else {
                        System.out.println("Invalid Choice. Choose Options 1-6 Only.");
                        continue;
                    }

                    if (list.isEmpty()) {
                        System.out.println("No records found.");
                        continue;
                    }

                    System.out.println();
                    System.out.println("Select " + type + " to delete:");
                    System.out.println("--------------------------------");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                    System.out.println("--------------------------------");

                    System.out.print("Enter number: ");
                    int index = INPUT.nextInt();
                    INPUT.nextLine();

                    if (index < 1 || index > list.size()) {
                        System.out.println("Invalid selection.");
                        continue;
                    }

                    String selected = list.get(index - 1);

                    if (deletechoice == 1) {
                        mainsystem.DELETE_ALLERGY(patientId, selected);
                    } else if (deletechoice == 2) {
                        mainsystem.DELETE_CONDITION(patientId, selected);
                    } else if (deletechoice == 3) {
                        mainsystem.DELETE_MEDICATION(patientId, selected);
                    } else if (deletechoice == 4) {
                        mainsystem.DELETE_FAMILY_HISTORY(patientId, selected);
                    } else if (deletechoice == 5) {
                        mainsystem.DELETE_IMMUNIZATION(patientId, selected);
                    }
                    System.out.println("Deleted Successfully!");
                }


                

            } else if (option == 3) {
                while (true) {
                System.out.println();
                System.out.println("=========================================");
                System.out.println("|             UPDATE CHOICES            |");
                System.out.println("|=======================================|");
                System.out.println("| 1. Update Patient's Name              |");
                System.out.println("| 2. Update Patient's Address           |");
                System.out.println("| 3. Update Patient's Birthdate         |");
                System.out.println("| 4. Update Patient's Blood Type        |");
                System.out.println("| 5. Update Patient's Age               |");
                System.out.println("| 6. Update Patient's Emergency Contact |");
                System.out.println("| 7. Update Patient's Allergies         |");
                System.out.println("| 8. Update Patient's Conditions        |");
                System.out.println("| 9. Update Patient's Medications       |");
                System.out.println("| 10. Update Patient's Pedigree         |");
                System.out.println("| 11. Update Patient's Immunizations    |");
                System.out.println("| 12. Back To EMHAS Edit Choices        |");
                System.out.println("=========================================");
                System.out.println();
                System.out.print("Choose Update Choices: ");
                int updatechoice = INPUT.nextInt();
                INPUT.nextLine();

                List<String> details = mainsystem.SEARCH_PATIENT(patientId);

                if (updatechoice == 1) {
                    System.out.println();
                    for (String d : details) 
                        if (d.startsWith("Name:")) 
                            System.out.println("Current " + d);
                    System.out.println();
                    System.out.print("Enter New First Name: ");
                    String fname = INPUT.nextLine();
                    System.out.println();
                    System.out.print("Enter New Middle Initial: ");
                    String mi = INPUT.nextLine();
                    System.out.println();
                    System.out.print("Enter New Last Name: ");
                    String lname = INPUT.nextLine();
                    mainsystem.UPDATE_NAME(patientId, fname, mi, lname);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 2) {
                    System.out.println();
                    for (String d : details) 
                        if (d.startsWith("Address:")) 
                            System.out.println("Current " + d);
                    System.out.println();
                    System.out.print("Enter New Barangay: ");
                    String barangay = INPUT.nextLine();
                    System.out.println();
                    System.out.print("Enter New City: ");
                    String city = INPUT.nextLine();
                    System.out.println();
                    System.out.print("Enter New Province: ");
                    String province = INPUT.nextLine();
                    mainsystem.UPDATE_ADDRESS(patientId, barangay, city, province);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 3) {
                    System.out.println();
                    for (String d : details) 
                        if (d.startsWith("Date of Birth:")) 
                            System.out.println("Current " + d);
                    System.out.println();
                    System.out.print("Enter New Birth Month: ");
                    String month = INPUT.nextLine();
                    System.out.println();
                    System.out.print("Enter New Birth Day: ");
                    int day = INPUT.nextInt();
                    System.out.println();
                    System.out.print("Enter New Birth Year: ");
                    int year = INPUT.nextInt();
                    INPUT.nextLine();
                    System.out.println();
                    mainsystem.UPDATE_BIRTHDATE(patientId, month, day, year);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 4) {
                    System.out.println();
                    for (String d : details) 
                        if (d.startsWith("Blood Type:")) 
                            System.out.println("Current " + d);
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
                    System.out.print("Enter New Blood Type Option (1-8): ");
                    int bloodtypeoption = INPUT.nextInt();
                    INPUT.nextLine();
                    String newBloodType = "";
                    if (bloodtypeoption == 1){
                        newBloodType = "O+";
                    } else if (bloodtypeoption == 2){
                        newBloodType = "O-";
                    } else if (bloodtypeoption == 3){
                        newBloodType = "A+";
                    } else if (bloodtypeoption == 4){
                        newBloodType = "A-";
                    } else if (bloodtypeoption == 5){
                        newBloodType = "B+";
                    } else if (bloodtypeoption == 6){
                        newBloodType = "B-";
                    } else if (bloodtypeoption == 7){
                        newBloodType = "AB+";
                    } else if (bloodtypeoption == 8){
                        newBloodType = "AB-";
                    } else {
                        System.out.println();
                        System.out.println("Invalid Choice. Choose Options 1-8 Only.");
                        continue;
                    }
                    mainsystem.UPDATE_BLOODTYPE(patientId, newBloodType);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 5) {
                    System.out.println();
                    for (String d : details) 
                        if (d.startsWith("Age:")) 
                            System.out.println("Current " + d);
                    System.out.println();
                    System.out.print("Enter New Age: ");
                    int age = INPUT.nextInt();
                    INPUT.nextLine();
                    mainsystem.UPDATE_AGE(patientId, age);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 6) {
                    System.out.println();
                    for (String d : details) 
                        if (d.startsWith("Emergency Contact:")) 
                            System.out.println("Current " + d);
                    System.out.println();
                    System.out.print("Enter New Emergency Contact: ");
                    String contact = INPUT.nextLine();
                    mainsystem.UPDATE_EMERGENCYCONTACT(patientId, contact);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 7) {
                    System.out.println();
                    System.out.println("=======================");
                    System.out.println("Allergies of " + patientId);
                    System.out.println("=======================");
                    List<String> allergiesList = mainsystem.GET_ALLERGIES(patientId);
                    for (String a : allergiesList) 
                        System.out.println(a);
                    System.out.println("=======================");
                    System.out.println();
                    System.out.print("Enter the exact Allergy to update: ");
                    String oldAllergy = INPUT.nextLine();
                    System.out.println();
                    System.out.print("Enter New Allergy: ");
                    String newAllergy = INPUT.nextLine();
                    mainsystem.UPDATE_SPECIFIC_ALLERGY(patientId, oldAllergy, newAllergy);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 8) {
                    System.out.println();
                    System.out.println("=======================");
                    System.out.println("Conditions of " + patientId);
                    System.out.println("=======================");
                    List<String> conditionsList = mainsystem.GET_CONDITIONS(patientId);
                    for (String c : conditionsList) 
                        System.out.println(c);
                    System.out.println("=======================");
                    System.out.println();
                    System.out.print("Enter the exact Condition to update: ");
                    String oldCondition = INPUT.nextLine();
                    System.out.println();
                    System.out.print("Enter New Condition: ");
                    String newCondition = INPUT.nextLine();
                    System.out.println();
                    mainsystem.UPDATE_SPECIFIC_CONDITION(patientId, oldCondition, newCondition);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 9) {
                    System.out.println();
                    System.out.println("=======================");
                    System.out.println("Medications of " + patientId);
                    System.out.println("=======================");
                    List<String> medsList = mainsystem.GET_MEDICATIONS(patientId);
                    for (String m : medsList) 
                        System.out.println(m);
                    System.out.println("=======================");
                    System.out.println();
                    System.out.print("Enter the exact Medication to update: ");
                    String oldMed = INPUT.nextLine();
                    System.out.println();
                    System.out.print("Enter New Medication: ");
                    String newMed = INPUT.nextLine();
                    mainsystem.UPDATE_SPECIFIC_MEDICATION(patientId, oldMed, newMed);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 10) {
                    System.out.println();
                    System.out.println("=======================");
                    System.out.println("Pedigree of " + patientId);
                    System.out.println("=======================");
                    List<String> pedigreeList = mainsystem.GET_PEDIGREE(patientId);
                    for (String p : pedigreeList) 
                        System.out.println(p);
                    System.out.println("=======================");
                    System.out.println();
                    System.out.print("Enter the exact Pedigree entry to update: ");
                    String oldPedigree = INPUT.nextLine();
                    System.out.println();
                    System.out.print("Enter New Pedigree: ");
                    String newPedigree = INPUT.nextLine();
                    mainsystem.UPDATE_SPECIFIC_PEDIGREE(patientId, oldPedigree, newPedigree);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 11) {
                    System.out.println();
                    System.out.println("=======================");
                    System.out.println("Immunizations of " + patientId);
                    System.out.println("=======================");
                    List<String> immunizationsList = mainsystem.GET_IMMUNIZATIONS(patientId);
                    for (String i : immunizationsList) 
                        System.out.println(i);
                    System.out.println("=======================");
                    System.out.println();
                    System.out.print("Enter the exact Immunization to update: ");
                    String oldImmunization = INPUT.nextLine();
                    System.out.println();
                    System.out.print("Enter New Immunization: ");
                    String newImmunization = INPUT.nextLine();
                    mainsystem.UPDATE_SPECIFIC_IMMUNIZATION(patientId, oldImmunization, newImmunization);
                    System.out.println();
                    System.out.println("Update Successful");

                } else if (updatechoice == 12) {
                    System.out.println();
                    System.out.println("Returning Back To EMHAS Edit Choices....");
                    break;
                } else {
                    System.out.println();
                    System.out.println("Invalid Choice. Choose Options 1-12 Only.");
                    continue;
                }
            } 

            } else if (option == 4) {
                System.out.println();
                System.out.println("Returning to EMHAS Menu System.... ");
                break; 
            } else {
                System.out.println();
                System.out.println("Invalid Choice. Choose Options 1-4 Only.");
                continue;
            }
        }
    }
}
