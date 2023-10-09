import core.Context;
import core.Guest;
import pages.StartPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class VSAS {

  // TODO: the DB path will be updated to Qifan's configuration
  private static final String DB_URL = "jdbc:sqlite:VSAS.db";

  public static void main(String[] args) {
    Guest guest = new Guest();
    Scanner sc = new Scanner(System.in);
    Connection conn = null;
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(DB_URL);
      Context ctx = new Context(conn, guest, sc);
      new StartPage().render(ctx);
      conn.close();
      System.out.println("\nThank you for using Virtual Scroll Access System!");
    } catch (ClassNotFoundException e) {
      e.printStackTrace(); // Logging can be improved by using a logger instead of printing stack trace
      System.out.println("Database driver not found");
    } catch (SQLException e) {
      e.printStackTrace(); // Logging can be improved by using a logger instead of printing stack trace
      System.out.println("Database access error");
    }
  }
}
