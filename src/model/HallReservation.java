package model;

import model.enums.ReservationStatus;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class HallReservation {
    private int reservationId;
    private Hall hall;
    private Member member;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private ReservationStatus status;

    public HallReservation (int reservationId,Member member, Hall hall, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.reservationId = reservationId;
        this.hall = hall;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        status = ReservationStatus.CONFIRMED;
    }

    public Hall getHall() {
        return hall;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getReservationId() {
        return reservationId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
