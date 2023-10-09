package ui;

public interface FormValidator {
    /**
     * Validates if the given value is correctly formatted.
     * 
     * @param value The value to be validated.
     * @return true if the value is valid, false otherwise.
     */
    public boolean validate(Object value);

    /**
     * Provides an error message for invalid value.
     * 
     * @return An error message as a String.
     */
    public String getErrorMessage();
}

