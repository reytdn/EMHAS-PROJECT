import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class EMHASEDITPATIENT {
    // reference to main system and scanner for user input
    private Scanner INPUT;
    private MAINSYSTEM mainsystem;

    // constructor to connect this class to main system and scanner
    public EMHASEDITPATIENT(MAINSYSTEM mainsystem, Scanner INPUT) {
        this.mainsystem = mainsystem;
        this.INPUT = INPUT;
    }

    // method to edit patient data, allows user to select a patient and edit their data 
    public String EDITPATIENT() {
        System.out.println();
        // asks user for patient ID to edit
        System.out.print("Enter Patient ID to Edit: ");
        String patientId = INPUT.nextLine();

        // loops the edit menu
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

            // handles invalid input for edit choices
            while (!validInput) {
                System.out.print("Choose Option: ");
                try {
                    option = INPUT.nextInt();
                    INPUT.nextLine();
                    // only allow option 1-4
                    if (option >= 1 && option <= 4) {
                        validInput = true; 
                    } else {
                        // error message for out of range input
                        System.out.println();
                        System.out.println("Invalid Choice. Please enter numbers 1-4 only.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println();
                    // error message for non-integer input
                    System.out.println("Invalid Choice. Please enter numbers 1-4 only.");
                    INPUT.nextLine(); 
                }
            }

            // OPTION 1 - ADD CRITICAL DATA
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

                    // return to emhas edit menu
                    if (addchoice == 6) break; 

                    // list to show current entries for the selected category and display it 
                    List<String> list = new ArrayList<>();
                    // used for display
                    String type = "";

                    // get current entries for selected category and updates the type
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
                        // handles invalid input
                        System.out.println();
                        System.out.println("Invalid Choice. Choose Options 1-6 Only.");
                        continue;
                    }

                    // display current entries for the selected category
                    System.out.println();
                    System.out.println("Current " + type + "s:");
                    System.out.println("=======================");
                    if (list.isEmpty()) {
                        System.out.println();
                        // display no records found
                        System.out.println("No Records Found.");
                    } else {
                        // loop to display records
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + ". " + list.get(i));
                        }
                        System.out.println("=======================");
                    }

                    //  add new allergy
                    if (addchoice == 1) {
                        System.out.println();
                        System.out.print("Input New Allergy: ");

                        String allergy = INPUT.nextLine().trim();

                        // error trap for empty input
                        if(allergy.isEmpty()){
                            System.out.println();
                            System.out.println("Allergy Cannot Be Empty.");
                            continue;
                        }
                        // sends new allergy to main system to be added to the patient record
                        mainsystem.ADD_ALLERGY(patientId, allergy);

                    // add new condition
                    } else if (addchoice == 2) {
                        System.out.println();
                        System.out.print("Input New Condition: ");

                        String condition = INPUT.nextLine().trim();

                        // error trap for empty input
                        if(condition.isEmpty()){
                            System.out.println();
                            System.out.println("Condition Cannot Be Empty.");
                            continue;
                        }
                        // sends new condition to main system to be added to the patient record
                        mainsystem.ADD_CONDITION(patientId, condition);
                    
                    // add new medication
                    } else if (addchoice == 3) {
                        System.out.println();
                        System.out.print("Input New Medication: ");

                        String medication = INPUT.nextLine().trim();

                        // error trap for empty input
                        if(medication.isEmpty()){
                            System.out.println();
                            System.out.println("Medication Cannot Be Empty.");
                            continue;
                        }
                        // sends new medication to main system to be added to the patient record
                        mainsystem.ADD_MEDICATION(patientId, medication);
                    
                    // add new family medical history
                    } else if (addchoice == 4) {
                        System.out.println();
                        System.out.print("Input New Family Medical History: ");

                        String history = INPUT.nextLine().trim();

                        // error trap for empty input
                        if(history.isEmpty()){
                            System.out.println();
                            System.out.println("Family Medical History Cannot Be Empty.");
                            continue;
                        }
                        // sends new family medical history to main system to be added to the patient record
                        mainsystem.ADD_FAMILY_HISTORY(patientId, history);

                    // add new immunization
                    } else if (addchoice == 5) {
                        System.out.println();
                        System.out.print("Input New Immunization: ");

                        String vaccine = INPUT.nextLine().trim();
                        // error trap for empty input
                        if(vaccine.isEmpty()){
                            System.out.println();
                            System.out.println("Immunization Cannot Be Empty.");
                            continue;
                        }
                        // sends new immunization to main system to be added to the patient record
                        mainsystem.ADD_IMMUNIZATION(patientId, vaccine);
                    }

                System.out.println();
                System.out.println("Successfully Added!");
                }
            

                // OPTION 2 - DELETE CRITICAL DATA
            } else if (option == 2) {
                // loops the delete critical data menu
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
                        System.out.println();
                        System.out.print("Choose Option: ");
                        try {
                            deletechoice = INPUT.nextInt();
                            INPUT.nextLine();

                            //only allow options 1-6
                            if (deletechoice >= 1 && deletechoice <= 6) {
                                break; 

                            } else {
                                System.out.println();
                                // out of range error
                                System.out.println("Invalid Choice. Please enter numbers 1-6 only.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println();
                            // error for non-integer input
                            System.out.println("Invalid Choice. Please enter numbers 1-6 only.");
                            INPUT.nextLine(); 
                        }
                    }


                    if (deletechoice == 6) 
                        break; // Go back to emhas edit menu

                    // list to show current entries for the selected category and display it
                    List<String> list = new ArrayList<>();
                    // used for display
                    String type = "";

                    // get current entries for selected category and updates the type
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
                        System.out.println();
                        //error message for no records found
                        System.out.println("No Records Found.");
                        continue;
                    }

                    System.out.println();
                    // display current entries for the selected category
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

                    // error trap for invalid index input
                    if (index < 1 || index > list.size()) {
                        System.out.println();
                        System.out.println("Invalid Selection.");
                        continue;
                    }

                    String selected = list.get(index - 1);

                    // sends request to main system to delete the selected entry from the patient record based on the category
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
                    System.out.println();
                    // success message
                    System.out.println("Deleted Successfully!");
                }


            } else if (option == 3) {
                // loops the update patient data menu  
                while (true) {
                    System.out.println();
                    System.out.println("=========================================");
                    System.out.println("|         UPDATE PATIENT DETAILS        |");
                    System.out.println("|=======================================|");
                    System.out.println("| 1. Update Patient's Name              |");
                    System.out.println("| 2. Update Patient's Address           |");
                    System.out.println("| 3. Update Patient's Blood Type        |");
                    System.out.println("| 4. Update Patient's Birthdate & Age   |");
                    System.out.println("| 5. Update Patient's Gender            |");
                    System.out.println("| 6. Update Patient's Emergency Contact |");
                    System.out.println("| 7. Update Patient's Allergies         |");
                    System.out.println("| 8. Update Patient's Conditions        |");
                    System.out.println("| 9. Update Patient's Medications       |");
                    System.out.println("| 10. Update Patient's Pedigree          |");
                    System.out.println("| 11. Update Patient's Immunizations    |");
                    System.out.println("| 12. Back To EMHAS Edit Choices        |");
                    System.out.println("=========================================");
                    System.out.println();

                    int updatechoice = -1;
                    while (true) {
                        System.out.println();
                        System.out.print("Choose Category (1-12): ");
                        // error trap for invalid input
                        try {
                            updatechoice = INPUT.nextInt();
                            INPUT.nextLine();
                            if (updatechoice >= 1 && updatechoice <= 12) 
                                break; // only allow option 1-12
                            else 
                                System.out.println();
                                // error message for out of range input
                                System.out.println("Invalid Choice. Please enter numbers 1-12 only.");
                        } catch (Exception e) {
                            // error message for non-integer input
                            System.out.println();
                            System.out.println("Invalid Choice. Please enter numbers 1-12 only.");
                            INPUT.nextLine();
                        }
                    }

                    if (updatechoice == 1) {
                        // Name
                        String fname, mi, lname;
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New First Name: ");
                            fname = INPUT.nextLine().trim();
                            if (fname.matches("[a-zA-Z ]+")) 
                                break; // only allow letters and spaces
                            else 
                                // error message for invalid input
                                System.out.println();
                                System.out.println("Letters And Spaces Only For First Name.");
                        }
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Middle Initial: ");
                            mi = INPUT.nextLine().trim();
                            if (mi.matches("[a-zA-Z]")) {
                                mi = mi.toUpperCase();
                                break;
                            } else {
                                System.out.println();
                                // error message for invalid input
                                System.out.println("Please Enter A Single Letter For Middle Initial.");
                            }
                        }
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Last Name: ");
                            lname = INPUT.nextLine().trim();
                            if (lname.matches("[a-zA-Z ]+"))
                                break; // only allow letters and spaces
                            else {
                                System.out.println();
                            // error message for invalid input
                                System.out.println("Letters And Spaces Only For Last Name.");
                            }
                        }
                        // sends new name to main system to update the patient record
                        mainsystem.UPDATE_NAME(patientId, fname, mi, lname);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 2) {
                        // Address
                        String barangay, city, province;
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Barangay: ");
                            barangay = INPUT.nextLine().trim();
                            if (!barangay.isEmpty()) 
                                break; // error trap for empty input
                            else 
                                System.out.println();
                                // error message for empty input
                                System.out.println("Barangay Cannot Be Empty.");
                        }
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New City: ");
                            city = INPUT.nextLine().trim();
                            if (city.matches("[a-zA-Z ]+")) 
                                break; // only allow letters and spaces
                            else {
                                // error message for invalid input
                                System.out.println();
                                System.out.println("Letters And Spaces Only For City.");
                            }
                        }
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Province: ");
                            province = INPUT.nextLine().trim();
                            if (province.matches("[a-zA-Z ]+")) 
                                break; // only allow letters and spaces
                            else {
                                // error message for invalid input
                                System.out.println();
                                System.out.println("Letters And Spaces Only For Province.");
                            }
                        }
                        // sends new address to main system to update the patient record
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
                                System.out.println();
                                System.out.print("Enter New Blood Type Option (1-8): ");
                                bloodtypeoption = INPUT.nextInt();
                                INPUT.nextLine();
                                if (bloodtypeoption >= 1 && bloodtypeoption <= 8) 
                                    break; // only allow options 1-8
                                else 
                                    System.out.println();
                                    // error message for out of range input
                                    System.out.println("Invalid Choice. Please enter numbers 1-8 only.");

                            } catch (Exception e) {
                                System.out.println();
                                // error message for non-integer input
                                System.out.println("Invalid Choice. Please enter numbers 1-8 only.");
                                INPUT.nextLine();
                            }
                        }
                        // convert option to blood type string
                        String newBloodType = "";
                        if (bloodtypeoption == 1) 
                            newBloodType = "O+";
                        else if (bloodtypeoption == 2) 
                            newBloodType = "O-";
                        else if (bloodtypeoption == 3) 
                            newBloodType = "A+";
                        else if (bloodtypeoption == 4) 
                            newBloodType = "A-";
                        else if (bloodtypeoption == 5) 
                            newBloodType = "B+";
                        else if (bloodtypeoption == 6) 
                            newBloodType = "B-";
                        else if (bloodtypeoption == 7) 
                            newBloodType = "AB+";
                        else if (bloodtypeoption == 8) 
                            newBloodType = "AB-";

                        // sends new blood type to main system to update the patient record
                        mainsystem.UPDATE_BLOODTYPE(patientId, newBloodType);
                        System.out.println();
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
                                System.out.println();
                                System.out.print("Enter Birth Month (1-12): ");
                                dob_month_option = INPUT.nextInt();
                                INPUT.nextLine();

                                if (dob_month_option >= 1 && dob_month_option <= 12) 
                                    break; // only allow option 1-12
                                else 
                                    System.out.println();
                                    // error message for out of range input
                                    System.out.println("Invalid Choice. Please enter numbers 1-12 only.");
                            } catch (Exception e) {
                                System.out.println();
                                // error message for non-integer input
                                System.out.println("Invalid Choice. Please enter numbers 1-12 only.");
                                INPUT.nextLine();
                            }
                        }

                        // error trap loop for day and year input
                        while (true) {
                            try {
                                System.out.println();
                                System.out.print("Enter Birth Year: ");
                                dob_year = INPUT.nextInt();
                                INPUT.nextLine();
                                int currentYear = java.time.LocalDate.now().getYear();
                                if (dob_year >= 1900 && dob_year <= currentYear) 
                                    break; // only allow reasonable year range
                                else {
                                    System.out.println();
                                    // error message for out of range input
                                    System.out.println("Invalid Year.");
                                }
                            } catch (Exception e) {
                                System.out.println();
                                // error message for non-integer input
                                System.out.println("Invalid Input! Numbers Only.");
                                INPUT.nextLine();
                            }
                        }

                        // error trap loop for day input, checks for valid day based on month 
                        while (true) {
                            try {
                                System.out.println();
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

                                if (dob_day >= 1 && dob_day <= maxDays) 
                                    break; // only allow valid day for the selected month 
                                else {
                                    System.out.println();
                                    System.out.println("Invalid Day for Selected Month.");
                                }
                            } catch (Exception e) {
                                System.out.println();
                                // error message for non-integer input
                                System.out.println("Invalid Input! Numbers Only.");
                                INPUT.nextLine();
                            }
                        }

                        int age;
                        // error trap loop for age input, checks if age is a number and matches the birthdate
                        while (true) {
                            try {
                                System.out.println();
                                System.out.print("Enter Age: ");
                                age = INPUT.nextInt();
                                INPUT.nextLine();

                                // error message for out of range age input
                                if (age < 0 || age > 150) {
                                    System.out.println("Age Must Be Between 0 And 150.");
                                    System.out.println();
                                    continue;
                                }

                                // compute age based on birthdate and compare with input age for validation
                                LocalDate birth = LocalDate.of(dob_year, dob_month_option, dob_day);
                                LocalDate today = LocalDate.now();
                                int computedAge = Period.between(birth, today).getYears();

                                // if age matches the birthdate, send new birthdate and age to main system to update the patient record
                                if (age == computedAge) {
                                    mainsystem.UPDATE_BIRTHDATE(patientId, String.valueOf(dob_month_option), dob_day, dob_year);
                                    mainsystem.UPDATE_AGE(patientId, age);
                                    System.out.println();
                                    System.out.println("Update Successful");
                                    break; // age is valid, update successful
                                } else {
                                    System.out.println();
                                    // error if age doesnt match birthdate
                                    System.out.println("Age Does Not Match Birthdate.");
                                }
                            } catch (Exception e) {
                                System.out.println();
                                // error message for non-integer input
                                System.out.println("Invalid Input! Numbers Only.");
                                INPUT.nextLine();
                            }
                        }
                    }


                    else if (updatechoice == 5) {
                        // Update Gender
                        String newGender;
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Gender (M/F): ");
                            String g = INPUT.nextLine().trim().toUpperCase();

                            if (g.equals("M")) {
                                newGender = "Male";
                                break;
                            } else if (g.equals("F")) {
                                newGender = "Female";
                                break;
                            } else {
                                System.out.println();
                                // error message for invalid input
                                System.out.println("Invalid Choice. Please Enter M or F Only.");
                            }
                        }
                        // sends new gender to main system to update the patient record
                        mainsystem.UPDATE_GENDER(patientId, newGender);
                        System.out.println();
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 6) {
                        // Update Emergency Contact
                        String contact;
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Emergency Contact (11 digits): ");
                            contact = INPUT.nextLine().trim();
                            if (contact.matches("\\d{11}")) 
                                break; // only allow exactly 11 digits
                            else
                                System.out.println();
                                // error message for invalid input
                                System.out.println("Invalid Contact Number. Please Enter Exactly 11 Digits.");
                        }
                        // sends new emergency contact to main system to update the patient record
                        mainsystem.UPDATE_EMERGENCYCONTACT(patientId, contact);

                        System.out.println();
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 7) {
                        // update allergy
                        List<String> allergiesList = mainsystem.GET_ALLERGIES(patientId);
                        System.out.println();
                        System.out.println("Current Allergies:");
                        System.out.println("==================");
                        for (String a : allergiesList) System.out.println(a);
                        String oldAllergy, newAllergy;
                        System.out.println("==================");
                        while (true) {
                            System.out.println();
                            System.out.print("Enter the exact Allergy to update: ");
                            oldAllergy = INPUT.nextLine().trim();
                            if (!oldAllergy.isEmpty()) 
                                break; // error trap for empty input
                            else
                                System.out.println(); 
                                System.out.println("Cannot Be Empty.");
                        }
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Allergy: ");
                            newAllergy = INPUT.nextLine().trim();
                            if (!newAllergy.isEmpty()) 
                                break; // error trap for empty input
                            else {
                                System.out.println();
                                System.out.println("Cannot Be Empty.");
                            }
                        }
                        // sends old allergy and new allergy to main system to update the patient record
                        mainsystem.UPDATE_SPECIFIC_ALLERGY(patientId, oldAllergy, newAllergy);
                        System.out.println();
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 8) {
                        // Update Conditions
                        List<String> conditionsList = mainsystem.GET_CONDITIONS(patientId);
                        System.out.println();
                        System.out.println("Current Conditions:");
                        System.out.println("==================");
                        for (String c : conditionsList) System.out.println(c);
                        String oldCondition, newCondition;
                        System.out.println("==================");
                        while (true) {
                            System.out.println();
                            System.out.print("Enter the exact Condition to update: ");
                            oldCondition = INPUT.nextLine().trim();
                            if (!oldCondition.isEmpty()) 
                                break; // error trap for empty input
                            else {
                                System.out.println();
                                System.out.println("Cannot Be Empty.");
                            }
                        }
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Condition: ");
                            newCondition = INPUT.nextLine().trim();
                            if (!newCondition.isEmpty()) 
                                break; // error trap for empty input
                            else {
                                System.out.println();
                                System.out.println("Cannot Be Empty.");
                            }
                        }
                        // sends old condition and new condition to main system to update the patient record
                        mainsystem.UPDATE_SPECIFIC_CONDITION(patientId, oldCondition, newCondition);
                        System.out.println();
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 9) {
                        // Medications
                        List<String> medsList = mainsystem.GET_MEDICATIONS(patientId);
                        System.out.println();
                        System.out.println("Current Medications:");
                        System.out.println("==================");
                        for (String m : medsList) System.out.println(m);
                        String oldMed, newMed;
                        System.out.println("==================");
                        while (true) {
                            System.out.println();
                            System.out.print("Enter the exact Medication to update: ");
                            oldMed = INPUT.nextLine().trim();
                            if (!oldMed.isEmpty()) 
                                break; // error trap for empty input
                            else {
                                System.out.println();
                                System.out.println("Cannot Be Empty.");
                            }
                        }
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Medication: ");
                            newMed = INPUT.nextLine().trim();
                            if (!newMed.isEmpty()) 
                                break; // error trap for empty input
                            else {
                                System.out.println();
                                System.out.println("Cannot Be Empty.");
                            }
                        }
                        // sends old medication and new medication to main system to update the patient record
                        mainsystem.UPDATE_SPECIFIC_MEDICATION(patientId, oldMed, newMed);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 10) {
                        // Pedigree (Family History)
                        List<String> pedigreeList = mainsystem.GET_PEDIGREE(patientId);
                        System.out.println();
                        System.out.println("Current Family History:");
                        System.out.println("==================");
                        for (String p : pedigreeList) System.out.println(p);
                        String oldHistory, newHistory;
                        System.out.println("==================");
                        while (true) {
                            System.out.println();
                            System.out.print("Enter the exact Family History to update: ");
                            oldHistory = INPUT.nextLine().trim();
                            if (!oldHistory.isEmpty()) 
                                break; // error trap for empty input
                            else {
                                System.out.println();
                                System.out.println("Cannot Be Empty.");
                            }
                        }
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Family History: ");
                            newHistory = INPUT.nextLine().trim();
                            if (!newHistory.isEmpty()) 
                                break; // error trap for empty input
                            else {
                                System.out.println();
                                System.out.println("Cannot Be Empty.");
                            }
                        }
                        // sends old family history and new family history to main system to update the patient record
                        mainsystem.UPDATE_SPECIFIC_PEDIGREE(patientId, oldHistory, newHistory);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 11) {
                        // Immunizations
                        List<String> immunizationsList = mainsystem.GET_IMMUNIZATIONS(patientId);
                        System.out.println();
                        System.out.println("Current Immunizations:");
                        System.out.println("==================");
                        for (String i : immunizationsList) System.out.println(i);
                        String oldImmunization, newImmunization;
                        System.out.println("==================");
                        while (true) {
                            System.out.println();
                            System.out.print("Enter the exact Immunization to update: ");
                            oldImmunization = INPUT.nextLine().trim();
                            if (!oldImmunization.isEmpty()) 
                                break; // error trap for empty input
                            else {
                                System.out.println();
                                System.out.println("Cannot Be Empty.");
                            }
                        }
                        while (true) {
                            System.out.println();
                            System.out.print("Enter New Immunization: ");
                            newImmunization = INPUT.nextLine().trim();
                            if (!newImmunization.isEmpty()) 
                                break; // error trap for empty input
                            else {
                                System.out.println();
                                System.out.println("Cannot Be Empty.");
                            }
                        }
                        // sends old immunization and new immunization to main system to update the patient record
                        mainsystem.UPDATE_SPECIFIC_IMMUNIZATION(patientId, oldImmunization, newImmunization);
                        System.out.println("Update Successful");
                    }

                    else if (updatechoice == 12) {
                        System.out.println();
                        System.out.println("Returning Back To EMHAS Edit Choices....");
                        break; // exit the update patient data menu and return to the main edit menu
                    }
                }
                
            } else if (option == 4) {
                System.out.println();
                System.out.println("Returning to EMHAS Menu System.... ");
                break; // exit the edit patient menu and return to the main EMHAS menu
            }
        } 
        // returs the patient id to the main menu after editing is done
        return patientId;
    }
}