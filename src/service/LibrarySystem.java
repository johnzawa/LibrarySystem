package service;
import model.Book;
import model.Hall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibrarySystem {
    HashMap<Integer, Book> Books = new HashMap<>();
    HashMap<Integer, Hall> Halls = new HashMap<>();

    public List<Book> searchBooks(String title) {
        List<Book> results = new ArrayList<>();
            for (Book book:Books.values()) {
                if(book.getTitle().toLowerCase().contains(title.toLowerCase()))
                    results.add(book);
            }
        return results;
    }
}
