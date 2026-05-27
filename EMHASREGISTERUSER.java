import java.util.*;
 
public class EMHASREGISTERUSER {
    // main system reference where users are stored
    private MAINSYSTEM mainsystem;
 
    // scanner object for user input
    private Scanner INPUT;
 
    // connect this class to main system and scanner
    public EMHASREGISTERUSER(Scanner INPUT, MAINSYSTEM mainsystem){
        this.mainsystem = mainsystem;
        this.INPUT = INPUT;
    }
    // method for registering hospital personnel
    public void REGISTERPERSONNELS(){
 
        // get first name
        System.out.println();
        System.out.print("Enter First Name: ");
        String fname = INPUT.nextLine().trim();
        if (fname.isEmpty() && !fname.matches("[a-zA-Z]+")) {
            System.out.println();
            System.out.println("First Name Must Contain Letters Only And Cannot Be Empty.");
            return;
        }
 
        // get last name
        System.out.println();
        System.out.print("Enter Last Name: ");
        String lname = INPUT.nextLine().trim();
        if (lname.isEmpty() && !lname.matches("[a-zA-Z]+")) {
            System.out.println();
            System.out.println("Last Name Must Contain Letters Only And Cannot Be Empty.");
            return;
        }
 
        // get middle name
        System.out.println();
        System.out.print("Enter Middle Initial: ");
        String mi = INPUT.nextLine().trim();

        // must be exactly one letter, no symbols or dots
        if (mi.isEmpty() || !mi.matches("[A-Za-z]")) {
            System.out.println();
            System.out.println("Middle Initial Must Be Exactly One Letter (A-Z Only, No Symbols or Periods).");
            return;
        }
       
         // show profession menu
        System.out.println();
        System.out.println("========================================");
        System.out.println("|        USER PROFESSION OPTIONS       |");
        System.out.println("|======================================|");
        System.out.println("| 1. Medical Technician                |");
        System.out.println("| 2. ER Physician                      |");
        System.out.println("| 3. ER Nurse                          |");
        System.out.println("| 4. Paramedic                         |");
        System.out.println("========================================");
        System.out.println();
 
        // choose profession
        int Option = -1;
        try {
            System.out.print("Select Option: ");
            Option = INPUT.nextInt();
            INPUT.nextLine();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Invalid Input. Please Enter A Number Only.");
            INPUT.nextLine();
            return;
        }

        if (Option < 1 || Option > 4) {
            System.out.println("Invalid Choice. Choose Options 1-4 Only.");
            return;
        }
 
        // store profession
        String profession = "";
       
        // assign profession based on input
        if (Option == 1) profession = "Medical Technician";
        else if (Option == 2) profession = "ER Physician";
        else if (Option == 3) profession = "ER Nurse";
        else if (Option == 4) profession = "Paramedic";
 
        // get username
        System.out.println();
        System.out.print("Enter your username: ");
        String username = INPUT.nextLine();
 
        // get password
        System.out.println();
        System.out.print("Enter your password: ");
        String password = INPUT.nextLine();
 
 
        // send data to main system for registration
        if(mainsystem.REGISTER_USER(fname, lname, mi, profession, username, password)) {
            System.out.println();
 
            // yey success message
            System.out.println("User registered successfully!");
        } else {
 
            // awww failed message
            System.out.println();
            System.out.println("Failed To Register User.");
        }
    }
}