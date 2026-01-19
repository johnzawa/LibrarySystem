package service;
import model.*;
import model.enums.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibrarySystem {

    // Storage for data
    HashMap<Integer, Book> Books = new HashMap<>();
    HashMap<Integer, Hall> Halls = new HashMap<>();
    HashMap<Integer, Member> Members = new HashMap<>();
    List<BookReservation> BookReservations = new ArrayList<>();
    List<HallReservation> HallReservations = new ArrayList<>();
    List<BookSuggestion> BookSuggestions = new ArrayList<>();
    int bookReservationId;
    int bookId = 1;
    int hallReservationId;
    int hallId = 1;
    int memberId = 1;
    int bookSuggestionId = 1;

    public boolean isOverlapping(LocalDate startA, LocalDate endA, LocalDate startB, LocalDate endB) {
        return !endA.isBefore(startB) && !endB.isBefore(startA);
    }

    public boolean isOverlappingTime(LocalDate dateA, LocalTime startA, LocalTime endA, LocalDate dateB, LocalTime startB, LocalTime endB) {
        if (!dateA.isEqual(dateB)) {
            return false;
        }
        return startA.isBefore(endB) && startB.isBefore(endA);
    }


    public Member addMember(String name, String email, String password) {
        Member member = new Member(memberId, name, email, password);
        Members.put(memberId++, member);
        return member;
    }

    public void seedMembers() {
        addMember("Ahmad Al-Zawaideh", "ahmad.z@uni.edu", "pass123");
        addMember("Sara Haddad", "sara.h@uni.edu", "password");
        addMember("Omar Khalil", "omar.k@uni.edu", "library1");
        addMember("Lina Nasser", "lina.n@uni.edu", "lina123");
        addMember("Yousef Mansour", "yousef.m@uni.edu", "yousefpass");
    }

    public boolean authenticatePassword(int memberId, String password) {
        if (Members.get(memberId).getPassword().equals(password))
            return true;
        else return false;
    }

    public Hall addHall(String name, int capacity) {
        Hall hall = new Hall(hallId, name, capacity);
        Halls.put(hallId++, hall);
        return hall;
    }

    public Hall getHallDetails(int hallId) {
        return Halls.get(hallId);
    }

    public void seedHalls() {
        addHall("Main Study Hall", 120);
        addHall("Conference Hall A", 60);
        addHall("Computer Lab 1", 40);
        addHall("Auditorium", 300);
        addHall("Quiet Reading Room", 25);
    }

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
        for (Book book : Books.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase()))
                results.add(book);
        }
        return results;
    }

    public Book getBookDetails(int bookId) {
        return Books.get(bookId);
    }

    public BookReservation addBookReservation(Book book, Member member, int reservationId, LocalDate startDate, LocalDate endDate) {
        //filtering reservations that are for the same book
        List<BookReservation> filteredList = new ArrayList<>();
        for (BookReservation reservation : BookReservations) {
            if (book.getBookId() == reservation.getBook().getBookId())
                filteredList.add(reservation);
        }
        for (BookReservation reservation : filteredList) {
            if (isOverlapping(startDate, endDate, reservation.getStartDate(), reservation.getEndDate()))
                throw new RuntimeException("overlapping date");
        }
        BookReservations.add(new BookReservation(book, member, reservationId, startDate, endDate));
        return BookReservations.getLast();
    }

    public void seedBookReservations() {

        addBookReservation(
                Books.get(1),
                Members.get(2),
                bookReservationId++,
                LocalDate.of(2026, 1, 10),
                LocalDate.of(2026, 1, 20)
        );

        addBookReservation(
                Books.get(3),
                Members.get(1),
                bookReservationId++,
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2025, 12, 15)
        );

        addBookReservation(
                Books.get(4),
                Members.get(4),
                bookReservationId++,
                LocalDate.of(2026, 2, 1),
                LocalDate.of(2026, 2, 10)
        );

        addBookReservation(
                Books.get(5),
                Members.get(3),
                bookReservationId++,
                LocalDate.of(2025, 11, 5),
                LocalDate.of(2025, 11, 18)
        );
    }

    public boolean cancelBookReservation(int bookReservationId) {
        for (BookReservation bookReservation : BookReservations) {
            if (bookReservationId == bookReservation.getReservationId()) {
                bookReservation.setStatus(ReservationStatus.CANCELLED);
                return true;
            }
        }
        return false;
    }

    public BookSuggestion addBookSuggestion(String title, String author, String publisher, String reason) {
        LocalDate date = LocalDate.now();
        BookSuggestions.add(new BookSuggestion(bookSuggestionId++,title,author,publisher,reason,date));
        return BookSuggestions.getLast();
    }

    public void seedBookSuggestions() {

        addBookSuggestion(
                "Clean Architecture",
                "Robert C. Martin",
                "Pearson",
                "Recommended for understanding layered system design"
        );

        addBookSuggestion(
                "The Pragmatic Programmer",
                "Andrew Hunt",
                "Addison-Wesley",
                "Frequently referenced in software engineering courses"
        );

        addBookSuggestion(
                "Head First Design Patterns",
                "Eric Freeman",
                "O'Reilly Media",
                "Suggested as an easier introduction to design patterns"
        );

        addBookSuggestion(
                "Operating System Concepts",
                "Abraham Silberschatz",
                "Wiley",
                "Useful for OS and systems programming studies"
        );

        addBookSuggestion(
                "Database System Concepts",
                "Abraham Silberschatz",
                "McGraw-Hill",
                "Requested for database course support"
        );
    }

    public HallReservation addHallReservation(Hall hall, Member member, int reservationId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        //filtering reservations that are for the same book
        List<HallReservation> filteredList = new ArrayList<>();
        for (HallReservation reservation : HallReservations) {
            if (hall.getHallId() == reservation.getHall().getHallId())
                filteredList.add(reservation);
        }
        for (HallReservation reservation : filteredList) {
            if (isOverlappingTime(date, startTime, endTime, reservation.getDate(), reservation.getStartTime(), reservation.getEndTime()))
                throw new RuntimeException("overlapping time");
        }
        HallReservations.add(new HallReservation(reservationId, member, hall, date, startTime, endTime));
        return HallReservations.getLast();
    }

    public void seedHallReservations() {

        addHallReservation(
                Halls.get(1),      // Main Study Hall
                Members.get(1),    // Ahmad
                hallReservationId++,
                LocalDate.of(2026, 1, 20),
                LocalTime.of(9, 0),
                LocalTime.of(11, 0)
        );

        addHallReservation(
                Halls.get(1),      // Same hall, later time (no overlap)
                Members.get(2),    // Sara
                hallReservationId++,
                LocalDate.of(2026, 1, 20),
                LocalTime.of(11, 0),
                LocalTime.of(12, 30)
        );

        addHallReservation(
                Halls.get(2),      // Different hall, same date/time (allowed)
                Members.get(3),    // Omar
                hallReservationId++,
                LocalDate.of(2026, 1, 20),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0)
        );

        addHallReservation(
                Halls.get(3),      // Computer Lab 1
                Members.get(4),    // Lina
                hallReservationId++,
                LocalDate.of(2026, 1, 21),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0)
        );
    }



}
