import java.sql.*;

public class DATACONNECTION {
    // constant for database URL
    private static final String URL = "jdbc:mysql://localhost:3306/emhas";
    
    // constant for database username
    private static final String USER = "root";
    
    // constant for database password
    private static final String PASSWORD = "";

    // method to establish and return a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


