import java.util.*;

public class MAINMENU {
    public static void main(String[] args) {
        Scanner INPUT = new Scanner(System.in);
        MAINSYSTEM mainsystem = new MAINSYSTEM();
        TESTLOGINUSER testloginuser = new TESTLOGINUSER(mainsystem, INPUT);
        TESTLOGINADMIN testloginadmin = new TESTLOGINADMIN(mainsystem, INPUT);

        while (true) {
            System.out.println();
            System.out.println("========================================");
            System.out.println("|          EMHAS MAIN MENU             |");
            System.out.println("|======================================|");
            System.out.println("| 1. ADMIN ROLE                        |");
            System.out.println("| 2. USER ROLE                         |");
            System.out.println("| 3. EXIT                              |");
            System.out.println("========================================");

            int Option = -1;
            boolean valid = false;

            // Error trap loop for main menu input
            while (!valid) {
                System.out.println();
                System.out.print("Choose Option: ");
                try {
                    Option = INPUT.nextInt();
                    INPUT.nextLine(); // clear buffer

                    if (Option >= 1 && Option <= 3) {
                        valid = true; // valid input
                    } else {
                        System.out.println();
                        System.out.println("Invalid Choice. Please enter 1-3 only.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println();
                    System.out.println("Invalid input! Please enter numbers only (1-3).");
                    INPUT.nextLine(); // clear invalid input
                }
            }

            // Handle valid options
            if (Option == 1) {
                // check admin table
                if (mainsystem.IS_USER_TABLE_EMPTY("admin")) {
                    System.out.println();
                    System.out.println("No User Found");
                } else if (testloginadmin.REQUEST()) {
                    String currentFullName = testloginadmin.getLoggedInFullName();
                    String userProfession = testloginadmin.getLoggedInUserProfession();
                    EMHASAPP.second("Admin", mainsystem, INPUT, currentFullName, userProfession);
                }
            } else if (Option == 2) {
                // check user tables (technicians, physicians, nurses, paramedics)
                if (mainsystem.IS_USER_TABLE_EMPTY("emergency_medical_technicians") &&
                    mainsystem.IS_USER_TABLE_EMPTY("emergency_physicians") &&
                    mainsystem.IS_USER_TABLE_EMPTY("er_nurses") &&
                    mainsystem.IS_USER_TABLE_EMPTY("paramedics")) {
                    System.out.println();
                    System.out.println("No User Found!");
                } else if (testloginuser.REQUEST()) {
                    String currentFullName = testloginuser.getLoggedInFullName();
                    String userProfession = testloginuser.getLoggedInUserProfession();
                    EMHASAPP.second("User", mainsystem, INPUT, currentFullName, userProfession);
                }
            } else if (Option == 3) {
                System.out.println();
                System.out.println("Exiting Program. Goodbye!");
                System.out.println();
                break;
            }
        }
    }
}
