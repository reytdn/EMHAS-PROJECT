import java.util.*;

public class TESTLOGINUSER {

    // Reference to the main system object and scanner for user input
    private MAINSYSTEM mainsystem;
    private Scanner INPUT;

    // store logged in user username, full name, and profession
    private String loggedInFullName;
    private String loggedInUserProfession;

    // Constructor to initialize the main system and scanner
    public TESTLOGINUSER(MAINSYSTEM mainsystem, Scanner INPUT){
        this.mainsystem = mainsystem;
        this.INPUT = INPUT;
    }

    // method to handle user login requests, allows users to select their profession and attempt to log in with their credentials
    public boolean REQUEST(){
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

        int Option = -1;
        try {
            System.out.print("Choose Option: ");
            Option = Integer.parseInt(INPUT.nextLine());
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Invalid Choice. Choose Options 1-4 Only.");
            return false;
        }

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
            System.out.println();
            System.out.println("Invalid choice. Choose Options 1-4 only.");
            return false;
        }

        // Loop continues while attempts remain and login is not valid
        while (Attempts > 0 && !VALID) {
            System.out.println();
            System.out.print("Enter username: ");
            String username = INPUT.nextLine();
            System.out.println();
            System.out.print("Enter password: ");
            String password = INPUT.nextLine();

            if (mainsystem.TEST_LOGIN(username, password, profession)) {
                System.out.println();
                System.out.println("Login Successful for " + profession + "!");
                loggedInFullName = mainsystem.GET_FULLNAME(username, profession);
                loggedInUserProfession = profession;

                System.out.println("Welcome " + loggedInFullName + "!");
                VALID = true;

                loggedInFullName = mainsystem.GET_FULLNAME(username, profession);
                loggedInUserProfession = profession;
            } else {
                System.out.println();
                Attempts--;
                System.out.println("Invalid User Credentials. Attempts remaining: " + Attempts);
            }
        }

        if (!VALID) {
            System.out.println();
            System.out.println("Terminated. Too many failed User login attempts.");
        }
        return VALID;
    }

    public String getLoggedInFullName() {
        return loggedInFullName;
    }

    public String getLoggedInUserProfession() {
        return loggedInUserProfession;
    }
}
