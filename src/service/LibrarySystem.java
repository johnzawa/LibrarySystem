package service;
import model.*;
import model.enums.HallType;
import model.enums.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LibrarySystem {

    // Storage for data
    HashMap<Integer, Book> Books = new HashMap<>();
    HashMap<Integer, Hall> Halls = new HashMap<>();
    HashMap<Integer, Member> Members = new HashMap<>();
    List<BookReservation> BookReservations = new ArrayList<>();
    List<HallReservation> HallReservations = new ArrayList<>();
    List<BookSuggestion> BookSuggestions = new ArrayList<>();
    int bookReservationId = 1;
    int bookId = 1;
    int hallReservationId = 1;
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

    public Hall addHall(String name, int capacity, HallType type) {
        Hall hall = new Hall(hallId, name, capacity, type);
        Halls.put(hallId++, hall);
        return hall;
    }

    public Hall getHallDetails(int hallId) {
        return Halls.get(hallId);
    }

    public void seedHalls() {
        addHall("Main Study Hall", 200, HallType.STUDY);
        addHall("Study Hall 2", 120, HallType.STUDY);
        addHall("Study Hall 3", 50, HallType.STUDY);
        addHall("Conference Hall A", 60, HallType.CONFERENCE);
        addHall("Conference Hall B", 30, HallType.CONFERENCE);
        addHall("Conference Hall C", 100, HallType.CONFERENCE);
        addHall("Computer Lab 1", 100, HallType.COMPUTER);
        addHall("Computer Lab 2", 70, HallType.COMPUTER);
        addHall("Computer Lab 3", 50, HallType.COMPUTER);
        addHall("Auditorium A", 300, HallType.AUDITORIUM);
        addHall("Auditorium B", 200, HallType.AUDITORIUM);
        addHall("Auditorium C", 500, HallType.AUDITORIUM);
        addHall("Quiet Reading Room", 50, HallType.READING);
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

    public boolean cancelHallReservation(int hallReservationId) {
        for (HallReservation hallReservation : HallReservations) {
            if (hallReservationId == hallReservation.getReservationId()) {
                hallReservation.setStatus(ReservationStatus.CANCELLED);
                return true;
            }
        }
        return false;
    }
    public List<Hall> searchHalls(LocalDate date, LocalTime startTime, LocalTime endTime, int expectedAttendees, HallType type) {
        List<Hall> filteredList = new ArrayList<>();
        int hallId;
        for(Hall hall : Halls.values())
            if(hall.getType()==type)
                if(expectedAttendees<=hall.getCapacity())
                    filteredList.add(hall);

        for(HallReservation hallReservation : HallReservations)
            if(isOverlappingTime(hallReservation.getDate(), hallReservation.getStartTime(), hallReservation.getEndTime(), date, startTime, endTime)) {
                hallId = hallReservation.getHall().getHallId();
                Iterator<Hall> iterator = filteredList.iterator();
                while(iterator.hasNext()) {
                    Hall hall = iterator.next();
                    if(hall.getHallId()==hallId)
                        iterator.remove();
                }
            }
        return filteredList;
    }

    public void seedHallReservations() {

        addHallReservation(
                Halls.get(1),
                Members.get(1),
                hallReservationId++,
                LocalDate.of(2026, 1, 20),
                LocalTime.of(9, 0),
                LocalTime.of(11, 0)
        );

        addHallReservation(
                Halls.get(2),
                Members.get(2),
                hallReservationId++,
                LocalDate.of(2026, 1, 20),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0)
        );

        addHallReservation(
                Halls.get(4),
                Members.get(3),
                hallReservationId++,
                LocalDate.of(2026, 1, 21),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0)
        );

        addHallReservation(
                Halls.get(6),
                Members.get(4),
                hallReservationId++,
                LocalDate.of(2026, 1, 21),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0)
        );

        addHallReservation(
                Halls.get(7),
                Members.get(5),
                hallReservationId++,
                LocalDate.of(2026, 1, 22),
                LocalTime.of(9, 0),
                LocalTime.of(11, 0)
        );

        addHallReservation(
                Halls.get(8),
                Members.get(1),
                hallReservationId++,
                LocalDate.of(2026, 1, 22),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0)
        );

        addHallReservation(
                Halls.get(10),
                Members.get(2),
                hallReservationId++,
                LocalDate.of(2026, 1, 23),
                LocalTime.of(10, 0),
                LocalTime.of(13, 0)
        );

        addHallReservation(
                Halls.get(12),
                Members.get(3),
                hallReservationId++,
                LocalDate.of(2026, 1, 24),
                LocalTime.of(14, 0),
                LocalTime.of(17, 0)
        );

        addHallReservation(
                Halls.get(13),
                Members.get(4),
                hallReservationId++,
                LocalDate.of(2026, 1, 25),
                LocalTime.of(9, 0),
                LocalTime.of(11, 0)
        );
    }


}
