package model;

import java.time.LocalDate;

public class BookSuggestion {
    private int suggestionId;
    private String title;
    private String author;
    private String publisher;
    private String reason;
    private LocalDate suggestionDate;

    public BookSuggestion(int suggestionId, String title, String author, String publisher, String reason, LocalDate suggestionDate) {
        this.suggestionId = suggestionId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.reason = reason;
        this.suggestionDate = suggestionDate;
    }

    @Override
    public String toString(){
        return "Title: " + title + "\nAuthor: " + author + "\nPublisher: " + publisher + "\nReason: " + reason + "\nDate: " + suggestionDate;
    }
    public int getSuggestionId() {
        return suggestionId;
    }

    public LocalDate getSuggestionDate() {
        return suggestionDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return  publisher;
    }

    public String getReason() {
        return reason;
    }
}
