/**
 * GUI's Homework Assn1
 * Brock Francom, A02052161
 *
 * This will be a Date object.
 */
public class Date {

    public String year;
    public String month;
    public String day;

    public Date(String date) {
        try {
            var year = date.substring(6);
            var month = date.substring(0,2);
            var day = date.substring(3,5);

            if ((year.length() != 4) || (year.contains("/"))) {
                System.out.println("Date could not be initialized, please format your date yyyy/mm/dd and try again.");
                System.exit(0);
            }
            else {
                this.year = year;
            }
            if (month.contains("/")) {
                System.out.println("Date could not be initialized, please format your date yyyy/mm/dd and try again.");
                System.exit(0);
            }
            else {
                this.month = month;
            }
            if (day.contains("/")) {
                System.out.println("Date could not be initialized, please format your date yyyy/mm/dd and try again.");
                System.exit(0);
            }
            else {
                this.day = day;
            }
        }
        catch (Exception ex) {
            System.out.println("Date could not be initialized, please format your date mm/dd/yyyy and try again.");
            System.exit(0);
        }
    }

    public String getYear() {
        return this.year;
    }

    public String getMonth() {
        return this.month;
    }

    public String getDay() {
        return this.day;
    }

    public String getDate() {
        return (getMonth() + "/" + getDay() + "/" + getYear());
    }
}
