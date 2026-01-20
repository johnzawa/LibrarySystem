package service;

import model.Hall;
import model.Member;
import model.enums.HallType;
import model.enums.ReservationStatus;
import org.junit.jupiter.api.Test;
import model.Book;

import java.time.LocalDate;
import java.time.LocalTime;
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
        system.addBookReservation(book,member,startDate,endDate);
        assertEquals(5,system.BookReservations.getLast().getReservationId());
    }
    @Test
    void cancelBookReservation_shouldCancelBookReservationIfExists(){
        LibrarySystem system = new LibrarySystem();
        system.seedBooks();
        system.seedBookReservations();
        system.cancelBookReservation(2);
        assertEquals(ReservationStatus.CANCELLED, system.BookReservations.get(1).getStatus());
    }
    @Test
    void isOverlappingTime_shouldReturnTrueIfTimeOverlaps(){
        LibrarySystem system = new LibrarySystem();
        LocalDate startDate = LocalDate.parse("2026-02-02");
        LocalTime startTime = LocalTime.of(12,30);
        LocalTime endTime = LocalTime.of(13,30);
        LocalTime startTime1 = LocalTime.of(13,30);
        LocalTime endTime2 = LocalTime.of(14,30);
        assertFalse(system.isOverlappingTime(startDate,startTime,endTime,startDate,startTime1,endTime2));
    }
    @Test
    void addHallReservation_shouldStoreHallReservationIfPossible(){
        LibrarySystem system = new LibrarySystem();
        system.seedMembers();
        system.seedHalls();
        system.seedHallReservations();
        assertEquals(9,system.HallReservations.size());
    }

    @Test
    void searchHalls_shouldShowApplicableHalls() {
        LibrarySystem system = new LibrarySystem();
        system.seedMembers();
        system.seedHalls();
        system.seedHallReservations();
        List<Hall> searchResults = system.searchHalls(LocalDate.parse("2026-01-20"), LocalTime.of(10,0), LocalTime.of(12,0), 40, HallType.STUDY );
        assertEquals(1, searchResults.size());
        searchResults = system.searchHalls(LocalDate.parse("2026-01-20"), LocalTime.of(11,0), LocalTime.of(13,0), 100, HallType.STUDY );
        assertEquals(1, searchResults.size());
        searchResults = system.searchHalls(LocalDate.parse("2026-01-20"), LocalTime.of(13,0), LocalTime.of(15,0), 100, HallType.STUDY );
        assertEquals(2, searchResults.size());
    }

    @Test
    void cancelHallReservation_shouldCancelHallReservationIfExists(){
        LibrarySystem system = new LibrarySystem();
        system.seedMembers();
        system.seedHalls();
        system.seedHallReservations();
        assertTrue(system.cancelHallReservation(2));
        system.cancelHallReservation(2);
        assertEquals(ReservationStatus.CANCELLED, system.HallReservations.get(1).getStatus());
    }
}
