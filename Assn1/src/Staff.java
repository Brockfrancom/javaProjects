/**
 * GUI's Homework Assn1
 * Brock Francom, A02052161
 *
 * This will be a Staff object.
 */
public class Staff extends Employee {

    private String title;

    public Staff(String name, String address, String phone, String email, String salary, String office, String dateHired, String title) {
        super(name, address, phone, email, salary, office, dateHired); // uses Employee constructor
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Staff class, " + name;
    }
}