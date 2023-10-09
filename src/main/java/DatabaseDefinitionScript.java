import java.sql.*;

public class DatabaseDefinitionScript {

    private static final String DB_URL = "jdbc:sqlite:VSAS.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String createUserTable = "CREATE TABLE IF NOT EXISTS User (" +
                    "UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Username TEXT NOT NULL UNIQUE," +
                    "Password TEXT NOT NULL," +
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
                    "UploadDate TEXT," +
                    "FOREIGN KEY(UploaderID) REFERENCES User(UserID)" +
                    ");";
            stmt.execute(createScrollsTable);

            System.out.println("Database and tables created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}