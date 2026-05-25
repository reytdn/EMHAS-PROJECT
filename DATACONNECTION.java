import java.sql.*;

public class DATACONNECTION {
    private static final String URL = "jdbc:mysql://localhost:3306/emhas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    


    //Enter allergies:
    //Enter allergies:
    //Enter allergies: 
    //Enter allergies: 
    //Enter allergies (Enter Stop if Done): 

    //if 1
    //1. Add Infos(Allergies, Conditions, Medications)

    //1. Allergies
    //2. Conditions
    //3. Medications

    //if 2
    //2. Delete Infos(Allergies, Conditions, Medications)

    //1. Allergies
    //2. Conditions
    //3. Medications


    //if 3
    //3. Update Infos(First Name, Last Name, Middle Initial, Blood Type, Emergency Contact, Allergies, Conditions, Medications)

    //1. First Name
    //2. Last Name
    //3. Middle Initial
    //4. Blood Type
    //5. Emergency Contact
    //6. Allergies
    //7. Condition
    //8. Medication
    //9. Go Back To Edit Menu


    //if 4
    //4. Back To EMHAS Menu System

}




//1. Add Invoice
//2. Pay Invoice

//Choice: 2
//Enter Name: JUAN 

//JUAN | PAID:     | INVOICE #1 BALANCE: 1000
//JUAN | PAID:     | INVOICE #2 BALANCE: 2000
//JUAN | PAID:     | INVOICE #3 BALANCE: 3000

//Enter Amount to pay: 2000

//1. Add Invoice
//2. Pay Invoice

//Choice: 2
//Enter Name: JUAN 

//JUAN | PAID: 1000| INVOICE #2 BALANCE: 1000
//JUAN | PAID:     | INVOICE #3 BALANCE: 3000

//Enter Amount to pay: 2500

//1. Add Invoice
//2. Pay Invoice

//Choice: 2
//Enter Name: JUAN 


//JUAN | PAID: 1500| INVOICE #3 BALANCE: 1500