package core;


import java.sql.Connection;
import java.util.Scanner;

/**
 * Represents parameters of the current context.
 * <p>
 * This class serves as a container for parameters commonly used in database interactions such as
 * user registration and login. By using this class, you can easily add, remove, or modify parameters
 * without affecting classes that implement the database operation interface.
 * </p>
 */

public class Context {   
    private Connection conn;
    private User currentUser;
    private Scanner scanner;

    /**
     * Constructs a new instance of DatabaseOperationParams.
     *
     * @param conn        The database connection to be used for the operation.
     * @param currentUser The current user involved in the database operation.
     */
    public Context(Connection conn, User currentUser, Scanner scanner) {
        this.conn = conn;
        this.currentUser = currentUser;
        this.scanner = scanner;
    }

    /**
     * Gets the current database connection.
     *
     * @return The current database connection.
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * Sets the database connection.
     *
     * @param conn The new database connection.
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * Gets the current user involved in the database operation.
     *
     * @return The current user.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user involved in the database operation.
     *
     * @param currentUser The new current user.
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    
}
