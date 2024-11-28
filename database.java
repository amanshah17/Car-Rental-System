import java.sql.*;

public class database {
    private static final String URL = "jdbc:mysql://localhost:3306/mycar";  // Database URL
    private static final String USER = "root";  // MySQL username
    private static final String PASSWORD = "1234";  // MySQL password

    public static Connection connect() {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish and return the connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection connection, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
