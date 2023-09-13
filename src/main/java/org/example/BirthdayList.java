package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BirthdayList {
    static final List<BirthdayDate> birthdayDates = new ArrayList<>();
    private static final String FILE_NAME = "Birthday.csv";
    private static final String DATE_FORMAT = "M/d/yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static void main(String[] args) {
        loadBirthdayDate(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Ask user for the option, menu runs until user choose to exit
        while (running) {
            System.out.println("Welcome to Birthday Printer App");
            System.out.println("Choose an option:");
            System.out.println("A) Print previous week from Sunday to Saturday birthday");
            System.out.println("B) Print previous week from Sunday to Saturday birthday");
            System.out.println("X) Exit");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    printAllBirthdayLists();
                    break;
                case "B":
                    printPreviousWeekBirthdayLists();
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        scanner.close();
    }

    // loadBirthdayDate loads birthday data from a CSV file
    public static void loadBirthdayDate(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int number = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    LocalDate date = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern(DATE_FORMAT));
                    number++;
                    String name = parts[1];
                    int weekNumber = Integer.parseInt(parts[3]);
                    birthdayDates.add(new BirthdayDate(number, name, date, weekNumber));
                }
            }
        } catch (IOException | DateTimeParseException e) {
            System.out.println("Error loading birthday data: " + e.getMessage());
        }
    }
    private static void printAllBirthdayLists() {
        System.out.println("Birthday List:");
        for (BirthdayDate birthdayDate : birthdayDates) {
            System.out.println(birthdayDate);
        }
    }

    public static void printPreviousWeekBirthdayLists() {
        LocalDate currentDate = LocalDate.now();
        // Calculate the date for the most recent Sunday
        LocalDate previousSunday = currentDate.minusDays(currentDate.getDayOfWeek().getValue());

        // Calculate the date for the previous Saturday
        LocalDate previousSaturday = previousSunday.minusDays(1);

        // Calculate the date for the previous Sunday
        LocalDate startOfPreviousWeek = previousSunday.minusWeeks(1);

        System.out.println("Birthday List for the Previous Week from Saturday to Sunday:");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");

        for (BirthdayDate birthdayDate : birthdayDates) {
            LocalDate date = birthdayDate.getDate();
            if (!date.isBefore(startOfPreviousWeek) && !date.isAfter(previousSaturday)) {
                System.out.println("No: " + birthdayDate.getNumber() + ", Name: " + birthdayDate.getName() + ", Birthday: " + date.format(dateFormat) + ", WeekNumber: " + birthdayDate.getWeekNumber());
            }
        }
    }
}
