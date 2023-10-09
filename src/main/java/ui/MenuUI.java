package ui;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * MenuUI class provides a text-based user interface to select an option.
 */
public abstract class MenuUI extends TerminalUI {

  /**
   * Initializes a new instance of MenuUI with customized Scanner and print stream (used in Junit).
   * @param out the custom print stream
   */
  public MenuUI(PrintStream out) {
    super(out);
  }

  /**
   * Initializes a new instance of MenuUI.
   */
  public MenuUI() {
    super();
  }

  /**
   * Renders the menu to the user and captures their choice from the available options.
   * <p>
   * This method fetches an array of {@link MenuCommand} objects by calling the {@link #getChoices()}
   * method, which should be overridden in subclasses to provide specific menu options. Each
   * MenuCommand encapsulates both the description and the action for the menu option.
   * </p>
   * <p>
   * Developers extending this class may want to override this method to perform additional tasks,
   * such as displaying a table or performing checks, prior to showing the menu options. In such cases,
   * perform the pre-menu tasks and then call `super.render()` to inherit the base menu functionality.
   * </p>
   *
   * @return The {@link MenuCommand} object corresponding to the user's chosen menu option.
   * @return null if the choice 
   */
  public MenuCommand render(Scanner sc) {
    MenuCommand[] choices = getChoices();
    
    int userChoice = -1;

    // Print all choices
    for (int i = 0; i < choices.length; i++) {
      out.println((i + 1) + ". " + choices[i].getDescription());
    }
    while (true) {
      out.print("Enter your choice (1-" + choices.length + ", or leave empty to go back): ");

      try {
        String inputStr = sc.nextLine();
        if (inputStr.length() == 0) return null;
        userChoice = Integer.parseInt(inputStr); // Convert string to integer
       
        if (userChoice >= 1 && userChoice <= choices.length) {
          break;
        } else {
          out.println("Choice out of range. Try again.");
        }
      } catch (NumberFormatException e) {
        out.println("Invalid input. Try again.");
      } catch (NoSuchElementException e) {
        return null;
      }
    }
    return choices[userChoice - 1]; // Convert to 0-based index
  }

  /**
   * Retrieves an array of MenuCommand objects, each representing a menu option.
   *
   * Implement this method in your subclass to provide a list of menu options specific
   * to that subclass' functionality. Each MenuCommand object should encapsulate both
   * the description and the action of the menu option.
   *
   * This method serves as a part of the Template Method Pattern, providing the specific
   * 'choices' that the generic MenuUI class will use to render the menu and handle user input.
   *
   * @return An array of MenuCommand objects representing the available choices for this menu.
   */
  public abstract MenuCommand[] getChoices();
}
