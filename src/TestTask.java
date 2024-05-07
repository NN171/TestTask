import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class TestTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите дату в формате ДД.ММ.ГГГГ: ");

        try {
            String[] inputDate = sc.nextLine().split("\\.");

            int day = Integer.parseInt(inputDate[0]);
            int month = Integer.parseInt(inputDate[1]);
            int year = Integer.parseInt(inputDate[2]);
            LocalDate currDate = LocalDate.of(year, month, day);

            System.out.print("Введите время в формате ЧЧ.ММ: ");

            String[] inputTime = sc.nextLine().split("\\.");

            int hours = Integer.parseInt(inputTime[0]);
            int minutes = Integer.parseInt(inputTime[1]);

            System.out.println(isWorkingDay(isWeekend(currDate), hours, minutes, currDate));
        } catch (DateTimeException | IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean isWeekend(LocalDate currDate) {
        int day = currDate.getDayOfMonth();
        int month = currDate.getMonthValue();
        int year = currDate.getYear();
        DayOfWeek dayOfWeek = currDate.getDayOfWeek();

        if (month != 5 || year != 2024) {
            throw new IllegalArgumentException("Invalid date");
        }

        return switch (day) {
            case 1, 9, 10 -> true;
            default -> dayOfWeek == SATURDAY || dayOfWeek == SUNDAY;
        };
    }

    static boolean isWorkingDay(boolean isWeekend, int hours, int minutes, LocalDate currDate) {
        int day = currDate.getDayOfMonth();

        if (hours > 24 || hours < 0 || minutes < 0 || minutes > 59)
            throw new IllegalArgumentException("Invalid time");

        if (!isWeekend && hours >= 9 && hours <= 18) {
            if (day != 8) return hours == 18 && minutes > 0;
            else return hours == 17 && minutes > 0;
        }
        return true;
    }
}
