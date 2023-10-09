import java.sql.*;
import java.io.File;
import java.sql.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;


public class DatabaseCreation {
    private static final String VSAS_DIR = System.getProperty("user.home") + File.separator + ".VSAS";

    public static final String DB = VSAS_DIR + File.separator + "VSAS.db";
    public static final String DB_URL = "jdbc:sqlite:" + DB;



    public static void main(String[] args) {

        File dir = new File(VSAS_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File dbFile = new File(VSAS_DIR + File.separator + "VSAS.db");
        if (dbFile.exists()) {
            System.out.println("VSAS.db database already exists.");
            return;
        }


        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String createUserTable = "CREATE TABLE IF NOT EXISTS User (" +
                    "UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Username TEXT NOT NULL UNIQUE," +
                    "Password TEXT NOT NULL," +
                    "Salt TEXT NOT NULL," +
                    "FullName TEXT," +
                    "Email TEXT," +
                    "PhoneNumber TEXT," +
                    "UserType TEXT," +
                    "CustomIDKey TEXT UNIQUE" +
                    ");";
            stmt.execute(createUserTable);

            String createScrollsTable = "CREATE TABLE IF NOT EXISTS DigitalScrolls (" +
                    "ScrollID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ScrollName TEXT NOT NULL," +
                    "UploaderID INTEGER," +
                    "BinaryFile BLOB," +
                    "UploadDate DATETIME," +
                    "FOREIGN KEY(UploaderID) REFERENCES User(UserID)" +
                    ");";
            stmt.execute(createScrollsTable);

            String insertUser = "INSERT INTO User (Username, Password, Salt, FullName, Email, PhoneNumber, UserType, CustomIDKey) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertUser)) {
                String salt = AccountRegistration.generateSalt();
                pstmt.setString(1, "Qifan");
                pstmt.setString(2, AccountRegistration.hashPassword("Soft2412",salt));
                pstmt.setString(3, salt);
                pstmt.setString(4, "Qifan Zhu");
                pstmt.setString(5, "qzhu@example.com");
                pstmt.setString(6, "1234567890");
                pstmt.setString(7, "ADMIN");
                pstmt.setString(8, "qzhu9379");
                pstmt.executeUpdate();
            }

            String insertScroll = "INSERT INTO DigitalScrolls (ScrollName, UploaderID, UploadDate) " +
                    "VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertScroll)) {
                pstmt.setString(1, "Example Scroll");
                pstmt.setInt(2, 1); // Assuming John Doe's UserID is 1
                pstmt.setDate(3, Date.valueOf(LocalDate.now())); // Assuming John Doe's UserID is 1
                pstmt.executeUpdate();
            }

            System.out.println("Database and tables created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}