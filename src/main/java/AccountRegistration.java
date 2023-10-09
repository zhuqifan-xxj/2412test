import org.sqlite.SQLiteException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

public class AccountRegistration {

    public static final String DB_URL = "jdbc:sqlite:VSAS.db";

    private static final int SALT_LENGTH = 16;

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    public static String hashPassword(String password, String salt) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String saltedPassword = password + salt;
            byte[] hashedBytes = md.digest(saltedPassword.getBytes());
            hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }



    public static void createAccount(Connection conn, String username, String password,String fullname, String email, String phoneNumber, String customIDKey) {
        try {

            String insertSQL = "INSERT INTO User(Username, Password, Salt, FullName, Email, PhoneNumber, UserType, CustomIDKey) VALUES(?, ?, ?,?,?,?,?,?)";

            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                String salt = AccountRegistration.generateSalt();  // Assuming you have a PasswordUtility class
                String hashedPassword = AccountRegistration.hashPassword(password, salt);

                pstmt.setString(1, username);
                pstmt.setString(2, hashedPassword);
                pstmt.setString(3, salt);
                pstmt.setString(4,fullname);
                pstmt.setString(5,email);
                pstmt.setString(6,phoneNumber);
                pstmt.setString(7,"GeneralUser");
                pstmt.setString(8,customIDKey);



                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting data into Admin table.");
        }
    }

    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL);
        createAccount(conn,"Qifan", "Soft2412", "Qifan Zhu", "qzhu9379@uni.sydney", "1234567890", "Regular");

    }
}