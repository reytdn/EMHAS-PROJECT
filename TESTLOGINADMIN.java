import java.util.*;

public class TESTLOGINADMIN {
    private MAINSYSTEM mainsystem;
    private Scanner scanner;

    public TESTLOGINADMIN(MAINSYSTEM mainsystem, Scanner scanner){
        this.mainsystem = mainsystem;
        this.scanner = scanner;
    }

    public boolean REQUEST(){
        int Attempts = 3;
        boolean VALID = false;

        while (Attempts > 0 && !VALID) {
            System.out.println();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (mainsystem.TEST_LOGIN(username, password, "Admin")) {
                System.out.println();
                System.out.println("Admin Login Successful!");
                VALID = true;
            } else {
                System.out.println();
                Attempts--;
                System.out.println("Invalid Admin Credentials. Attempts remaining: " + Attempts);
            }
        }

        if (!VALID) {
            System.out.println("Terminated. Too many failed Admin login attempts.");
        }
        return VALID;
    }
}
