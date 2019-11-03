/**
 * GUI's Homework Assn1
 * Brock Francom, A02052161
 *
 * This will be a Faculty object.
 */
public class Faculty extends Employee {

    private String officeHours;
    private String rank;

    public Faculty(String name, String address, String phone, String email, String salary, String office, String dateHired, String officeHours, String rank) {
        super(name, address, phone, email, salary, office, dateHired); // uses Employee constructor
        setOfficeHours(officeHours);
        setRank(rank);
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Faculty class, " + name;
    }
}