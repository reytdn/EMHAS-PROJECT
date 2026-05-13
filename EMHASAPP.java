import java.util.*;


public class EMHASAPP {
    public static void second(String Role, MAINSYSTEM mainsystem, Scanner INPUT) {

        while (true) {
            if (Role.equalsIgnoreCase("Admin")){
                System.out.println();
                System.out.println("========================================");
                System.out.println("|          EMHAS MENU SYSTEM           |");
                System.out.println("|======================================|");
                System.out.println("| 1. Register New Patient              |");
                System.out.println("| 2. Search Patient Record             |");
                System.out.println("| 3. Edit Patient Record               |");
                System.out.println("| 4. Access Patient Record             |");
                System.out.println("| 5. View Emergency Access Logs        |");
                System.out.println("| 6. Register Ambulance/Hospital User  |");
                System.out.println("| 7. Return To Main Menu               |");
                System.out.println("========================================");
                System.out.println();
                System.out.print("Select Option: ");
                int Option = INPUT.nextInt();
                INPUT.nextLine();

                if (Option == 1){
                    
                }

                else if (Option == 2){

                }

                else if (Option == 3){

                }

                else if (Option == 4){

                }

                else if (Option == 5){

                }

                else if (Option == 6){
                    EMHASREGISTER register = new EMHASREGISTER(mainsystem, INPUT);
                    register.REGISTERPERSONNELS();

                }

                else if (Option == 7){
                    System.out.println();
                    System.out.println("Returning Back To Main Menu.....");
                    break;
                } else {
                    System.out.println();
                    System.out.println("Invalid Choice. Choose Options 1-7 Only.");
                    continue;
                }


            } else if (Role.equalsIgnoreCase("User")){
                System.out.println();
                System.out.println("========================================");
                System.out.println("|          EMHAS MENU SYSTEM           |");
                System.out.println("|======================================|");
                System.out.println("| 1. Register New Patient              |");
                System.out.println("| 2. Search Patient Record             |");
                System.out.println("| 3. Edit Patient Record               |");
                System.out.println("| 4. Access Patient Record             |");
                System.out.println("| 5. View Emergency Access Logs        |");
                System.out.println("| 6. Return To Main Menu               |");
                System.out.println("========================================");
                System.out.println();
                System.out.print("Select Option: ");
                int Option = INPUT.nextInt();
                INPUT.nextLine();

                if (Option == 1){
                    
                }

                else if (Option == 2){

                }

                else if (Option == 3){

                }

                else if (Option == 4){

                }

                else if (Option == 5){

                }

                else if (Option == 6){
                    System.out.println();
                    System.out.println("Returning Back To Main Menu.....");
                    break;
                } else {
                    System.out.println();
                    System.out.println("Invalid Choice. Choose Options 1-6 Only.");
                    continue;
                }
            }
            


        }
    }    
}
