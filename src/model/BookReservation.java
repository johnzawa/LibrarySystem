package model;

import model.enums.ReservationStatus;

import java.util.Date;

public class BookReservation {
    private int reservationId;
    private Date startDate;
    private Date endDate;
    private Book book;
    private Member member;
    private ReservationStatus status;

    public BookReservation(Book book, Member member, int reservationId, Date reservationDate, Date startDate, Date endDate, ReservationStatus status) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate= endDate;
        this.status = status;
        this.book = book;
        this.member = member;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }
}

