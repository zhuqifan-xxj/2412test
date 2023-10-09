import org.junit.jupiter.api.Test;

import apis.Admin;
import core.User;

import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.sql.SQLException;

public class AdminTest {

    @Test
    public void testViewAllUsers() {
        Admin admin = new Admin();
        try {
            List<User> users = admin.viewAllUsers();
            Assertions.assertNotNull(users);  // Ensures that the method does not return a null
            Assertions.assertFalse(users.isEmpty());  // Ensures that the method returns a list with at least one user
        } catch (SQLException e) {
            Assertions.fail("SQLException thrown: " + e.getMessage());
        }
    }

    @Test
    public void testViewAllUsersDatabaseError() {
        Admin admin = new Admin();
        Assertions.assertThrows(SQLException.class, () -> {
            admin.viewAllUsers();  // Assume a scenario where a database error occurs (this would typically be mocked)
        });
    }
}
