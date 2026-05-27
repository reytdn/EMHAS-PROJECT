import java.util.*; 

public class TESTLOGINADMIN {
 
    // Reference to the main system object
    private MAINSYSTEM mainsystem;
 
    // scanner object to read user input
    private Scanner scanner;
 
    // store logged in admin username, full name, and profession
    private String loggedInFullName;
    private String loggedInUserProfession = "Admin";
 
    // Constructor to initialize the main system and scanner
    public TESTLOGINADMIN(MAINSYSTEM mainsystem, Scanner scanner) {
        this.mainsystem = mainsystem;
        this.scanner = scanner;
    }
 
    // method to handle admin login requests, allows admins to attempt to log in with their credentials and tracks login attempts
    public boolean REQUEST() {
        // Maximum number of login attempts allowed
        int Attempts = 3;
 
        // Tracks whether login is successful or not
        boolean VALID = false;
 
        // Loop continues while attempts remain and login is not valid
        while (Attempts > 0 && !VALID) {
            // Ask user for username
            System.out.println();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
 
            // Ask user for password
            System.out.println();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
 
            // Check if credentials are correct and role is "Admin"
            if (mainsystem.TEST_LOGIN(username, password, "Admin")) {
                // Login successful
                System.out.println();
                System.out.println("Admin Login Successful!");
                VALID = true;
 
                // after successful login
                loggedInFullName = mainsystem.GET_FULLNAME(username, "Admin");
                loggedInUserProfession = "Admin";
            } else {
                // login failed, reduces attempts and informs user of remaining attempts
                System.out.println();
                Attempts--;
                System.out.println("Invalid Admin Credentials. Attempts Remaining: " + Attempts);
            }
        }
 
        // login failed after all attempts, inform user and terminate
        if (!VALID) {
            System.out.println("Terminated. Too Many Failed Admin Login Attempts.");
        }
 
        // return whether login was successful or not
        return VALID;
    }
 

    // getter for logged in admin full name
    public String getLoggedInFullName() {
        return loggedInFullName;
    }
 
    // getter for logged in admin profession
    public String getLoggedInUserProfession() {
        return loggedInUserProfession;
    }
}