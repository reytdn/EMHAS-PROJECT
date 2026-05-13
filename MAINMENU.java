import java.util.*;

public class MAINMENU {
    public static void main(String[] args) {
        Scanner INPUT = new Scanner(System.in);
        MAINSYSTEM mainsystem = new MAINSYSTEM();
        TESTLOGINUSER testloginuser = new TESTLOGINUSER(mainsystem, INPUT);
        TESTLOGINADMIN testloginadmin = new TESTLOGINADMIN(mainsystem, INPUT);

        while (true) {
            System.out.println();
            System.out.println("");
                System.out.println("=================S=======================");
                System.out.println("|          EMHAS MAIN MENU             |");
                System.out.println("|======================================|");
                System.out.println("| 1. ADMIN ROLE                        |");
                System.out.println("| 2. USER ROLE                         |");
                System.out.println("| 3. EXIT                              |");
                System.out.println("========================================");
                System.out.println();
                System.out.print("Select Option: ");
                int Option = INPUT.nextInt();
                INPUT.nextLine();

                if (Option == 1) {
                if (testloginadmin.REQUEST()) {
                    EMHASAPP.second("Admin", mainsystem, INPUT);
                }
            } else if (Option == 2) {
                if (testloginuser.REQUEST()) {
                    EMHASAPP.second("User", mainsystem, INPUT);
                }
            } else if (Option == 3) {
                System.out.println();
                System.out.println("Exiting Program. Goodbye!");
                break;
            } else {
                System.out.println();
                System.out.println("Invalid Choice. Choose Options 1-3 Only.");
            }
        }

        INPUT.close();
    }
}
