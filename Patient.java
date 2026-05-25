class Patient {
    String name;
    String address;
    String birthdate;
    String bloodType;
    int age;
    String emergencyContact;
    String allergies;
    String conditions;
    String medications;
    String pedigree;
    String immunizations;

    public Patient(String name, String address, String birthdate, String bloodType, int age,
                   String emergencyContact, String allergies, String conditions,
                   String medications, String pedigree, String immunizations) {
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
        this.bloodType = bloodType;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.allergies = allergies;
        this.conditions = conditions;
        this.medications = medications;
        this.pedigree = pedigree;
        this.immunizations = immunizations;
    }
}
