package org.exercise.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Event {
    private String title;
    private LocalDate date;
    private int eventCapacity;
    private int reservedSeats;

    public Event(String title, LocalDate date, int eventCapacity) throws IllegalArgumentException{
        if (title == null || title.isEmpty()){
            throw new IllegalArgumentException("Title null or empty");
        }
        if (eventCapacity <= 0){
            throw new IllegalArgumentException("You must enter a number greater than 0");
        }

        this.title = title;
        this.eventCapacity = eventCapacity;
        this.date = date;

        if (date.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("The date is obligatory and must not have already passed");
        }

        reservedSeats = 0;
    }

    public Event(String title) {
        this.title = title;
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

    public String getDateFormat() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

    public int bookSeats(int seat) throws IllegalArgumentException{
        if((reservedSeats + seat)<getEventCapacity()){
            return reservedSeats += seat;
        } else {
            throw new IllegalArgumentException("non ci sono abbastanza posti disponibili");
        }
    }

    public int cancelReservation(int seat){
        return reservedSeats -= seat;
    }

    @Override
    public String toString() {
        return  getDateFormat() + " - " + title + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(getTitle(), event.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTitle());
    }


}
