package ui;
/**
 * The Page class serves as a base class for different pages in the terminal UI.
 * It provides a method to render a page with a title and user information in a visually appealing way.
 * 
 * <p>It extends the TerminalUI class, so it assumes that some terminal-based UI operations may be needed.</p>
 *
 * <p>This is an abstract class, which means you'll need to implement the 'run()' method when you create subclasses.</p>
 */
import java.io.PrintStream;
import java.util.Scanner;

import core.Context;

public abstract class Page extends TerminalUI {

    /**
     * The title for the Page.
     */
    private String title;

    /**
     * The menu used for the Page.
     */
    private MenuUI menu;

    /**
     * Gets the title of the page.
     *
     * @return The title of the page as a String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the page.
     *
     * @param title The title as a String.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    
    /**
     * Constructor to initialize the Page with a title and context.
     *
     * @param title The title of the page.

     */
    public Page(String title, MenuUI menu) {
        super();
        this.title = title;
        this.menu = menu;
    }

    /**
     * Overloaded constructor to specify a PrintStream.
     *
     * @param title The title of the page.
     * @param menu The menu used for this page.
     * @param out The PrintStream to use for output.
     */
    public Page(String title, MenuUI menu, PrintStream out) {
        super(out);
        this.title = title;
        this.menu = menu;
    }
    
    /**
     * Constructor to initialize the Page with a title and context.
     *
     * @param title The title of the page.

     */
    public Page(String title) {
        super();
        this.title = title;
    }

    /**
     * Overloaded constructor to specify a PrintStream.
     *
     * @param title The title of the page.
     * @param out The PrintStream to use for output.
     */
    public Page(String title, PrintStream out) {
        super(out);
        this.title = title;
    }

    /**
     * The showContent method needs to be implemented by any subclass.
     * This method will contain the core content for each specific page.
     */
    protected abstract void showContent(Context ctx);

    /**
     * Renders the page to the terminal, displaying the title and user information in a formatted box.
     * Calls the 'run()' method at the end, which should be implemented by the subclass.
     * 
     * @param ctx The Context object for the page.
     */
    public void printHeader(Context ctx) {
        System.out.println();
        String userInfo = "User: " + ctx.getCurrentUser().getUsername() + " | " + "Role: " + ctx.getCurrentUser().getUserType();
        int paddingSize = (48 - this.title.length()) / 2;
        int userPaddingSize = (48 - userInfo.length()) / 2;
    
        StringBuilder titlePadding = new StringBuilder();
        StringBuilder userPadding = new StringBuilder();
      
        for(int i = 0; i < paddingSize; i++) {
            titlePadding.append(" ");
        }
      
        for(int i = 0; i < userPaddingSize; i++) {
            userPadding.append("-");
        }
    
        String titleLine = "|" + titlePadding + this.title + titlePadding + "|";
        String userLineContent = userPadding + userInfo + userPadding;
        String extraDash = "";
        if (userLineContent.length() % 2 == 1) extraDash = "-";
        String userLine = "+" + userLineContent + extraDash + "+";
    
        System.out.println("+------------------------------------------------+");
        System.out.println(titleLine);
        System.out.println(userLine);
    }

    public void printFooter() {
        System.out.println();
        String footer = "End of " + this.title;
        int footerPaddingSize = (50 - footer.length()) / 2;
        StringBuilder footerPadding = new StringBuilder();
        for(int i = 0; i < footerPaddingSize; i++) {
            footerPadding.append("-");
        }
        System.out.println(footerPadding + footer + footerPadding);
    }

    public void render(Context ctx) {
        if (menu != null) {
        while (true) {
            printHeader(ctx);
            showContent(ctx);
            MenuCommand c = menu.render(ctx.getScanner());
            printFooter();
            if (c == null) 
                break;
            else
                c.execute(ctx);
        }
        } else {
            printHeader(ctx);
            showContent(ctx);
            printFooter();
            pauseForUser(ctx.getScanner());
        }
    }

    private void pauseForUser(Scanner scanner) {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
}
