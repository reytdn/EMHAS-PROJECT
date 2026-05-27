import java.util.List;

public class PATIENTRECORD {
    private String name;
    private String bloodType;
    private String emergencyContact;
    private List<String> allergies;
    private List<String> conditions;
    private List<String> medications;
    private List<String> familyHistory;
    private List<String> immunizations;

    public PATIENTRECORD(String name, String bloodType, String emergencyContact,
                         List<String> allergies, List<String> conditions,
                         List<String> medications, List<String> familyHistory,
                         List<String> immunizations) {
        this.name = name;
        this.bloodType = bloodType;
        this.emergencyContact = emergencyContact;
        this.allergies = allergies;
        this.conditions = conditions;
        this.medications = medications;
        this.familyHistory = familyHistory;
        this.immunizations = immunizations;
    }

    public String getName() { return name; }
    public String getBloodType() { return bloodType; }
    public String getEmergencyContact() { return emergencyContact; }
    public List<String> getAllergies() { return allergies; }
    public List<String> getConditions() { return conditions; }
    public List<String> getMedications() { return medications; }
    public List<String> getFamilyHistory() { return familyHistory; }
    public List<String> getImmunizations() { return immunizations; }
}
