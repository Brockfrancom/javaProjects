/**
 * Assignment 6 for CS 1410
 * This program creates a JulianDate class.
 *
 * @author Brock Francom
 */
public class JulianDate extends Date {

    public JulianDate() {
        //first an instance of JulianDate is created and then the difference in days between the gregorian start
        //date and the Julian start date is added to the instance.
        this(1,1,1);
        super.addDays(719164);
        // the days and months and years since 1/1/1970 are calculated
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
        // the date is set to Julian date
        super.year = (super.getCurrentYear() + totalYear) + 1;
        super.month = (super.getCurrentMonth() + currentMonth - 1) % 12;
        super.day = (super.getCurrentDayOfMonth() + (int) currentDay) - 1;
        if (super.day > getNumberOfDaysInMonth(super.year, super.month)) {
            super.day -= getNumberOfDaysInMonth(super.year, super.month);
            super.month += 1;
        }
    }
    public JulianDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public boolean isLeapYear() {
        //this function is unique to JulianDate
        int year = this.year;
        boolean leapYear = false;
        if ((year % 4) == 0) {
            leapYear = true;
        }
        return leapYear;
    }
    public boolean isLeapYear(int year) {
        boolean leapYear = false;
        if ((year % 4) == 0) {
            leapYear = true;
        }
        return leapYear;
    }
}