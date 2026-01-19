package model;

import model.enums.ReservationStatus;

import java.time.LocalDate;

public class BookReservation {
    private int reservationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Book book;
    private Member member;
    private ReservationStatus status;
    public BookReservation(Book book, Member member, int reservationId, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate= endDate;
        status = ReservationStatus.CONFIRMED;
        this.book = book;
        this.member = member;
    }

    public int getReservationId() {
        return reservationId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }
}

