package org.exercise.event;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TryConcert {
    public static void main(String[] args) {

        List<Event> listaEventi = new ArrayList<>();

        Concert concerto1 = new Concert("concerto1", LocalDate.of(2025,12,25), 200, LocalTime.of(20,30), new BigDecimal(25));
        Concert concerto2 = new Concert("concerto2", LocalDate.of(2026,12,25), 300, LocalTime.of(22,15), new BigDecimal(10.50));
        listaEventi.add(concerto1);
        listaEventi.add(concerto2);

        for (Event c: listaEventi){
            System.out.println(c.toString());
        }
    }
}
