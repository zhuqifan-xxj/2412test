package pages;

import core.Context;
import menus.StartMenu;
import ui.Page;

public class StartPage extends Page {

    public StartPage() {
        super("Start Page", new StartMenu());
    }

    @Override
    protected void showContent(Context ctx) {
        out.println("Welcome to the Library of Agility.");
    }
}
