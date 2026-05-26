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
        String fname = INPUT.nextLine();
 
        // get last name
        System.out.println();
        System.out.print("Enter Last Name: ");
        String lname = INPUT.nextLine();
 
        // get middle name
        System.out.println();
        System.out.print("Enter Middle Initial: ");
        String mi = INPUT.nextLine(); //A.
       
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
        System.out.print("Select Option: ");
        int Option = INPUT.nextInt();
        INPUT.nextLine(); // clear input buffer
 
 
        // store profession
        String profession = "";
       
        // assign profession based on input
        if (Option == 1){
            profession = "Medical Technician";
        } else if (Option == 2){
            profession = "ER Physician";
        } else if (Option == 3){
            profession = "ER Nurse";
        } else if (Option == 4){
            profession = "Paramedic";
        } else {
            // invalid input
            System.out.println();
            System.out.println("Invalid Choice. Choose Options 1-4 Only.");
            return;
        }
 
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
            System.out.println("Failed to register user.");
        }
    }
}