import java.util.*;

public class TESTLOGINUSER {
    private MAINSYSTEM mainsystem;
    private Scanner scanner;

    public TESTLOGINUSER(MAINSYSTEM mainsystem, Scanner scanner){
        this.mainsystem = mainsystem;
        this.scanner = scanner;
    }

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
        System.out.print("Select Option: ");
        int Option = Integer.parseInt(scanner.nextLine());

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

        while (Attempts > 0 && !VALID) {
            System.out.println();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.println();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (mainsystem.TEST_LOGIN(username, password, profession)) {
                System.out.println();
                System.out.println("Login successful for " + profession + "!");
                VALID = true;
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
}