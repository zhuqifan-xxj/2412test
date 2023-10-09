package ui;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * Abstract class TableUI serves as a template for rendering tables of DTOs.
 * @param <T> The type of DTO that the table will display.
 */
public abstract class TableUI<T> extends TerminalUI {
    private List<T> data;

    public List<T> getData() {
        return data;
    }


    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * 
     * @return An array of Column objects that define the columns of the table.
     */
    public abstract List<Column<T>> getColumns();
    

    /**
     * Initializes a new instance of TableUI for testing purposes.
     * @param out the custom print stream for testing
     */
    public TableUI(List<T> data, PrintStream out) {
        super(out);
        this.data = data;
    }

    /**
     * Initializes a new instance of TableUI.
     */
    public TableUI(List<T> data) {
        super();
        this.data = data;
    }

    /**
     * Renders the table to the console.
     * Headers, separators and data rows are printed to stdout.
     */
    public void render() {
        List<Column<T>> columns = getColumns();
        // Print header
        out.printf("#\t");
        for (Column<T> col : columns) {
            out.printf(col.getColumnName() + "\t");
        }
        out.println();
        out.print("---");
        // Print separator
        for (int i = 0; i < columns.size(); i++) {
            out.print("----------");
        }
        out.println();
        int counter = 1;
        // Print data rows
        for (T row : data) {
            out.printf("%d\t", counter);
            for (Column<T> col : columns) {
                out.printf(col.getValue(row) + "\t");
            }
            out.println();
            counter += 1;
        }
    }

    /**
     * Ask the user to select an item from the table.
     * Note that this method does not display the table itself, but ask for a choice.
     */
    public T selectItem(Scanner sc) {
        if (data == null || data.isEmpty()) {
            out.println("No data available to select from.");
            return null;
        }
        
        int choice = -1;
        while (true) {
            out.print("Please enter the number of the item you want to select: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= data.size()) {
                    break;
                } else {
                    out.println("The number you entered is out of range. Try again.");
                }
            } catch (NumberFormatException e) {
                out.println("Invalid input. Please enter a number.");
            }
        }
        return data.get(choice - 1);
    }
    
}
