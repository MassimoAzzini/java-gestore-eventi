package org.exercise.event;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concert extends Event{
    LocalTime time;
    BigDecimal price;

    public Concert(String title, LocalDate date, int eventCapacity, LocalTime time, BigDecimal price) throws IllegalArgumentException {
        super(title, date, eventCapacity);

        this.time = time;
        this.price = price;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getTimeFormat() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPriceFormat() {
        return price.setScale(2,4);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  getDateFormat() + " " + getTimeFormat() + " - " + getTitle() + " - " + getPriceFormat() + "\n";
    }
}
