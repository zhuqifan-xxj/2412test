package ui.testers;
import java.util.Scanner;

import core.Context;
import core.Guest;
import ui.MenuCommand;
import ui.MenuUI;

public class MenuUITester {

    public static class TestMenuUI extends MenuUI {
        
        public TestMenuUI() {
            super();
        }

        @Override
        public MenuCommand[] getChoices() {
            return new MenuCommand[]{
                new MenuCommand() {
                    public void execute(Context ctx) {
                        System.out.println("You selected Option 1.");
                    }
                    public String getDescription() {
                        return "Option 1";
                    }
                },
                new MenuCommand() {
                    public void execute(Context ctx) {
                        System.out.println("You selected Option 2.");
                    }
                    public String getDescription() {
                        return "Option 2";
                    }
                }
            };
        }
    }

    public static void main(String[] args) {
        // Instantiate your custom MenuUI
        TestMenuUI testMenu = new TestMenuUI();

        // Call render to display the menu and get the user's choice
        MenuCommand userChoice = testMenu.render(new Scanner(System.in));

        // Execute the chosen command
        userChoice.execute(new Context(null, new Guest(), new Scanner(System.in)));
    }
}
