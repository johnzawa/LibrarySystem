package service;
import model.Book;
import model.Hall;
import model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibrarySystem {

    // Storage for data
    HashMap<Integer, Book> Books = new HashMap<>();
    HashMap<Integer, Hall> Halls = new HashMap<>();
    HashMap<Integer, Member> Members = new HashMap<>();

    int bookId = 1;

    public Book addBook(String title, String author) {
        Book book = new Book(bookId, title, author);
        Books.put(bookId++, book);
        return book;
    }
    public void seedBooks() {
        addBook("Clean Code", "Robert C. Martin");
        addBook("Effective Java", "Joshua Bloch");
        addBook("Design Patterns", "Erich Gamma et al.");
        addBook("Introduction to Algorithms", "Thomas H. Cormen");
        addBook("Refactoring", "Martin Fowler");
    }

    public List<Book> searchBooks(String title) {
        List<Book> results = new ArrayList<>();
            for (Book book:Books.values()) {
                if(book.getTitle().toLowerCase().contains(title.toLowerCase()))
                    results.add(book);
            }
        return results;
    }

    public Book getBookDetails(int bookId) {
        return Books.get(bookId);
    }


}
