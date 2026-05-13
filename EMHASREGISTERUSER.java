import java.util.*;

public class EMHASREGISTERUSER {
    private MAINSYSTEM mainsystem;
    private Scanner INPUT;

    public EMHASREGISTERUSER(MAINSYSTEM mainsystem, Scanner INPUT){
        this.mainsystem = mainsystem;
        this.INPUT = INPUT;
    }

    public void REGISTERPERSONNELS(){
        System.out.println();
        System.out.print("Enter First Name: ");
        String fname = INPUT.nextLine();

        System.out.println();
        System.out.print("Enter Last Name: ");
        String lname = INPUT.nextLine();

        System.out.println();
        System.out.print("Enter Middle Initial: ");
        String mi = INPUT.nextLine();

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
        int Option = INPUT.nextInt();
        INPUT.nextLine();

        String profession = "";
        if (Option == 1){
            profession = "Medical Technician";
        } else if (Option == 2){
            profession = "ER Physician";
        } else if (Option == 3){
            profession = "ER Nurse";
        } else if (Option == 4){
            profession = "Paramedic";
        } else {
            System.out.println();
            System.out.println("Invalid Choice. Choose Options 1-4 Only.");
            return;
        }

        System.out.println();
        System.out.print("Enter your username: ");
        String username = INPUT.nextLine();

        System.out.println();
        System.out.print("Enter your password: ");
        String password = INPUT.nextLine();

        if(mainsystem.REGISTER_USER(fname, lname, mi, profession, username, password)) {
            System.out.println();
            System.out.println("User registered successfully!");
        } else {
            System.out.println();
            System.out.println("Failed to register user.");
        }
    }
}
