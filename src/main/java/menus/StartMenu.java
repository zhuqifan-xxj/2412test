package menus;

import pages.ProfilePage;
import ui.MenuCommand;
import ui.MenuUI;
import core.Context;

public class StartMenu extends MenuUI {

    public StartMenu() {
        super();
    }

    @Override
    public MenuCommand[] getChoices() {
        return new MenuCommand[] {
            new MenuCommand() {
                @Override
                public void execute(Context ctx) {
                    // TODO: continue as a guest user
                    System.out.println("hello world!!!!!!");
                }

                @Override
                public String getDescription() {
                    return "Continue as a guest";
                }

            },
            new MenuCommand() {
                @Override
                public void execute(Context ctx) {
                    // TODO: enter login page
                }

                @Override
                public String getDescription() {
                    return "Login";
                }

            }
        };
    }
    
}
