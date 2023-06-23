import java.sql.*;

public class DBConnection {
    private static final String LocalDBURL = "jdbc:mysql://127.0.0.1:3306/e_commerce";
    private static final String LocalDBUsername = "root";
    private static final String LocalDBPassword = "Bhavi@1897";

    private static final String RemoteDBURL = "jdbc:mysql://34.69.25.158:3306/e_commerce";

    private static final String RemoteDBUsername = "root";
    private static final String RemoteDBPassword = "Bhavi@0118";

    public static Connection getLocalDBConnection() throws SQLException {
        return DriverManager.getConnection(LocalDBURL, LocalDBUsername, LocalDBPassword);
    }

    public static Connection getRemoteDBConnection() throws SQLException {
        return DriverManager.getConnection(RemoteDBURL, RemoteDBUsername, RemoteDBPassword);
    }

}
