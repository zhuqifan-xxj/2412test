package ui;
import java.io.PrintStream;

public abstract class TerminalUI {
    /** Output stream for testing purposes */
    protected PrintStream out;

    public TerminalUI() {
        this.out = System.out;
    }

    /**
     * Initializes a new instance of TerminalUI with customized Scanner and print stream (used in Junit).
     * @param sc Scanner
     * @param out the custom print stream for testing
     */
    public TerminalUI(PrintStream out) {
        this.out = out;
    }
}
