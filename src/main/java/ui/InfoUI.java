package ui;
import java.io.PrintStream;
import java.util.List;

public abstract class InfoUI<T> extends TerminalUI {
     /**
     * 
     * @return An array of Column objects that define the fields to display in the info page.
     */
    public abstract List<Column<T>> getColumns();
    
    /**
     * Initializes a new instance of InfoUI for testing purposes.
     * @param out The customized print stream for testing
     */
    public InfoUI(PrintStream out) {
        super(out);
    }

    /**
     * Initializes a new instance of InfoUI.
     */
    public InfoUI() {
        super();
    }


    /**
     * Display a DTO in the info page instantiated in the DTO's type.
     * @param data The DTO to be displayed
     */
    public void render(T data) {
        for (Column<T> col : getColumns()) {
            out.println(col.getColumnName() + ":\t" + col.getValue(data));
        }
    }
}
