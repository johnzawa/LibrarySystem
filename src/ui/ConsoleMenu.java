package ui;
import model.Book;
import model.Hall;
import model.Member;
import model.enums.HallType;
import service.LibrarySystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
public class ConsoleMenu {
    LibrarySystem system = new LibrarySystem();
    Member member;
    public void Start() {
        system.seedMembers();
        system.seedBooks();
        system.seedHalls();
        system.seedBookReservations();
        system.seedHallReservations();
        Login();
        while(true) {
            pickOption();
        }
    }

    public void Login() {
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.print("Enter your Member ID: ");
            int memberId = scan.nextInt();
            System.out.print("Enter your password: ");
            String password = scan.next();
            if (system.authenticatePassword(memberId, password)){
                System.out.println("Login Successful!");
                member = system.Members.get(memberId);
                break;
            }
            System.out.println("Wrong Cridentials, Try Again!");
        }
    }

    public void pickOption() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Search Books");
        System.out.println("2. Reserve a Book");
        System.out.println("3. Suggest a Book");
        System.out.println("4. Search for Available Halls");
        System.out.println("5. Reserve a Hall");
        System.out.println("6. Exit");
        int choice;
        while (true) {
            System.out.print("Pick an option: ");
            choice = scan.nextInt();
            if (choice<1 || choice>6){
                System.out.println("Invalid Choice");
                continue;
            }
            break;
        }
        switch (choice) {
            case 1:
                searchBook();
                break;
            case 2:
                reserveBook();
                System.out.println("Reservation Successful!\n");
                break;
            case 3:
                suggestBook();
                break;
            case 4:
                searchHalls();
                break;
            case 5:
                reserveHall();
                System.out.println("Reservation Successful!\n");
                break;
            case 6:
                System.exit(0);
        }
    }
    public void searchBook() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Book Title: ");
        String title = scan.nextLine();
        List<Book> results= system.searchBooks(title);
        for(Book book: results)
            System.out.print(book.toString());
    }

    public void reserveBook() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        int bookId = scan.nextInt();
        LocalDate startDate;
        LocalDate endDate;
        while(true) {
            System.out.print("Enter Start Date 'YYYY-MM-DD': ");
            String startD = scan.next();
            try {
                startDate = LocalDate.parse(startD);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date Format");
                continue;
            }
            break;
        }
        while(true) {
            System.out.print("Enter End Date 'YYYY-MM-DD': ");
            String endD = scan.next();
            try {
                endDate = LocalDate.parse(endD);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date Format");
                continue;
            }
            break;
        }
        try {
            system.addBookReservation(system.getBookDetails(bookId), member, startDate, endDate);
        }
        catch(RuntimeException e) {
            System.out.println("Reservation period overlaps with another reservation :(");
            reserveBook();
        }
    }

    public void suggestBook() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Book Title: ");
        String title = scan.next();
        System.out.print("Enter Book Author: ");
        String author = scan.next();
        System.out.print("Enter Book Publisher: ");
        String publisher = scan.next();
        System.out.print("Enter Reason For Suggestion: ");
        String reason = scan.next();
        system.addBookSuggestion(title, author, publisher, reason);
    }

    public void searchHalls() {
        Scanner scan = new Scanner(System.in);
        LocalDate date;
        while(true) {
            System.out.print("Enter Date 'YYYY-MM-DD': ");
            try {
                date = LocalDate.parse(scan.next());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date Format");
                continue;
            }
            break;
        }
        int startT;
        while(true) {
            System.out.print("Enter Start Time 'hh': ");
            startT = scan.nextInt();
            if (startT < 0 || startT > 23) {
                System.out.println("Invalid Time");
                continue;
            }
            break;
        }
        LocalTime startTime = LocalTime.of(startT,0);
        int endT;
        while(true) {
            System.out.print("Enter End Time 'hh': ");
            endT = scan.nextInt();
            if (endT < 0 || endT > 23) {
                System.out.println("Invalid Time");
                continue;
            }
            break;
        }
        LocalTime endTime = LocalTime.of(endT,0);
        System.out.print("Enter Expected Attendees Amount: ");
        int expectedAttendees = scan.nextInt();
        System.out.println("HALL TYPES: ");
        System.out.println("1. Study Hall");
        System.out.println("2. Conference Hall");
        System.out.println("3. Computer Lab");
        System.out.println("4. Auditorium");
        System.out.println("5. Quiet Reading Room");
        int option;
        while(true) {
            System.out.print("Choose an option: ");
            option = scan.nextInt();
            if (option<1 | option>5){
                System.out.println("Invalid Choice");
                continue;
            }
            break;
        }
        HallType type;
        switch (option) {
            case 1:
                type = HallType.STUDY;
                break;
            case 2:
                type = HallType.CONFERENCE;
                break;
            case 3:
                type = HallType.COMPUTER;
                break;
            case 4:
                type = HallType.AUDITORIUM;
                break;
            case 5:
                type = HallType.READING;
                break;
            default:
                type = null;
        }
        System.out.println("\nAvailable Halls: ");
        List<Hall> results = system.searchHalls(date,startTime,endTime,expectedAttendees,type);
        if(results.isEmpty())
            System.out.println("No Halls Available");
        else
            for(Hall hall : results)
                System.out.print(hall.toString());
    }

    public void reserveHall() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Hall ID: ");
        int hallId = scan.nextInt();
        if (hallId < 1 || hallId > 13) {
            System.out.println("Invalid Hall ID\nUse Search Halls to find suitable hall");
            return;
        }
        LocalDate date;
        while (true) {
            System.out.print("Enter Date 'YYYY-MM-DD': ");
            try {
                date = LocalDate.parse(scan.next());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date Format");
                continue;
            }
            break;
        }
        int startT;
        while (true) {
            System.out.print("Enter Start Time 'hh': ");
            startT = scan.nextInt();
            if (startT < 0 || startT > 23) {
                System.out.println("Invalid Time");
                continue;
            }
            break;
        }
        LocalTime startTime = LocalTime.of(startT, 0);
        int endT;
        while (true) {
            System.out.print("Enter End Time 'hh': ");
            endT = scan.nextInt();
            if (endT < 0 || endT > 23) {
                System.out.println("Invalid Time");
                continue;
            }
            break;
        }
        LocalTime endTime = LocalTime.of(endT, 0);
        try {
            system.addHallReservation(system.getHallDetails(hallId), member, date, startTime, endTime);
        } catch (RuntimeException e) {
            System.out.println("Time overlaps with another reservation, use Search Halls to check available halls");
            reserveHall();
        }
    }
}
