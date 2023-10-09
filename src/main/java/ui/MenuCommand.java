package ui;
import core.Context;
/**
 * MenuCommand is an interface designed to encapsulate individual menu options as command objects.
 * This approach addresses the shortcomings of representing menu options simply as a string array,
 * where the risk of index errors can occur when adding new functionality. 
 * The interface is meant to be used in conjunction with the MenuUI class.
 * 
 * By defining each menu option as a MenuCommand object, each command can contain both 
 * its description and the functionality it represents, adhering to the Single Responsibility Principle.
 */
public interface MenuCommand {

    /**
     * Executes the command's primary functionality.
     * The implementation of this method should contain the logic associated with the menu option.
     */
    void execute(Context ctx);

    /**
     * Returns a human-readable description of the command.
     * This description is used by MenuUI for displaying the menu option to the user.
     *
     * @return A string containing the description of the menu option.
     */
    String getDescription();
}
