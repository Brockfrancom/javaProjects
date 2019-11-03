/**
 * GUI's Homework Assn1
 * Brock Francom, A02052161
 *
 * This will be a Employee object.
 */
public class Employee extends Person {

    private String salary;
    private String dateHired;
    private String office;

    public Employee(String name, String address, String phone, String email, String salary, String office, String dateHired) {
        super(name, address, phone, email); // uses Person constructor
        setSalary(salary);
        setOffice(office);
        setDateHired(dateHired);
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDateHired() {
        return dateHired;
    }

    public void setDateHired(String dateHired) {
        Date date = new Date(dateHired);
        this.dateHired = date.getDate();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "Employee class, " + name;
    }
}