package ui;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * FormUI is an abstract class that provides a text-based form interface.
 * <p>
 * It prompts the user to fill in various form fields. The specific form fields
 * are defined by the concrete subclass through the {@code getFields()} method.
 * This class handles type checking, mandatory/optional fields, and user input validation.
 * </p>
 */
public abstract class FormUI extends TerminalUI {

   /**
     * Must be overridden by subclasses to provide the specific fields for the form.
     * @return A LinkedHashMap where the key is the field name and the value is a FormField object
     * containing metadata about the field.
     */
  public abstract LinkedHashMap<String, FormField> getFields();

  /**
     * Constructor to initialize a new FormUI instance.
     * @param sc Scanner to read user input.
     * @param out PrintStream to display output.
     */
  public FormUI(PrintStream out) {
    super(out);
  }

  /**
   * Initializes a new instance of FormUI.
   */
  public FormUI() {
    super();
  }

  /**
     * The {@code fill()} method guides the user through the form field-by-field.
     * <p>
     * It validates the input according to the field's data type, checks if it's a mandatory field,
     * and asks the user to re-enter data if it's not valid. Ctrl+D can be used to exit the form.
     * </p>
     * @return A LinkedHashMap containing the user's responses, mapped by field name. Null if the user quits.
     */
  public LinkedHashMap<String, Object> fill(Scanner sc) {
    LinkedHashMap<String, Object> formData = new LinkedHashMap<>();
    
    // Loop through each field in the form
    for (String fieldName : getFields().keySet()) {
      FormField field = getFields().get(fieldName);
      // Keep asking for valid input until received
      while (true) {
        try {
          out.print(field.getLabel());
          if (field.isRequired()) {
            out.print(" (*required)");
          }
          if (field.getDataType() == FormDataType.BOOLEAN) {
            out.print(" (y/n)");
          }

          out.print(": ");
          String userInput = sc.nextLine().trim(); // Read the next line as a string

          // Check if the field is mandatory
          if (field.isRequired() && userInput.isEmpty()) {
            out.println("This field is mandatory. Please provide a value.");
            continue;
          }

          FormValidator v = field.getValidator();
          if (v != null && v.validate(userInput)) {
            throw new IllegalArgumentException(v.getErrorMessage());
          }

          // Conduct type checks and handle optional fields
          Object value = null;
          if (!userInput.isEmpty()) {
            switch (field.getDataType()) {
              case NUMBER:
                value = Integer.parseInt(userInput);
                break;
              case TEXT:
                value = userInput;
                break;
              case BOOLEAN:
                if ("y".equalsIgnoreCase(userInput)) {
                  value = true;
                } else if ("n".equalsIgnoreCase(userInput)) {
                  value = false;
                } else {
                  throw new IllegalArgumentException(
                    "Please enter 'y' or 'n'."
                  );
                }
                break;
            }
          }

          formData.put(fieldName, value);
          break;
        } catch (NumberFormatException e) {
          out.println("Invalid number. Try again.");
        } catch (IllegalArgumentException e) {
          out.println(e.getMessage() + " Try again.");
        } catch (NoSuchElementException e) {
          out.println("Stream ended. Exiting.");
          return null;
        }
      }
    }
    return formData;
  }
}
