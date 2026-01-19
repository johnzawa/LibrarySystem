package model;
import model.enums.BookStatus;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private BookStatus status;

    public Book (int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        status = BookStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nBook Status: " + status + "\n\n";
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return status.equals(BookStatus.AVAILABLE);
    }
}