package ui.testers;

import java.util.Arrays;
import java.util.List;

import ui.Column;
import ui.InfoUI;

class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
}

public class InfoUITester {
    public static void main(String[] args) {
        // Create a sample Book instance
        Book book = new Book("1984", "George Orwell", 1949);

        // Create a sample InfoUI instance
        InfoUI<Book> bookInfo = new InfoUI<Book>(System.out) {
            @Override
            public List<Column<Book>> getColumns() {
                return Arrays.asList(
                    new Column<Book>() {
                        public String getColumnName() { return "Title"; }
                        public String getValue(Book data) { return data.getTitle(); }
                    },
                    new Column<Book>() {
                        public String getColumnName() { return "Author"; }
                        public String getValue(Book data) { return data.getAuthor(); }
                    },
                    new Column<Book>() {
                        public String getColumnName() { return "Year Published"; }
                        public String getValue(Book data) { return Integer.toString(data.getYear()); }
                    }
                );
            }
        };

        // Render the information
        bookInfo.render(book);
    }
}
