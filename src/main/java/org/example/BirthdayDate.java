package org.example;

import java.time.LocalDate;

public class BirthdayDate {
    private int number;
    private String name;
    private LocalDate date;
    private int weekNumber;

    public BirthdayDate(int number, String name, LocalDate date, int weekNumber) {
        this.number = number;
        this.name = name;
        this.date = date;
        this.weekNumber = weekNumber;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    @Override
    public String toString() {
        return "No: " + number + ", Name: " + name + ", Birthday: " + date + ", WeekNumber: " + weekNumber;
    }
}
