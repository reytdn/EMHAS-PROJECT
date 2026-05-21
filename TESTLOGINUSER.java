import java.util.*;

public class TESTLOGINUSER {

    // Reference to the main system object and scanner for user input
    private MAINSYSTEM mainsystem;
    private Scanner scanner;

    // store logged in user username, full name, and profession
    private String loggedInUserName;
    private String loggedInFullName;
    private String loggedInUserProfession;

    // Constructor to initialize the main system and scanner
    public TESTLOGINUSER(MAINSYSTEM mainsystem, Scanner scanner){
        this.mainsystem = mainsystem;
        this.scanner = scanner;
    }

    // method to handle user login requests, allows users to select their profession and attempt to log in with their credentials
    public boolean REQUEST(){
        //  Maximum number of login attempts allowed
        int Attempts = 3;
        boolean VALID = false;

        String profession = "";

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
        System.out.print("Select Option: ");
        int Option = Integer.parseInt(scanner.nextLine());
       
        // sets the profession based on the user's selection
        if (Option == 1){
            profession = "Medical Technician";
        }
        else if (Option == 2){
            profession = "ER Physician";
        }
        else if (Option == 3){
            profession = "ER Nurse";
        }
        else if (Option == 4){
            profession = "Paramedic";
        }
        else {
            System.out.println("Invalid choice. Choose Options 1-4 only.");
            return false;
        }

        // Loop continues while attempts remain and login is not valid
        while (Attempts > 0 && !VALID) {
            // ask user for username and password
            System.out.println();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.println();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // checks if their credentials are correct and if profession matches the selected profession
            if (mainsystem.TEST_LOGIN(username, password, profession)) {
                System.out.println();
                System.out.println("Login successful for " + profession + "!");
                VALID = true;

                // store logged in user info
                loggedInFullName = mainsystem.GET_FULLNAME(username, profession); // NEW FEATURE
                loggedInUserProfession = profession;
            } else {
                // login failed, reduces attempts and informs user of remaining attempts
                System.out.println();
                Attempts--;
                System.out.println("Invalid User Credentials. Attempts remaining: " + Attempts);
            }
        }

        // login failed after all attempts, inform user and terminate
        if (!VALID) {
            System.out.println();
            System.out.println("Terminated. Too many failed User login attempts.");
        }
        return VALID;
    }


    // getter for logged in user full name
    public String getLoggedInFullName() {
        return loggedInFullName;
    }

    // getter for logged in user profession
    public String getLoggedInUserProfession() {
        return loggedInUserProfession;
    }
}
