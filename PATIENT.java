import java.util.List;

public class PATIENT {
    
    private int id;
    private String fname;
    private String lname;
    private String mi;  
    private String bloodtype;
    private String emergencycontact;
    private List<String> allergies;
    private List<String> conditions;
    private List<String> medications;

    public PATIENT(int id, String fname, String lname, String mi, String bloodtype, String emergencycontact,
        List<String> allergies, List<String> conditions, List<String> medications){
            this.id = id;
            this.fname = fname;
            this.lname = lname;
            this.mi = mi;
            this.bloodtype = bloodtype;
            this.emergencycontact = emergencycontact;
            this.allergies = allergies;
            this.conditions = conditions;
            this.medications = medications;
    }

    public void DISPLAYALLDATA(){
        System.out.println();
        System.out.println("ID: " + id);
        System.out.println("Name: " + fname + " " + mi + " " + lname);
        System.out.println("Blood Type: " + bloodtype);
        System.out.println("Emergency Contact: " + emergencycontact);
        System.out.println("Allergies: " + String.join(", ", allergies));
        System.out.println("Conditions: " + String.join(", ", conditions));
        System.out.println("Medications: " + String.join(", ", medications));


    }

    public void DISPLAYCRITICALDATA(){
        System.out.println();
        System.out.println("Blood Type: " + bloodtype);
        System.out.println("Allergies: " + String.join(", ", allergies));
        System.out.println("Conditions: " + String.join(", ", conditions));
    }

    
}
