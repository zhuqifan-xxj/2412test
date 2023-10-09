package apis;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import core.User;

public class Admin {

    private static final String DB_URL = "jdbc:sqlite:VSAS.db";

    public static void main(String[] args) throws SQLException {
        viewAllUsers();
    }

    public static List<User> viewAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User;");

            while (rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("Email"),
                        rs.getString("FullName"),
                        rs.getString("PhoneNumber"),
                        rs.getString("UserID"),
                        rs.getString("UserType")
                );
                users.add(user);
            }

            rs.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // Logging can be improved by using a logger instead of printing stack trace
            throw new SQLException("Database driver not found", e);
        } catch (SQLException e) {
            e.printStackTrace();  // Logging can be improved by using a logger instead of printing stack trace
            throw new SQLException("Database access error", e);
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return users;
    }
}
