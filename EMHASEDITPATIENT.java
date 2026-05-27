import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class EMHASEDITPATIENT {

    private Scanner INPUT;
    private MAINSYSTEM mainsystem;

    public EMHASEDITPATIENT(MAINSYSTEM mainsystem, Scanner INPUT) {
        this.mainsystem = mainsystem;
        this.INPUT = INPUT;
    }
        
    public String EDITPATIENT() {
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

            int option = -1;
            boolean validInput = false;

            // Error trap loop
            while (!validInput) {
                System.out.print("Choose Option: ");
                try {
                    option = INPUT.nextInt();
                    INPUT.nextLine(); // clear buffer

                    if (option >= 1 && option <= 4) {
                        validInput = true; // valid choice
                    } else {
                        System.out.println();
                        System.out.println("Invalid Choice. Please enter numbers 1-4 only.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println();
                    System.out.println("Invalid Choice. Please enter numbers 1-4 only.");
                    INPUT.nextLine(); // clear invalid input
                }
            }

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
                    System.out.println("=======================");
                    if (list.isEmpty()) {
                        System.out.println("No Records Found.");
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + ". " + list.get(i));
                        }
                        System.out.println("=======================");
                    }

                    // Now add new entry
                    if (addchoice == 1) {
                        System.out.println();
                        System.out.print("Input New Allergy: ");

                        String allergy = INPUT.nextLine().trim();

                        if(allergy.isEmpty()){
                            System.out.println();
                            System.out.println("Allergy Cannot Be Empty.");
                            continue;
                        }

                        mainsystem.ADD_ALLERGY(patientId, allergy);

                    } else if (addchoice == 2) {
                        System.out.println();
                        System.out.print("Input New Condition: ");

                        String condition = INPUT.nextLine().trim();

                        if(condition.isEmpty()){
                            System.out.println();
                            System.out.println("Condition Cannot Be Empty.");
                            continue;
                        }

                        mainsystem.ADD_CONDITION(patientId, condition);

                    } else if (addchoice == 3) {
                        System.out.println();
                        System.out.print("Input New Medication: ");

                        String medication = INPUT.nextLine().trim();

                        if(medication.isEmpty()){
                            System.out.println();
                            System.out.println("Medication Cannot Be Empty.");
                            continue;
                        }

                        mainsystem.ADD_MEDICATION(patientId, medication);

                    } else if (addchoice == 4) {
                        System.out.println();
                        System.out.print("Input New Family Medical History: ");

                        String history = INPUT.nextLine().trim();

                        if(history.isEmpty()){
                            System.out.println();
                            System.out.println("Family Medical History Cannot Be Empty.");
                            continue;
                        }

                        mainsystem.ADD_FAMILY_HISTORY(patientId, history);

                    } else if (addchoice == 5) {
                        System.out.println();
                        System.out.print("Input New Immunization: ");

                        String vaccine = INPUT.nextLine().trim();

                        if(vaccine.isEmpty()){
                            System.out.println();
                            System.out.println("Immunization Cannot Be Empty.");
                            continue;
                        }

                        mainsystem.ADD_IMMUNIZATION(patientId, vaccine);
                    }

                System.out.println();
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
                    int deletechoice = -1;

                    // Error trap loop
                    while (true) {
                        System.out.print("Choose Option: ");
                        try {
                            deletechoice = INPUT.nextInt();
                            INPUT.nextLine(); // clear buffer

                            if (deletechoice >= 1 && deletechoice <= 6) {
                                break; // valid input, exit loop
                            } else {
                                System.out.println();
                                System.out.println("Invalid Choice. Please enter numbers 1-6 only.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println();
                            System.out.println("Invalid Choice. Please enter numbers 1-6 only.");
                            INPUT.nextLine(); // clear invalid input
                        }
                    }


                    if (deletechoice == 6) 
                        break; // Go back

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
                    } 
                    

                    if (list.isEmpty()) {
                        System.out.println("No Records Found.");
                        continue;
                    }

                    System.out.println();
                    System.out.println("Select " + type + " to delete:");
                    System.out.println("=======================");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                    System.out.println("=======================");
                    System.out.println();
                    System.out.print("Enter Choice Number: ");
                    int index = INPUT.nextInt();
                    INPUT.nextLine();

                    if (index < 1 || index > list.size()) {
                        System.out.println("Invalid Selection.");
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
                    System.out.println("|         UPDATE PATIENT DETAILS        |");
                    System.out.println("|=======================================|");
                    System.out.println("| 1. Update Patient's Name              |");
                    System.out.println("| 2. Update Patient's Address           |");
                    System.out.println("| 3. Update Patient's Blood Type        |");
                    System.out.println("| 4. Update Patient's Birthdate & Age   |");
                    System.out.println("| 5. Update Patient's Emergency Contact |");
                    System.out.println("| 6. Update Patient's Allergies         |");
                    System.out.println("| 7. Update Patient's Conditions        |");
                    System.out.println("| 8. Update Patient's Medications       |");
                    System.out.println("| 9. Update Patient's Pedigree          |");
                    System.out.println("| 10. Update Patient's Immunizations    |");
                    System.out.println("| 11. Back To EMHAS Edit Choices        |");
                    System.out.println("=========================================");
                    System.out.println();

                    int updatechoice = -1;
                    while (true) {
                        System.out.print("Choose Category (1-11): ");
                        try {
                            updatechoice = INPUT.nextInt();
                            INPUT.nextLine();
                            if (updatechoice >= 1 && updatechoice <= 11) break;
                            else System.out.println("Invalid Choice. Please enter numbers 1-11 only.");
                        } catch (Exception e) {
                            System.out.println("Invalid input! Numbers only (1-11).");
                            INPUT.nextLine();
                        }
                    }

                    if (updatechoice == 1) {
                        // Name
                        String fname, mi, lname;
                        while (true) {
                            System.out.print("Enter New First Name: ");
                            fname = INPUT.nextLine().trim();
                            if (fname.matches("[a-zA-Z ]+")) break;
                            else System.out.println("Letters only.");
                        }
                        while (true) {
                            System.out.print("Enter New Middle Initial: ");
                            mi = INPUT.nextLine().trim();
                            if (mi.matches("[a-zA-Z]")) {
                                mi = mi.toUpperCase();
                                break;
                            } else System.out.println("1 letter only.");
                        }
                        while (true) {
                            System.out.print("Enter New Last Name: ");
                            lname = INPUT.nextLine().trim();
                            if (lname.matches("[a-zA-Z ]+")) break;
                            else System.out.println("Letters only.");
                        }
                        mainsystem.UPDATE_NAME(patientId, fname, mi, lname);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 2) {
                        // Address
                        String barangay, city, province;
                        while (true) {
                            System.out.print("Enter New Barangay: ");
                            barangay = INPUT.nextLine().trim();
                            if (!barangay.isEmpty()) break;
                            else System.out.println("Barangay cannot be empty.");
                        }
                        while (true) {
                            System.out.print("Enter New City: ");
                            city = INPUT.nextLine().trim();
                            if (city.matches("[a-zA-Z ]+")) break;
                            else System.out.println("Letters only.");
                        }
                        while (true) {
                            System.out.print("Enter New Province: ");
                            province = INPUT.nextLine().trim();
                            if (province.matches("[a-zA-Z ]+")) break;
                            else System.out.println("Letters only.");
                        }
                        mainsystem.UPDATE_ADDRESS(patientId, barangay, city, province);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 3) {
                        // Blood Type with menu
                        int bloodtypeoption;
                        while (true) {
                            try {
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
                                System.out.print("Enter New Blood Type Option (1-8): ");
                                bloodtypeoption = INPUT.nextInt();
                                INPUT.nextLine();
                                if (bloodtypeoption >= 1 && bloodtypeoption <= 8) break;
                                else System.out.println("1-8 only.");
                            } catch (Exception e) {
                                System.out.println("Numbers only.");
                                INPUT.nextLine();
                            }
                        }
                        String newBloodType = "";
                        if (bloodtypeoption == 1) newBloodType = "O+";
                        else if (bloodtypeoption == 2) newBloodType = "O-";
                        else if (bloodtypeoption == 3) newBloodType = "A+";
                        else if (bloodtypeoption == 4) newBloodType = "A-";
                        else if (bloodtypeoption == 5) newBloodType = "B+";
                        else if (bloodtypeoption == 6) newBloodType = "B-";
                        else if (bloodtypeoption == 7) newBloodType = "AB+";
                        else if (bloodtypeoption == 8) newBloodType = "AB-";
                        mainsystem.UPDATE_BLOODTYPE(patientId, newBloodType);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 4) {
                        // Birthdate & Age together
                        int dob_month_option, dob_day, dob_year;
                        // Month menu
                        while (true) {
                            try {
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
                                System.out.print("Enter Birth Month (1-12): ");
                                dob_month_option = INPUT.nextInt();
                                INPUT.nextLine();
                                if (dob_month_option >= 1 && dob_month_option <= 12) break;
                                else System.out.println("1-12 only.");
                            } catch (Exception e) {
                                System.out.println("Numbers only.");
                                INPUT.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.print("Enter Birth Year: ");
                                dob_year = INPUT.nextInt();
                                INPUT.nextLine();
                                int currentYear = java.time.LocalDate.now().getYear();
                                if (dob_year >= 1900 && dob_year <= currentYear) break;
                                else System.out.println("Invalid year.");
                            } catch (Exception e) {
                                System.out.println("Numbers only.");
                                INPUT.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.print("Enter Birth Day: ");
                                dob_day = INPUT.nextInt();
                                INPUT.nextLine();

                                int maxDays = 31;
                                if (dob_month_option == 4 || dob_month_option == 6 ||
                                    dob_month_option == 9 || dob_month_option == 11) {
                                    maxDays = 30;
                                } else if (dob_month_option == 2) {
                                    boolean leap = (dob_year % 4 == 0 && dob_year % 100 != 0) ||
                                                (dob_year % 400 == 0);
                                    maxDays = leap ? 29 : 28;
                                }

                                if (dob_day >= 1 && dob_day <= maxDays) break;
                                else System.out.println("Invalid day for selected month.");
                            } catch (Exception e) {
                                System.out.println("Numbers only.");
                                INPUT.nextLine();
                            }
                        }

                        int age;
                        while (true) {
                            try {
                                System.out.print("Enter Age: ");
                                age = INPUT.nextInt();
                                INPUT.nextLine();

                                if (age < 0 || age > 150) {
                                    System.out.println("Age must be 0-150 only.");
                                    continue;
                                }

                                LocalDate birth = LocalDate.of(dob_year, dob_month_option, dob_day);
                                LocalDate today = LocalDate.now();
                                int computedAge = Period.between(birth, today).getYears();

                                if (age == computedAge) {
                                    mainsystem.UPDATE_BIRTHDATE(patientId, String.valueOf(dob_month_option), dob_day, dob_year);
                                    mainsystem.UPDATE_AGE(patientId, age);
                                    System.out.println("Update Successful");
                                    break;
                                } else {
                                    System.out.println("Age does not match birthdate.");
                                }
                            } catch (Exception e) {
                                System.out.println("Numbers only.");
                                INPUT.nextLine();
                            }
                        }
                    }

                    else if (updatechoice == 5) {
                        // Emergency Contact
                        String contact;
                        while (true) {
                            System.out.print("Enter New Emergency Contact (11 digits): ");
                            contact = INPUT.nextLine().trim();
                            if (contact.matches("\\d{11}")) break;
                            else System.out.println("Must be 11 digits.");
                        }
                        mainsystem.UPDATE_EMERGENCYCONTACT(patientId, contact);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 6) {
                        // Allergies
                        List<String> allergiesList = mainsystem.GET_ALLERGIES(patientId);
                        for (String a : allergiesList) System.out.println(a);
                        String oldAllergy, newAllergy;
                        while (true) {
                            System.out.print("Enter the exact Allergy to update: ");
                            oldAllergy = INPUT.nextLine().trim();
                            if (!oldAllergy.isEmpty()) break;
                            else System.out.println("Cannot be empty.");
                        }
                        while (true) {
                            System.out.print("Enter New Allergy: ");
                            newAllergy = INPUT.nextLine().trim();
                            if (!newAllergy.isEmpty()) break;
                            else System.out.println("Cannot be empty.");
                        }
                        mainsystem.UPDATE_SPECIFIC_ALLERGY(patientId, oldAllergy, newAllergy);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 7) {
                        // Conditions
                        List<String> conditionsList = mainsystem.GET_CONDITIONS(patientId);
                        for (String c : conditionsList) System.out.println(c);
                        String oldCondition, newCondition;
                        while (true) {
                            System.out.print("Enter the exact Condition to update: ");
                            oldCondition = INPUT.nextLine().trim();
                            if (!oldCondition.isEmpty()) break;
                            else System.out.println("Cannot be empty.");
                        }
                        while (true) {
                            System.out.print("Enter New Condition: ");
                            newCondition = INPUT.nextLine().trim();
                            if (!newCondition.isEmpty()) break;
                            else System.out.println("Cannot be empty.");
                        }
                        mainsystem.UPDATE_SPECIFIC_CONDITION(patientId, oldCondition, newCondition);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 8) {
                        // Medications
                        List<String> medsList = mainsystem.GET_MEDICATIONS(patientId);
                        for (String m : medsList) System.out.println(m);
                        String oldMed, newMed;
                        while (true) {
                            System.out.print("Enter the exact Medication to update: ");
                            oldMed = INPUT.nextLine().trim();
                            if (!oldMed.isEmpty()) break;
                            else System.out.println("Cannot be empty.");
                        }
                        while (true) {
                            System.out.print("Enter New Medication: ");
                            newMed = INPUT.nextLine().trim();
                            if (!newMed.isEmpty()) break;
                            else System.out.println("Cannot be empty.");
                        }
                        mainsystem.UPDATE_SPECIFIC_MEDICATION(patientId, oldMed, newMed);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 9) {
                        // Pedigree (Family History)
                        List<String> pedigreeList = mainsystem.GET_PEDIGREE(patientId);
                        for (String p : pedigreeList) System.out.println(p);
                        String oldHistory, newHistory;
                        while (true) {
                            System.out.print("Enter the exact Family History to update: ");
                            oldHistory = INPUT.nextLine().trim();
                            if (!oldHistory.isEmpty()) break;
                            else System.out.println("Cannot be empty.");
                        }
                        while (true) {
                            System.out.print("Enter New Family History: ");
                            newHistory = INPUT.nextLine().trim();
                            if (!newHistory.isEmpty()) break;
                            else System.out.println("Cannot be empty.");
                        }
                        mainsystem.UPDATE_SPECIFIC_PEDIGREE(patientId, oldHistory, newHistory);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 10) {
                        // Immunizations
                        List<String> immunizationsList = mainsystem.GET_IMMUNIZATIONS(patientId);
                        for (String i : immunizationsList) System.out.println(i);
                        String oldImmunization, newImmunization;
                        while (true) {
                            System.out.print("Enter the exact Immunization to update: ");
                            oldImmunization = INPUT.nextLine().trim();
                            if (!oldImmunization.isEmpty()) break;
                            else System.out.println("Cannot be empty.");
                        }
                        while (true) {
                            System.out.print("Enter New Immunization: ");
                            newImmunization = INPUT.nextLine().trim();
                            if (!newImmunization.isEmpty()) break;
                            else System.out.println("Cannot be empty.");
                        }
                        mainsystem.UPDATE_SPECIFIC_IMMUNIZATION(patientId, oldImmunization, newImmunization);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 11) {
                        System.out.println("Returning Back To EMHAS Edit Choices....");
                        break;
                    }
                }
                
            } else if (option == 4) {
                System.out.println();
                System.out.println("Returning to EMHAS Menu System.... ");
                break; 
            }
            
            
        } 
        return patientId;
        
    }

    
}
