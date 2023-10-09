package ui.testers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ui.*;

/**
 * EmailValidator class checks if a given email is valid.
 * <p>
 * This class uses regular expressions to validate the format of the email. 
 * It implements the FormValidator interface.
 * </p>
 */
public class EmailValidator implements FormValidator {
  
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Validates if the given email is correctly formatted.
     * 
     * @param value The email to be validated.
     * @return true if the email is valid, false otherwise.
     */
    @Override
    public boolean validate(Object value) {
        if (!(value instanceof String)) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher((String) value);
        return matcher.matches();
    }

    /**
     * Provides an error message for invalid emails.
     * 
     * @return An error message as a String.
     */
    @Override
    public String getErrorMessage() {
        return "Invalid email format.";
    }
}
