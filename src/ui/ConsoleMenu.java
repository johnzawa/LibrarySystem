package ui;
import model.Book;
import model.Member;
import service.LibrarySystem;

import java.time.LocalDate;
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
        System.out.println("4. Search Halls");
        System.out.println("5. Reserve a Hall");
        System.out.println("6. Exit");
        System.out.print("Pick an option: ");
        int choice = scan.nextInt();
        switch(choice) {
            case 1:
                searchBook();
                break;
            case 2:
                reserveBook();
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

    }
}
