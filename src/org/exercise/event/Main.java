package org.exercise.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Event> eventsList = new ArrayList<>();



        // CREAZIONE EVENTO UTENTE

        boolean end = false;
        while (!end){
            System.out.println("do you want to create a new event?(Y/N)");
            String choice = scan.nextLine().trim().toUpperCase();

            switch (choice){

                case "Y":

                    System.out.println("Event name: ");
                    String title = scan.nextLine();
                    while (title.isEmpty()) {
                        System.out.println("Event name must not be empty");
                        System.out.print("Event name: ");
                        title = scan.nextLine();
                    }

                    LocalDate date = null;
                    while(date == null || date.isBefore(LocalDate.now())) {

                        int year = 0;
                        while (year == 0) {
                            System.out.println("Year Event: ");
                            try {
                                year = Integer.parseInt(scan.nextLine());
                                if (year < LocalDate.now().getYear()) {
                                    System.out.println("Value not valid");
                                    year = 0;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Value not valid");
                            }
                        }

                        int mounth = 0;
                        while (mounth == 0) {
                            System.out.println("Mounth Event: ");
                            try {
                                mounth = Integer.parseInt(scan.nextLine());
                                if (mounth < 0 || mounth > 12) {
                                    System.out.println("Value not valid");
                                    mounth = 0;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Value not valid");
                            }
                        }

                        int day = 0;
                        while (day == 0) {
                            System.out.println("Day Event: ");
                            try {
                                day = Integer.parseInt(scan.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Value not valid");
                            }
                        }

                        try {
                            date = LocalDate.of(year, mounth, day);
                        } catch (Exception e) {
                            System.out.println("Date not valid");
                        }
                        if (date.isBefore(LocalDate.now())){
                            System.out.println("The date is obligatory and must not have already passed");
                        }
                    }

                    System.out.println("Event capacity: ");
                    int capacity = 0;
                    while(capacity == 0){
                        try {
                            capacity = Integer.parseInt(scan.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Value not valid");
                        }
                    }


                    try {
                        Event event = new Event(title, date, capacity);
                        System.out.println(event);
                        eventsList.add(event);


                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "N":
                    end = true;
                    break;

                default:
                    break;
            }
        }

        boolean endPrenotation = false;
        while (!endPrenotation){

            if (eventsList.size() == 0) endPrenotation = true;

            for(Event e: eventsList){
                System.out.println(e.toString());
            }

            System.out.println("Scrivi EXIT per finire");

            System.out.println("Con quale evento vuoi interagire?: ");
            String sceltaEvento = scan.nextLine().trim().toLowerCase();
            if (sceltaEvento.equals("exit")){
                endPrenotation = true;
                break;
            }

            Event eventToFind = new Event(sceltaEvento);

            if (eventsList.contains(eventToFind)){
                for (Event e: eventsList){
                    if(e.equals(eventToFind)) {
                        boolean done = false;
                        while (!done) {
                            System.out.println("Posti disponibili: " + (e.getEventCapacity()-e.getReservedSeats()));

                            System.out.println("***OPZIONI***");
                            System.out.println("1-partecipa all'evento");
                            System.out.println("2-disdici prenotazione");
                            System.out.println("3-esci");
                            System.out.println("***********************");
                            System.out.println("Cosa vuoi fare?(1-2-3): ");
                            int scelta = Integer.parseInt(scan.nextLine());

                            switch (scelta){
                                case 1:
                                    System.out.println("Quanti posti vuoi prenotare?");
                                    int numberPrenotation = Integer.parseInt(scan.nextLine());
                                    try {
                                        e.bookSeats(numberPrenotation);

                                    } catch (IllegalArgumentException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                    break;

                                case 2:
                                    if (e.getReservedSeats() == 0){
                                        System.out.println("Non hai prenotazioni in questo evento");
                                        break;
                                    } else {

                                        System.out.println("Posti prenotati: " + e.getReservedSeats());
                                        System.out.println("Quanti posti vuoi disdire?");
                                        int numberDisdetta = Integer.parseInt(scan.nextLine());
                                        try {
                                            e.cancelReservation(numberDisdetta);

                                        } catch (IllegalArgumentException ex) {
                                            System.out.println(ex.getMessage());
                                        }
                                        break;
                                    }

                                default:
                                    done = true;
                                    break;
                            }
                        }
                    }
                }

            } else {
                System.out.println("Non esiste questo evento");
            }

        }



        scan.close();
    }
}
