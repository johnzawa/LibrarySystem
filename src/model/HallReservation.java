package model;

import model.enums.ReservationStatus;

import java.sql.Time;
import java.util.Date;

public class HallReservation {
    private int reservationId;
    private Date date;
    private Time startTime;
    private Time endTime;
    private ReservationStatus status;

    public HallReservation (int reservationId, Date date, Time startTime, Time endTime) {
        this.reservationId = reservationId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
