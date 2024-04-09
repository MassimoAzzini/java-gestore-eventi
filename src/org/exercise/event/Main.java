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

                case "N":
                    end = true;

                default:
                    break;
            }

        }

        System.out.println(eventsList.toString());






        scan.close();
    }
}
