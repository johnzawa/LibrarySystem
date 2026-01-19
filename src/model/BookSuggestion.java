package model;

import java.util.Date;

public class BookSuggestion {
    private int suggestionId;
    private String title;
    private String author;
    private String publisher;
    private String reason;
    private Date suggestionDate;

    public BookSuggestion(int suggestionId, String title, String author, String publisher, String reason, Date suggestionDate) {
        this.suggestionId = suggestionId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.reason = reason;
        this.suggestionDate = suggestionDate;
    }

    public int getSuggestionId() {
        return suggestionId;
    }

    public Date getSuggestionDate() {
        return suggestionDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getReason() {
        return reason;
    }
}
