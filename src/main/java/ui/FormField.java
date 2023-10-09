package ui;

/**
 * The FormField class represents a single form field with attributes like label, requirement status, and data type.
 * <p>
 * This class is designed to be used in conjunction with the FormUI class. Each FormField object serves as metadata
 * for a corresponding field that the user will fill in using the text-based interface provided by FormUI.
 * </p>
 * <p>
 * The class defines three properties for a form field:
 * <ul>
 *     <li>{@code label}: A descriptive text for the field, displayed when the user is prompted for input.</li>
 *     <li>{@code isRequired}: Specifies whether filling the field is mandatory or optional.</li>
 *     <li>{@code dataType}: Specifies the expected type of the input (Number, Text, or Boolean).</li>
 * </ul>
 * </p>
 */
public class FormField {
    private String label; // The text label for this field.
    private boolean isRequired; // Whether this field is required.
    private FormDataType dataType; // The data type for this field.
    private FormValidator validator;  // the validator

    /**
     * Creates a new FormField with the given attributes.
     * @param label The text label for this field.
     * @param isRequired Whether this field is mandatory.
     * @param dataType The expected data type of this field.
     */
    public FormField(String label, boolean isRequired, FormDataType dataType) {
        this.label = label;
        this.isRequired = isRequired;
        this.dataType = dataType;
    }

    /**
     * Creates a new FormField with the given attributes.
     * @param label The text label for this field.
     * @param isRequired Whether this field is mandatory.
     * @param dataType The expected data type of this field.
     * @param validator A customized validator for checking the data format
     */
    public FormField(String label, boolean isRequired, FormDataType dataType, FormValidator validator) {
        this(label, isRequired, dataType);
        this.validator = validator;
    }

    /**
     * Retrieves the label of this FormField.
     * @return The label as a String.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Checks if this FormField is required.
     * @return true if required, false otherwise.
     */
    public boolean isRequired() {
        return isRequired;
    }

    /**
     * Retrieves the expected data type of this FormField.
     * @return The FormDataType enum value representing the expected data type.
     */
    public FormDataType getDataType() {
        return dataType;
    }

    /**
     * Retrieves the customized validator.
     * @return The customized validator object
     */
    public FormValidator getValidator() {
        return validator;
    }
}
