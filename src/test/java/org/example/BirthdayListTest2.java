package org.example;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BirthdayListTest2 {
    @Test
    public void testPrintPreviousWeekBirthdayListsFromSundayToSaturday() {
        // Arrange
        List<BirthdayDate> birthdayDates = new ArrayList<>();
        birthdayDates.add(new BirthdayDate(1, "Emzbebh bhe (nu)", LocalDate.of(2023, 8, 1), 32));
        birthdayDates.add(new BirthdayDate(2, "yem uerue", LocalDate.of(2023, 9, 3), 35));
        birthdayDates.add(new BirthdayDate(3, "Ryae Ryn", LocalDate.of(2023, 9, 4), 36));
        birthdayDates.add(new BirthdayDate(4, "hbu ahy", LocalDate.of(2023, 9, 6), 36));
        birthdayDates.add(new BirthdayDate(5, "nh n", LocalDate.of(2023, 9, 7), 36));
        birthdayDates.add(new BirthdayDate(6, "ahern uerue", LocalDate.of(2023, 9, 8), 36));
        birthdayDates.add(new BirthdayDate(7, "abhmeen bhe", LocalDate.of(2023, 9, 9), 36));
        birthdayDates.add(new BirthdayDate(8, "nne By bhe", LocalDate.of(2023, 9, 13), 37));

        // Act
        LocalDate currentDate = LocalDate.of(2023, 9, 13);
        // Calculate the date for the most recent Sunday
        LocalDate previousSunday = currentDate.minusDays(currentDate.getDayOfWeek().getValue());

        // Calculate the date for the previous Saturday
        LocalDate previousSaturday = previousSunday.minusDays(1);

        // Calculate the date for the previous Sunday
        LocalDate startOfPreviousWeek = previousSunday.minusWeeks(1);

        // Create a StringBuilder to capture the printed output
        StringBuilder actualOutput = new StringBuilder();

        for (BirthdayDate birthdayDate : birthdayDates) {
            LocalDate date = birthdayDate.getDate();
            if (!date.isBefore(startOfPreviousWeek) && !date.isAfter(previousSaturday)) {
                actualOutput.append("No: ").append(birthdayDate.getNumber())
                        .append(", Name: ").append(birthdayDate.getName())
                        .append(", Birthday: ").append(date.format(DateTimeFormatter.ofPattern("M/d/yyyy")))
                        .append(", WeekNumber: ").append(birthdayDate.getWeekNumber()).append("\n");
            }
        }

        // Assert
        String expectedOutput = "No: 2, Name: yem uerue, Birthday: 9/3/2023, WeekNumber: 35\n" +
                "No: 3, Name: Ryae Ryn, Birthday: 9/4/2023, WeekNumber: 36\n" +
                "No: 4, Name: hbu ahy, Birthday: 9/6/2023, WeekNumber: 36\n" +
                "No: 5, Name: nh n, Birthday: 9/7/2023, WeekNumber: 36\n" +
                "No: 6, Name: ahern uerue, Birthday: 9/8/2023, WeekNumber: 36\n" +
                "No: 7, Name: abhmeen bhe, Birthday: 9/9/2023, WeekNumber: 36\n";

        assertEquals(expectedOutput, actualOutput.toString());
    }
}
