package service;

import org.junit.jupiter.api.Test;
import service.LibrarySystem;
import model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarySystemTest {
    @Test
    void addBook_shouldStoreBookInMap() {
        LibrarySystem system = new LibrarySystem();

        Book book = system.addBook("Clean Code", "Robert Martin");

        assertNotNull(book);
        assertEquals("Clean Code", book.getTitle());
        assertEquals(book, system.getBookDetails(book.getBookId()));
    }
    @Test
    void searchBooks_shouldFindBookByTitle() {
        LibrarySystem system = new LibrarySystem();
        system.seedBooks();
        List<Book> results = system.searchBooks("clean");
        assertEquals(1, results.size());
        results = system.searchBooks("refactor");
        assertEquals("Refactoring", results.getFirst().getTitle());
    }
    @Test
    void addMember_shouldStoreMemberInMap() {
        LibrarySystem system = new LibrarySystem();
        system.seedMembers();
        assertEquals("Ahmad Al-Zawaideh",system.Members.get(1).getName());
        assertEquals("yousefpass",system.Members.get(5).getPassword());
    }
}
