import java.util.*;


public class EMHASSEARCHPATIENT {
    private Scanner INPUT;
    private MAINSYSTEM mainsystem;
    
    public EMHASSEARCHPATIENT(MAINSYSTEM mainsystem, Scanner INPUT){
        this.mainsystem = mainsystem;
        this.INPUT = INPUT;
    }

    public String SEARCHPATIENT(){
        System.out.println();
        System.out.print("Enter Patient ID to Search: ");
        String patientid = INPUT.nextLine();

        List<String> logs = mainsystem.SEARCH_PATIENT(patientid);

        if (logs.isEmpty()){
            System.out.println();
            System.out.println("No details found for Patiend ID: " + patientid);
        } else {
            System.out.println("================================================================");
            System.out.println("|                        PATIENT DETAILS                       |");
            System.out.println("================================================================");
            for (String log : logs){
                System.out.println(log);
            }
            System.out.println("================================================================");
        }
        return patientid;
    }
    
}
