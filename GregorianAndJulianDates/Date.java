/**
 * Assignment 6 for CS 1410
 * This program creates a Date super class.
 *
 * @author Brock Francom
 */

class Date {

    public int year;
    public int month;
    public int day;
// Initializes Date
    public Date() {
    }
    public void addDays(int days) {
        // this loop adds days by incrementing the days months and years accordingly.
        while (days > 0) {
            this.day += 1;
            days -= 1;
            if (this.day > getNumberOfDaysInMonth(this.year, this.month)) {
                this.month += 1;
                this.day = 1;
                if (this.month == 13) {
                    this.year += 1;
                    this.month = 1;
                }
            }
        }
    }
    public void subtractDays(int days) {
        // this loop subtracts days by decrementing the days months and years accordingly.
        while (days > 0) {
            this.day -= 1;
            days -= 1;
            if (this.day == 0) {
                this.month -= 1;
                if (this.month == 0) {
                    this.year -= 1;
                    this.month = 12;
                }
                this.day = getNumberOfDaysInMonth(this.year, this.month);
            }
        }
    }
    public void printShortDate() {
        System.out.print(this.month + "/" + this.day + "/" + this.year);

    }
    public void printLongDate() {
        System.out.print(this.getCurrentMonthName() + " " + this.day + ", " + this.year);

    }
    public String getCurrentMonthName() {
        String currentMonthName = getMonthName(this.month);
        return currentMonthName;
    }
    public int getCurrentMonth() {
        return this.month;

    }
    public int getCurrentYear() {

        return this.year;
    }
    public int getCurrentDayOfMonth() {

        return this.day;
    }
    public boolean isLeapYear(int year) {
        boolean leapYear = false;
        if ((year % 4) == 0 && (year % 100) != 0) {
            leapYear = true;
        }
        else if ((year % 4) == 0 && (year % 400) == 0) {
            leapYear = true;
        }
        return leapYear;
    }
    public boolean isLeapYear() {
        int year = this.year;
        boolean leapYear = false;
        if ((year % 4) == 0 && (year % 100) != 0) {
            leapYear = true;
        }
        else if ((year % 4) == 0 && (year % 400) == 0) {
            leapYear = true;
        }
        return leapYear;
    }
    public int getNumberOfDaysInMonth(int year, int month) {
        int days = 0;
        switch(month) {
            case 1:
                days = 31;
                break;
            case 2:
                if (isLeapYear(year)) {
                    days = 29;
                }
                else {
                    days = 28;
                }
                break;
            case 3:
                days = 31;
                break;
            case 4:
                days = 30;
                break;
            case 5:
                days = 31;
                break;
            case 6:
                days = 30;
                break;
            case 7:
                days = 31;
                break;
            case 8:
                days = 31;
                break;
            case 9:
                days = 30;
                break;
            case 10:
                days = 31;
                break;
            case 11:
                days = 30;
                break;
            case 12:
                days = 31;
                break;
        }
        return days;
    }
    public String getMonthName(int month) {
        String monthName = "";
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        return monthName;
    }
}