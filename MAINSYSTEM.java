import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;

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
            PreparedStatement prepuser = connection.prepareStatement(INSERTQUERYUSER);
            prepuser.setString(1, fname);
            prepuser.setString(2, lname);
            prepuser.setString(3, mi);
            prepuser.setString(4, profession);
            prepuser.setString(5, username);
            prepuser.setString(6, password);
            return prepuser.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println();
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public List<String> ACCESS_EMERGENCY(String patientId) {
        List<String> logs = new ArrayList<>();
        try(Connection connection = DATACONNECTION.getConnection()) {
            
            // Patient core info
            String patientQuery = "SELECT fname, lname, mi, bloodtype, emergencycontact FROM patients WHERE patientid = ?";
            PreparedStatement stmt = connection.prepareStatement(patientQuery);
            stmt.setString(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                logs.add("Patient: " + rs.getString("fname") + " " + rs.getString("mi") + " " + rs.getString("lname"));
                logs.add("Blood Type: " + rs.getString("bloodtype"));
                logs.add("Emergency Contact: " + rs.getString("emergencycontact"));
            }

            // Allergies
            String allergyQuery = "SELECT allergies FROM patient_allergies WHERE patientid = ?";
            stmt = connection.prepareStatement(allergyQuery);
            stmt.setString(1, patientId);
            rs = stmt.executeQuery();
            List<String> allergies = new ArrayList<>();
            while(rs.next()) allergies.add(rs.getString("allergies"));
            logs.add("Allergies: " + String.join(", ", allergies));

            // Conditions
            String conditionQuery = "SELECT conditions FROM patient_conditions WHERE patientid = ?";
            stmt = connection.prepareStatement(conditionQuery);
            stmt.setString(1, patientId);
            rs = stmt.executeQuery();
            List<String> conditions = new ArrayList<>();
            while(rs.next()) conditions.add(rs.getString("conditions"));
            logs.add("Conditions: " + String.join(", ", conditions));

            // Medications
            String medicationQuery = "SELECT medications FROM patient_medications WHERE patientid = ?";
            stmt = connection.prepareStatement(medicationQuery);
            stmt.setString(1, patientId);
            rs = stmt.executeQuery();
            List<String> medications = new ArrayList<>();
            while(rs.next()) medications.add(rs.getString("medications"));
            logs.add("Medications: " + String.join(", ", medications));

        } catch(Exception e) {
            System.out.println("Error fetching patient logs: " + e.getMessage());
        }
        return logs;
    }

    public void LOG_ACCESS(String fullname, String profession, String patientId, String action) {
        String query = "INSERT INTO access_logs (fullname, profession, patientid, action) VALUES (?, ?, ?, ?)";
        try(Connection connection = DATACONNECTION.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fullname);
            stmt.setString(2, profession);
            stmt.setString(3, patientId);
            stmt.setString(4, action);
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error logging access: " + e.getMessage());
        }
    }

    // Fetch logs with date and time separated
    public List<String> GET_ALL_ACCESS_LOGS() {
        List<String> logs = new ArrayList<>();
        String query = "SELECT fullname, profession, patientid, action, timestamp FROM access_logs ORDER BY timestamp DESC";
        try(Connection connection = DATACONNECTION.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery()) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            while (resultSet.next()) {
                Timestamp ts = resultSet.getTimestamp("timestamp");
                String datePart = dateFormat.format(ts);
                String timePart = timeFormat.format(ts);

                String logEntry =
                    "User: " + resultSet.getString("fullname") +
                    "\nProfession: " + resultSet.getString("profession") +
                    "\nAccessed PatientID: " + resultSet.getString("patientid") +
                    "\nAction: " + resultSet.getString("action") +
                    "\nDate: " + datePart +
                    "\nTime: " + timePart +
                    "\n----------------------------------------";

                logs.add(logEntry);
            }
        } catch(SQLException e){
            System.out.println("Error fetching logs: " + e.getMessage());
        }
        return logs;
    }

    // Build full name from fname, mi, lname in profession tables
    public String GET_FULLNAME(String username, String profession) {
        String fullName = username; // fallback if not found
        String table = "";

        // IMPORTANT: match table names exactly as in phpMyAdmin
        if (profession.equals("Paramedic")) table = "paramedics";
        else if (profession.equals("ER Nurse")) table = "er_nurses";
        else if (profession.equals("ER Physician")) table = "emergency_physicians";
        else if (profession.equals("Medical Technician")) table = "medical_technicians"; // <-- fix here
        else if (profession.equals("Admin")) table = "admin";

        String query = "SELECT fname, mi, lname FROM " + table + " WHERE username = ?";
        try(Connection connection = DATACONNECTION.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String mi = rs.getString("mi");

                if (mi != null && !mi.isEmpty()) {
                    fullName = fname + " " + mi + ". " + lname;
                } else {
                    fullName = fname + " " + lname;
                }
            }
        } catch(SQLException e){
            System.out.println("Error fetching full name: " + e.getMessage());
        }
        return fullName;
    }


}




