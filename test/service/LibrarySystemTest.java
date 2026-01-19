package service;

import model.Member;
import org.junit.jupiter.api.Test;
import service.LibrarySystem;
import model.Book;

import java.time.LocalDate;
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
    @Test
    void authenticate_checkIfPasswordIsCorrect() {
        LibrarySystem system = new LibrarySystem();
        system.seedMembers();
        assertTrue(system.authenticatePassword(1,"pass123"));
        assertTrue(system.authenticatePassword(3,"library1"));
    }
    @Test
    void addHall_shouldStoreHallInMap() {
        LibrarySystem system = new LibrarySystem();
        system.seedHalls();
        assertEquals("Main Study Hall",system.Halls.get(1).getName());
    }
    @Test
    void isOverlapping_checkIfDatesAreOverlapping() {
        LibrarySystem system = new LibrarySystem();
        LocalDate startDate1 = LocalDate.parse("2026-01-15");
        LocalDate endDate1 = LocalDate.parse("2026-01-18");
        LocalDate startDate2 = LocalDate.parse("2026-01-19");
        LocalDate endDate2 = LocalDate.parse("2026-01-22");
        assertFalse(system.isOverlapping(startDate1,endDate1,startDate2,endDate2));
        startDate2 = LocalDate.parse("2026-01-16");
        assertTrue(system.isOverlapping(startDate1,endDate1,startDate2,endDate2));
    }
    @Test
    void addBookReservation_shouldStoreBookReservationOnlyIfPossible(){
        LibrarySystem system = new LibrarySystem();
        system.seedBooks();
        system.seedBookReservations();
        assertEquals(4,system.BookReservations.size());
        LocalDate startDate = LocalDate.parse("2026-02-02");
        LocalDate endDate = LocalDate.parse("2026-02-05");
        Book book = system.Books.get(3);
        Member member = system.Members.get(2);
        system.addBookReservation(book,member,5,startDate,endDate);
        assertEquals(5,system.BookReservations.getLast().getReservationId());
    }
}
