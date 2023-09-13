package org.example;

import org.example.BirthdayDate;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BirthdayListTest {
    @Test
    public void print_previousWeekBirthdayLists_fromSundayToSaturday() {
        // Arrange
        List<BirthdayDate> birthdayDates = new ArrayList<>();
        birthdayDates.add(new BirthdayDate(1, "Emzbebh bhe (nu)", LocalDate.of(2023, 8, 1), 32));
        birthdayDates.add(new BirthdayDate(1, "yem uerue", LocalDate.of(2023, 9, 3), 35));
        birthdayDates.add(new BirthdayDate(2, "Ryae Ryn", LocalDate.of(2023, 9, 4), 35));
        birthdayDates.add(new BirthdayDate(3, "hbu ahy", LocalDate.of(2023, 9, 6), 36));
        birthdayDates.add(new BirthdayDate(4, "nh n", LocalDate.of(2023, 9, 7), 36));
        birthdayDates.add(new BirthdayDate(5, "ahern uerue", LocalDate.of(2023, 9, 8), 36));
        birthdayDates.add(new BirthdayDate(6, "abhmeen bhe", LocalDate.of(2023, 9, 9), 36));
        birthdayDates.add(new BirthdayDate(6, "nne By bhe", LocalDate.of(2023, 9, 13), 37));

        // Act
        LocalDate currentDate = LocalDate.of(2023, 9, 13);
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
            // Assert
            if (!date.isBefore(startOfPreviousWeek) && !date.isAfter(previousSaturday)) {
                System.out.println("No: " + birthdayDate.getNumber() + ", Name: " + birthdayDate.getName() + ", Birthday: " + date.format(dateFormat) + ", WeekNumber: " + birthdayDate.getWeekNumber());
            }
        }
    }
}
