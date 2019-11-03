/**
 * Assignment 6 for CS 1410
 * This program creates a GregorianDate class.
 *
 * @author Brock Francom
 */
public class GregorianDate extends Date {

    public GregorianDate() {
        //this calculates how many days, months and years have passed since 1/1/1970.
        this(1,1,1);
        long totalMilliSeconds = System.currentTimeMillis() + java.util.TimeZone.getDefault().getRawOffset();
        long totalSeconds = totalMilliSeconds / 1000;
        long totalMinutes = totalSeconds / 60;
        long totalHours = totalMinutes / 60;
        long totalDays = totalHours / 24;
        int totalYear = (int)(totalDays / 365);
        int currentYear = (totalYear + 1970);
        long currentDay = totalDays;
        int year = 1970;
        while (year < currentYear) {
            if (isLeapYear(year)) {
                currentDay -= 366;
                year += 1;
            } else {
                currentDay -= 365;
                year += 1;
            }
        }
        int currentMonth = 1;
        while (currentDay > 31) {
            currentDay -= getNumberOfDaysInMonth(currentYear, currentMonth);
            currentMonth += 1;
        }
        //these statements set the current date.
        super.year = currentYear;
        super.month = currentMonth;
        super.day = (int)(currentDay + 1); // there was initially one day to begin with.
    }
    public GregorianDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
