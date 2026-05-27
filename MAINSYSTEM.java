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

    public boolean REGISTER_PATIENT(String patientid, String fname, String lname, String mi,
        String dob_month, int dob_day, int dob_year, String gender, int age,
        String emergencycontact, String bloodtype,
        String barangay, String city, String province,
        List<String> allergies, List<String> conditions, List<String> medications,
        List<String> familyhistory, List<String> immunization) {

        try (Connection connection = DATACONNECTION.getConnection()) {
            // Insert patient core info
            String INSERTQUERYPATIENT =
                "INSERT INTO patients (patientid, fname, lname, mi, gender, age, emergencycontact, bloodtype, dob_month, dob_day, dob_year, barangay, city, province) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preppat = connection.prepareStatement(INSERTQUERYPATIENT);
            preppat.setString(1, patientid);
            preppat.setString(2, fname);
            preppat.setString(3, lname);
            preppat.setString(4, mi);
            preppat.setString(5, gender);
            preppat.setInt(6, age);
            preppat.setString(7, emergencycontact);
            preppat.setString(8, bloodtype);
            preppat.setString(9, dob_month);
            preppat.setInt(10, dob_day);
            preppat.setInt(11, dob_year);
            preppat.setString(12, barangay);
            preppat.setString(13, city);
            preppat.setString(14, province);
            preppat.executeUpdate();

            // Allergies
            String INSERTQUERYALLERGIES = "INSERT INTO patient_allergies (patientid, allergies) VALUES (?, ?)";
            PreparedStatement prepallergy = connection.prepareStatement(INSERTQUERYALLERGIES);
            for (String allergy : allergies) {
                prepallergy.setString(1, patientid);
                prepallergy.setString(2, allergy);
                prepallergy.executeUpdate();
            }

            // Conditions
            String INSERTQUERYCONDITIONS = "INSERT INTO patient_conditions (patientid, conditions) VALUES (?, ?)";
            PreparedStatement prepcondition = connection.prepareStatement(INSERTQUERYCONDITIONS);
            for (String condition : conditions) {
                prepcondition.setString(1, patientid);
                prepcondition.setString(2, condition);
                prepcondition.executeUpdate();
            }

            // Medications
            String INSERTQUERYMEDICATIONS = "INSERT INTO patient_medications (patientid, medications) VALUES (?, ?)";
            PreparedStatement prepmedication = connection.prepareStatement(INSERTQUERYMEDICATIONS);
            for (String medication : medications) {
                prepmedication.setString(1, patientid);
                prepmedication.setString(2, medication);
                prepmedication.executeUpdate();
            }

            // Family Medical History (list)
            String INSERTQUERYFAMILY = "INSERT INTO patient_family_history (patientid, pedigree) VALUES (?, ?)";
            PreparedStatement prepfamily = connection.prepareStatement(INSERTQUERYFAMILY);
            for (String pedigree : familyhistory) {
                prepfamily.setString(1, patientid);
                prepfamily.setString(2, pedigree);
                prepfamily.executeUpdate();
            }

            // Immunizations (list)
            String INSERTQUERYIMMUNIZATION = "INSERT INTO patient_immunizations (patientid, vaccine) VALUES (?, ?)";
            PreparedStatement prepimmunization = connection.prepareStatement(INSERTQUERYIMMUNIZATION);
            for (String vaccine : immunization) {
                prepimmunization.setString(1, patientid);
                prepimmunization.setString(2, vaccine);
                prepimmunization.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Error registering patient: " + e.getMessage());
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

    public PatientRecord ACCESS_EMERGENCY(String patientId) {
        try (Connection connection = DATACONNECTION.getConnection()) {

            String name = "";
            String bloodType = "";
            String emergencyContact = "";
            List<String> allergies = new ArrayList<>();
            List<String> conditions = new ArrayList<>();
            List<String> medications = new ArrayList<>();
            List<String> familyHistory = new ArrayList<>();
            List<String> immunizations = new ArrayList<>();

            // Patient core info
            String patientQuery = "SELECT fname, lname, mi, bloodtype, emergencycontact FROM patients WHERE patientid = ?";
            PreparedStatement stmt = connection.prepareStatement(patientQuery);
            stmt.setString(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String mi = rs.getString("mi");
                if (mi != null && !mi.isEmpty()) mi = mi + ".";
                else mi = "";
                name = rs.getString("fname") + " " + mi + " " + rs.getString("lname");
                bloodType = rs.getString("bloodtype");
                emergencyContact = rs.getString("emergencycontact");
            }

            // Allergies
            stmt = connection.prepareStatement("SELECT allergies FROM patient_allergies WHERE patientid = ?");
            stmt.setString(1, patientId);
            rs = stmt.executeQuery();
            while (rs.next()) allergies.add(rs.getString("allergies"));

            // Conditions
            stmt = connection.prepareStatement("SELECT conditions FROM patient_conditions WHERE patientid = ?");
            stmt.setString(1, patientId);
            rs = stmt.executeQuery();
            while (rs.next()) conditions.add(rs.getString("conditions"));

            // Medications
            stmt = connection.prepareStatement("SELECT medications FROM patient_medications WHERE patientid = ?");
            stmt.setString(1, patientId);
            rs = stmt.executeQuery();
            while (rs.next()) medications.add(rs.getString("medications"));

            // Family Medical History
            stmt = connection.prepareStatement("SELECT pedigree FROM patient_family_history WHERE patientid = ?");
            stmt.setString(1, patientId);
            rs = stmt.executeQuery();
            while (rs.next()) familyHistory.add(rs.getString("pedigree"));

            // Immunizations
            stmt = connection.prepareStatement("SELECT vaccine FROM patient_immunizations WHERE patientid = ?");
            stmt.setString(1, patientId);
            rs = stmt.executeQuery();
            while (rs.next()) immunizations.add(rs.getString("vaccine"));

            return new PatientRecord(name, bloodType, emergencyContact,
                                    allergies, conditions, medications,
                                    familyHistory, immunizations);

        } catch (Exception e) {
            System.out.println("Error fetching patient record: " + e.getMessage());
            return null;
        }
    }


    public boolean IS_PATIENT_REGISTERED(String patientId) {
        try (Connection connection = DATACONNECTION.getConnection();
            PreparedStatement prepcheck = connection.prepareStatement("SELECT COUNT(*) FROM patients WHERE patientid = ?")) {
            prepcheck.setString(1, patientId);
            ResultSet rs = prepcheck.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking patient ID: " + e.getMessage());
        }
        return false;
    }

    public void SHOW_PATIENTS() {
        try(Connection connection =DATACONNECTION.getConnection()) {
        String query = "SELECT patientid, fname, lname FROM patients ORDER BY lname ASC";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        System.out.println();
        System.out.println("========================================");
        System.out.println("|         REGISTERED PATIENTS          |");
        System.out.println("========================================");

        int count = 1;

        while(rs.next()) {

            String patientid = rs.getString("patientid");
            String fullname = rs.getString("lname") + ", " + rs.getString("fname");
            System.out.println( count + ". " + patientid + " - " +fullname);
            count++;
        }

        if(count == 1) {
            System.out.println("No Registered Patients.");
        }

        System.out.println("========================================");

        } catch(SQLException e) {

            System.out.println();
            System.out.println("Error Loading Patients: " + e.getMessage());
        }
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
        else if (profession.equals("Medical Technician")) table = "emergency_medical_technicians"; 
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
            System.out.println();
        }
        return fullName;
    }


    public List<String> SEARCH_PATIENT(String patientid){
        List<String> logs = new ArrayList<>();
        try (Connection connection = DATACONNECTION.getConnection()){

           String SEARCHQUERYPATIENT = "SELECT fname, lname, mi, dob_month, dob_day, dob_year, age, gender, " +
               "bloodtype, emergencycontact, barangay, city, province " +
               "FROM patients WHERE patientid = ?";

            PreparedStatement prepsearch = connection.prepareStatement(SEARCHQUERYPATIENT);
            prepsearch.setString(1, patientid);
            ResultSet rs = prepsearch.executeQuery();

            if (rs.next()) {
                String mi = rs.getString("mi");
                if (mi != null && !mi.isEmpty()) {
                    mi = mi + ".";
                } else {
                    mi = "";
                }

                logs.add("Patiend ID: " + patientid);
                logs.add("Name: " + rs.getString("fname") + " " + mi + " " + rs.getString("lname"));
                logs.add("Date of Birth: " + rs.getString("dob_month") + " " + rs.getInt("dob_day") + ", " + 
                        rs.getInt("dob_year"));
                logs.add("Age: " + rs.getInt("age"));
                logs.add("Gender: " + rs.getString("gender"));
                logs.add("Blood Type: " + rs.getString("bloodtype"));
                logs.add("Emergency Contact: " + rs.getString("emergencycontact"));
                logs.add("Address: " + "Brgy. " + rs.getString("barangay") + ", " + rs.getString("city") + " City"
                        + ", " + rs.getString("province"));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching patient details: " + e.getMessage());
        }
        return logs;
    }
    // Update patient name
    public boolean UPDATE_NAME(String patientId, String fname, String mi, String lname) {
        String query = "UPDATE patients SET fname = ?, mi = ?, lname = ? WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fname);
            stmt.setString(2, mi);
            stmt.setString(3, lname);
            stmt.setString(4, patientId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating name: " + e.getMessage());
            return false;
        }
    }

    // Update patient address
    public boolean UPDATE_ADDRESS(String patientId, String barangay, String city, String province) {
        String query = "UPDATE patients SET barangay = ?, city = ?, province = ? WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, barangay);
            stmt.setString(2, city);
            stmt.setString(3, province);
            stmt.setString(4, patientId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating address: " + e.getMessage());
            return false;
        }
    }

    // Update patient birthdate
    public boolean UPDATE_BIRTHDATE(String patientId, String month, int day, int year) {
        String query = "UPDATE patients SET dob_month = ?, dob_day = ?, dob_year = ? WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, month);
            stmt.setInt(2, day);
            stmt.setInt(3, year);
            stmt.setString(4, patientId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating birthdate: " + e.getMessage());
            return false;
        }
    }

    // Update patient blood type
    public boolean UPDATE_BLOODTYPE(String patientId, String bloodType) {
        String query = "UPDATE patients SET bloodtype = ? WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, bloodType);
            stmt.setString(2, patientId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating blood type: " + e.getMessage());
            return false;
        }
    }

    // Update patient age
    public boolean UPDATE_AGE(String patientId, int age) {
        String query = "UPDATE patients SET age = ? WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, age);
            stmt.setString(2, patientId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating age: " + e.getMessage());
            return false;
        }
    }

    // Update emergency contact
    public boolean UPDATE_EMERGENCYCONTACT(String patientId, String contact) {
        String query = "UPDATE patients SET emergencycontact = ? WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, contact);
            stmt.setString(2, patientId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating emergency contact: " + e.getMessage());
            return false;
        }
    }

    // Update allergies (replace old with new)
    public boolean UPDATE_ALLERGIES(String patientId, String allergy) {
        String deleteQuery = "DELETE FROM patient_allergies WHERE patientid = ?";
        String insertQuery = "INSERT INTO patient_allergies (patientid, allergies) VALUES (?, ?)";
        try (Connection connection = DATACONNECTION.getConnection()) {
            PreparedStatement del = connection.prepareStatement(deleteQuery);
            del.setString(1, patientId);
            del.executeUpdate();

            PreparedStatement ins = connection.prepareStatement(insertQuery);
            ins.setString(1, patientId);
            ins.setString(2, allergy);
            return ins.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating allergies: " + e.getMessage());
            return false;
        }
    }

    // Update conditions
    public boolean UPDATE_CONDITIONS(String patientId, String condition) {
        String deleteQuery = "DELETE FROM patient_conditions WHERE patientid = ?";
        String insertQuery = "INSERT INTO patient_conditions (patientid, conditions) VALUES (?, ?)";
        try (Connection connection = DATACONNECTION.getConnection()) {
            PreparedStatement del = connection.prepareStatement(deleteQuery);
            del.setString(1, patientId);
            del.executeUpdate();

            PreparedStatement ins = connection.prepareStatement(insertQuery);
            ins.setString(1, patientId);
            ins.setString(2, condition);
            return ins.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating conditions: " + e.getMessage());
            return false;
        }
    }

    // Update medications
    public boolean UPDATE_MEDICATIONS(String patientId, String medication) {
        String deleteQuery = "DELETE FROM patient_medications WHERE patientid = ?";
        String insertQuery = "INSERT INTO patient_medications (patientid, medications) VALUES (?, ?)";
        try (Connection connection = DATACONNECTION.getConnection()) {
            PreparedStatement del = connection.prepareStatement(deleteQuery);
            del.setString(1, patientId);
            del.executeUpdate();

            PreparedStatement ins = connection.prepareStatement(insertQuery);
            ins.setString(1, patientId);
            ins.setString(2, medication);
            return ins.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating medications: " + e.getMessage());
            return false;
        }
    }

    // Update pedigree (family medical history)
    public boolean UPDATE_PEDIGREE(String patientId, String pedigree) {
        String deleteQuery = "DELETE FROM patient_family_history WHERE patientid = ?";
        String insertQuery = "INSERT INTO patient_family_history (patientid, pedigree) VALUES (?, ?)";
        try (Connection connection = DATACONNECTION.getConnection()) {
            PreparedStatement del = connection.prepareStatement(deleteQuery);
            del.setString(1, patientId);
            del.executeUpdate();

            PreparedStatement ins = connection.prepareStatement(insertQuery);
            ins.setString(1, patientId);
            ins.setString(2, pedigree);
            return ins.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating pedigree: " + e.getMessage());
            return false;
        }
    }

    // Update immunizations
    public boolean UPDATE_IMMUNIZATIONS(String patientId, String vaccine) {
        String deleteQuery = "DELETE FROM patient_immunizations WHERE patientid = ?";
        String insertQuery = "INSERT INTO patient_immunizations (patientid, vaccine) VALUES (?, ?)";
        try (Connection connection = DATACONNECTION.getConnection()) {
            PreparedStatement del = connection.prepareStatement(deleteQuery);
            del.setString(1, patientId);
            del.executeUpdate();

            PreparedStatement ins = connection.prepareStatement(insertQuery);
            ins.setString(1, patientId);
            ins.setString(2, vaccine);
            return ins.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating immunizations: " + e.getMessage());
            return false;
        }
    }        
    public List<String> GET_ALLERGIES(String patientId) {
        List<String> allergies = new ArrayList<>();
        String query = "SELECT allergies FROM patient_allergies WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allergies.add(rs.getString("allergies"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching allergies: " + e.getMessage());
        }
        return allergies;
    }

    public boolean UPDATE_SPECIFIC_ALLERGY(String patientId, String oldAllergy, String newAllergy) {
        String query = "UPDATE patient_allergies SET allergies = ? WHERE patientid = ? AND allergies = ?";
        try (Connection connection = DATACONNECTION.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newAllergy);
            stmt.setString(2, patientId);
            stmt.setString(3, oldAllergy);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating allergy: " + e.getMessage());
            return false;
        }
    }

    // Get all conditions
    public List<String> GET_CONDITIONS(String patientId) {
        List<String> conditions = new ArrayList<>();
        String query = "SELECT conditions FROM patient_conditions WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                conditions.add(rs.getString("conditions"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching conditions: " + e.getMessage());
        }
        return conditions;
    }

    public boolean UPDATE_SPECIFIC_CONDITION(String patientId, String oldCondition, String newCondition) {
        String query = "UPDATE patient_conditions SET conditions = ? WHERE patientid = ? AND conditions = ?";
        try (Connection connection = DATACONNECTION.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newCondition);
            stmt.setString(2, patientId);
            stmt.setString(3, oldCondition);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating condition: " + e.getMessage());
            return false;
        }
    }

    // Get all medications
    public List<String> GET_MEDICATIONS(String patientId) {
        List<String> medications = new ArrayList<>();
        String query = "SELECT medications FROM patient_medications WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                medications.add(rs.getString("medications"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching medications: " + e.getMessage());
        }
        return medications;
    }

    public boolean UPDATE_SPECIFIC_MEDICATION(String patientId, String oldMed, String newMed) {
        String query = "UPDATE patient_medications SET medications = ? WHERE patientid = ? AND medications = ?";
        try (Connection connection = DATACONNECTION.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newMed);
            stmt.setString(2, patientId);
            stmt.setString(3, oldMed);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating medication: " + e.getMessage());
            return false;
        }
    }

    // Get all pedigree entries
    public List<String> GET_PEDIGREE(String patientId) {
        List<String> pedigree = new ArrayList<>();
        String query = "SELECT pedigree FROM patient_family_history WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pedigree.add(rs.getString("pedigree"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching pedigree: " + e.getMessage());
        }
        return pedigree;
    }

    public boolean UPDATE_SPECIFIC_PEDIGREE(String patientId, String oldPedigree, String newPedigree) {
        String query = "UPDATE patient_family_history SET pedigree = ? WHERE patientid = ? AND pedigree = ?";
        try (Connection connection = DATACONNECTION.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newPedigree);
            stmt.setString(2, patientId);
            stmt.setString(3, oldPedigree);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating pedigree: " + e.getMessage());
            return false;
        }
    }

    // Get all immunizations
    public List<String> GET_IMMUNIZATIONS(String patientId) {
        List<String> immunizations = new ArrayList<>();
        String query = "SELECT vaccine FROM patient_immunizations WHERE patientid = ?";
        try (Connection connection = DATACONNECTION.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                immunizations.add(rs.getString("vaccine"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching immunizations: " + e.getMessage());
        }
        return immunizations;
    }

    public boolean UPDATE_SPECIFIC_IMMUNIZATION(String patientId, String oldImmunization, String newImmunization) {
        String query = "UPDATE patient_immunizations SET vaccine = ? WHERE patientid = ? AND vaccine = ?";
        try (Connection connection = DATACONNECTION.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newImmunization);
            stmt.setString(2, patientId);
            stmt.setString(3, oldImmunization);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating immunization: " + e.getMessage());
            return false;
        }
    }

    public void DELETE_ALLERGY(String patientId, String allergy) {
        try (Connection conn = DATACONNECTION.getConnection()) {
            String sql = "DELETE FROM patient_allergies WHERE patientid = ? AND allergies = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            stmt.setString(2, allergy);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting allergy: " + e.getMessage());
        }
    }

    public void DELETE_CONDITION(String patientId, String condition) {
        try (Connection conn = DATACONNECTION.getConnection()) {
            String sql = "DELETE FROM patient_conditions WHERE patientid = ? AND conditions = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            stmt.setString(2, condition);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting condition: " + e.getMessage());
        }
    }

    public void DELETE_MEDICATION(String patientId, String medication) {
        try (Connection conn = DATACONNECTION.getConnection()) {
            String sql = "DELETE FROM patient_medications WHERE patientid = ? AND medications = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            stmt.setString(2, medication);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting medication: " + e.getMessage());
        }
    }

    public void DELETE_FAMILY_HISTORY(String patientId, String pedigree) {
        try (Connection conn = DATACONNECTION.getConnection()) {
            String sql = "DELETE FROM patient_family_history WHERE patientid = ? AND pedigree = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            stmt.setString(2, pedigree);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting family history: " + e.getMessage());
        }
    }

    public void DELETE_IMMUNIZATION(String patientId, String vaccine) {
        try (Connection conn = DATACONNECTION.getConnection()) {
            String sql = "DELETE FROM patient_immunizations WHERE patientid = ? AND vaccine = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            stmt.setString(2, vaccine);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting immunization: " + e.getMessage());
        }
    }

    public void ADD_ALLERGY(String patientId, String allergy) {
        try (Connection conn = DATACONNECTION.getConnection()) {
            String sql = "INSERT INTO patient_allergies (patientid, allergies) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            stmt.setString(2, allergy);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding allergy: " + e.getMessage());
        }
    }

    public void ADD_CONDITION(String patientId, String condition) {
        try (Connection conn = DATACONNECTION.getConnection()) {
            String sql = "INSERT INTO patient_conditions (patientid, conditions) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            stmt.setString(2, condition);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding condition: " + e.getMessage());
        }
    }

    public void ADD_MEDICATION(String patientId, String medication) {
        try (Connection conn = DATACONNECTION.getConnection()) {
            String sql = "INSERT INTO patient_medications (patientid, medications) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            stmt.setString(2, medication);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding medication: " + e.getMessage());
        }
    }

    public void ADD_FAMILY_HISTORY(String patientId, String pedigree) {
        try (Connection conn = DATACONNECTION.getConnection()) {
            String sql = "INSERT INTO patient_family_history (patientid, pedigree) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            stmt.setString(2, pedigree);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding family history: " + e.getMessage());
        }
    }

    public void ADD_IMMUNIZATION(String patientId, String vaccine) {
        try (Connection conn = DATACONNECTION.getConnection()) {
            String sql = "INSERT INTO patient_immunizations (patientid, vaccine) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);
            stmt.setString(2, vaccine);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding immunization: " + e.getMessage());
        }
    }
}
                        




