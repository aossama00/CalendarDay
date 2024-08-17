import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year, month, day;
        do{
            System.out.println("Enter a valid year: ");
            year = scanner.nextInt();
        }
        while(year <= 0);
        do{
            System.out.println("Enter a valid month: ");
            month = scanner.nextInt();
        }
        while(month <= 0 || month > 12);
        do{
            System.out.println("Enter a valid day: ");
            day = scanner.nextInt();
        }
        while(day <= 0 || day > 31);

        LocalDate date = LocalDate.of(year, month, day);
        System.out.println("The week day of " + date + ": " + calendarDay(date));
    }
    public enum WeekDay {
        SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;
        private static final WeekDay[] ENUMS = WeekDay.values();
        public static WeekDay of(int value){
            return ENUMS[value];
        }
    }
    public static WeekDay calendarDay(LocalDate date) {
        // Friday is the reference day because all years divisible by 28 begins with it
        WeekDay referenceDay = WeekDay.FRIDAY;
        int daysCount = 0;
        int daysCountUntil28 = 0;

        Month month = date.getMonth();
        int day = date.getDayOfMonth() - 1;
        int year = date.getYear();

        if ((year % 28) != 0) {
            int yearsFrom28 = year % 28;
            if (yearsFrom28 < 4) {
                daysCountUntil28 += 366 + (yearsFrom28 - 1) * 365;
            } else {
                for (int i = 0; i < yearsFrom28; i += 4) {
                    if ((yearsFrom28 - i) < 4) {
                        daysCountUntil28 += 366 + (yearsFrom28 - i - 1) * 365;
                        break;
                    }
                    daysCountUntil28 += 366 + (3 * 365);
                }
            }
        }
        // Adding the 29th of February Day
        if (month.getValue() > 2 && date.isLeapYear())
            daysCount += 1;

        switch (month) {
            case JANUARY:
                daysCount += day;
                break;
            case FEBRUARY:
                daysCount += day + 31;
                break;
            case MARCH:
                daysCount += day + 59;
                break;
            case APRIL:
                daysCount += day + 90;
                break;
            case MAY:
                daysCount += day + 120;
                break;
            case JUNE:
                daysCount += day + 151;
                break;
            case JULY:
                daysCount += day + 181;
                break;
            case AUGUST:
                daysCount += day + 212;
                break;
            case SEPTEMBER:
                daysCount += day + 243;
                break;
            case OCTOBER:
                daysCount += day + 273;
                break;
            case NOVEMBER:
                daysCount += day + 304;
                break;
            case DECEMBER:
                daysCount += day + 334;
                break;
        }
        daysCount += daysCountUntil28;
        int dayIndex = (((daysCount % 7) + referenceDay.ordinal()) % 7);
        return WeekDay.of(dayIndex);
    }
}