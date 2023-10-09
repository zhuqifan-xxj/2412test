import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

import core.Context;
import ui.MenuCommand;
import ui.MenuUI;

public class TerminalUITest {

  class SampleMenu extends MenuUI {
    
    public SampleMenu(PrintStream out) {
        super(out);
    }

    @Override
    public MenuCommand[] getChoices() {
      return new MenuCommand[] {
        new MenuCommand() {
          @Override
          public void execute(Context ctx) {
            System.out.println("lol");
          }

          @Override
          public String getDescription() {
            return "Choice 1";
          }
        },
        new MenuCommand() {
          @Override
          public void execute(Context ctx) {
            System.out.println("lol");
          }

          @Override
          public String getDescription() {
            return "Choice 2";
          }
        },
      };
    }
  }

  @Test
  public void testValidInput() {
    String input = "1\n";
    ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inStream);

    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outStream);

    MenuUI ui = new SampleMenu(printStream);
    MenuCommand selected = ui.render(scanner);

    assertTrue(selected.getDescription().equals("Choice 1"));

    assertEquals(
      "1. Choice 1\n2. Choice 2\nEnter your choice (1-2): ",
      outStream.toString()
    );
  }

  @Test
  public void testInvalidInput() {
    String input = "abc\n1\n";
    ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inStream);

    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outStream);

    MenuUI ui = new SampleMenu(printStream);
    MenuCommand selected = ui.render(scanner);

    assertTrue(selected.getDescription().equals("Choice 1"));
    assertEquals(
      "1. Choice 1\n2. Choice 2\nEnter your choice (1-2): Invalid input. Try again.\nEnter your choice (1-2): ",
      outStream.toString()
    );
  }

  @Test
  public void testOutOfRangeInput() {
    String input = "3\n1\n";
    ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
    Scanner scanner = new Scanner(inStream);

    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outStream);

    MenuUI ui = new SampleMenu(printStream);
    MenuCommand selected = ui.render(scanner);

    assertTrue(selected.getDescription().equals("Choice 1"));
    assertEquals(
      "1. Choice 1\n2. Choice 2\nEnter your choice (1-2): Choice out of range. Try again.\nEnter your choice (1-2): ",
      outStream.toString()
    );
  }
  // Additional test cases can be added as needed.
}
