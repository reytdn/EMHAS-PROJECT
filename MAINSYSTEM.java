
import java.sql.*;
import java.util.List;


public class MAINSYSTEM {
    
    public boolean TEST_LOGIN(String username, String password, String role){
        String table = "";
        
        if (role.equalsIgnoreCase("Admin")){
            table = "admin";
        } else if (role.equalsIgnoreCase("Medical Technician")){
            table = "emergency_medical_technicians";
        } else if (role.equalsIgnoreCase("ER Physician")){
            table = "emergency_physicians";
        } else if (role.equalsIgnoreCase("ER Nurse")){
            table = "er_nurses";
        } else if (role.equalsIgnoreCase("Paramedic")){
            table = "paramedics";
        } else {
            System.out.println();
            System.out.println("Invalid Profession Selected.");
            return false;
        }
        String SELECTQUERY = "SELECT username, password FROM " + table + " WHERE username = ? AND password = ?";
        try (Connection connection = DATACONNECTION.getConnection()){
            PreparedStatement prepstat = connection.prepareStatement(SELECTQUERY);
            prepstat.setString(1, username);
            prepstat.setString(2, password);
            ResultSet resultSet = prepstat.executeQuery();
            return resultSet.next();
        } catch (SQLException e){
            System.out.println();
            System.out.println("Login Failed: " + e.getMessage());
            return false;
        }
    }

    public boolean REGISTER_PATIENT(String patientid, String fname, String lname, String mi, String emergencycontact, String bloodtype,
        List<String> allergies, List<String> conditions, List<String> medications){
            try(Connection connection = DATACONNECTION.getConnection()){
                String INSERTQUERYPATIENT = "INSERT INTO patients (patientid, fname, lname, mi, emergencycontact, bloodtype) VALUES (?, ?, ? ,?, ?, ?)";
                PreparedStatement preppat = connection.prepareStatement(INSERTQUERYPATIENT);
                preppat.setString(1, patientid);
                preppat.setString(2, fname);
                preppat.setString(3, lname);
                preppat.setString(4, mi);
                preppat.setString(5, emergencycontact);
                preppat.setString(6, bloodtype);
                preppat.executeUpdate();

                String INSERTQUERYALLERGIES = "INSERT INTO patient_allergies (patientid, allergies) VALUES (?, ?)";
                PreparedStatement prepallergy = connection.prepareStatement(INSERTQUERYALLERGIES);
                for (String allergy : allergies){
                    prepallergy.setString(1, patientid);
                    prepallergy.setString(2, allergy); 
                    prepallergy.executeUpdate();
                }
                


                String INSERTQUERYCONDITIONS = "INSERT INTO patient_conditions (patientid, conditions) VALUES (?, ?)";
                PreparedStatement prepcondition = connection.prepareStatement(INSERTQUERYCONDITIONS);
                for (String condition : conditions){
                    prepcondition.setString(1, patientid);
                    prepcondition.setString(2, condition); 
                    prepcondition.executeUpdate();
                }

                String INSERTQUERYMEDICATIONS = "INSERT INTO patient_medications (patientid, medications) VALUES (?, ?)";
                PreparedStatement prepmedication = connection.prepareStatement(INSERTQUERYMEDICATIONS);
                for (String medication : medications){
                    prepmedication.setString(1, patientid);
                    prepmedication.setString(2, medication); 
                    prepmedication.executeUpdate();
                }
                return true;
                
            } catch (SQLException e){
                System.out.println();
                System.out.println("Error registring patient: " + e.getMessage());
                return false;
            }
        }

    public boolean REGISTER_USER(String fname, String lname, String mi, String profession, String username, String password){
        String table = "";

        if (profession.equalsIgnoreCase("Medical Technician")) {
            table = "emergency_medical_technicians";
        } else if (profession.equalsIgnoreCase("ER Physician")) {
            table = "emergency_physicians";
        } else if (profession.equalsIgnoreCase("ER Nurse")) {
            table = "er_nurses";
        } else if (profession.equalsIgnoreCase("Paramedic")) {
            table = "paramedics";
        } else {
            System.out.println();
            System.out.println("Invalid profession.");
            return false;
        }

        String INSERTQUERYUSER = "INSERT INTO " + table + " (fname, lname, mi, profession, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection connection = DATACONNECTION.getConnection()){
            PreparedStatement prepstat = connection.prepareStatement(INSERTQUERYUSER);
            prepstat.setString(1, fname);
            prepstat.setString(2, lname);
            prepstat.setString(3, mi);
            prepstat.setString(4, profession);
            prepstat.setString(5, username);
            prepstat.setString(6, password);
            return prepstat.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println();
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }
}


