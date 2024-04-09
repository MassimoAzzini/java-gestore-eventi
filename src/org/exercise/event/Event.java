package org.exercise.event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private LocalDate date;
    private int eventCapacity;
    private int reservedSeats;

    public Event(String title, LocalDate date, int eventCapacity) throws IllegalArgumentException{
        if (title == null || title.isEmpty()){
            throw new IllegalArgumentException("Title null or empty");
        }
        if (date.isBefore(LocalDate.now()) || date == null){
            throw new IllegalArgumentException("The date is obligatory and must not have already passed");
        }
        if (eventCapacity == 0){
            throw new IllegalArgumentException("You must enter a number greater than 0");
        }
        this.title = title;
        this.date = date;
        this.eventCapacity = eventCapacity;

        reservedSeats = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (title == null || title.isEmpty()){
            throw new IllegalArgumentException("Title null or empty");
        }
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws IllegalArgumentException {
        if (date.isBefore(LocalDate.now()) || date == null){
            throw new IllegalArgumentException("The date is obligatory and must not have already passed");
        }
        this.date = date;
    }

    public int getEventCapacity() {
        return eventCapacity;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public int bookSeats(int seat){
        return reservedSeats += seat;
    }

    public int cancelReservation(int seat){
        return reservedSeats -= seat;
    }

    @Override
    public String toString() {
        return  date.format(DateTimeFormatter.ISO_LOCAL_DATE) + " - " + title + "\n";
    }
}
