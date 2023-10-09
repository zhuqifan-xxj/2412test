package pages;

import java.util.Scanner;

import core.Context;
import core.User;
import infos.ProfileInfo;
import ui.Page;

public class ProfilePage extends Page {

    public ProfilePage() {
        super("Your Profile");
    }

    @Override
    protected void showContent(Context ctx) {
        ProfileInfo info = new ProfileInfo();
        info.render(ctx.getCurrentUser());
    }

    public static void main(String[] args) {
        new ProfilePage().render(new Context(null, 
        new User("test", "zhang.san@gmail.com", "San Zhang", "0123456789", "123", ""),
        new Scanner(System.in)));
    }
}