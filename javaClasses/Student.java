/**
 * GUI's Homework Assn1
 * Brock Francom, A02052161
 *
 * This will be a Student object.
 */
public class Student extends Person{

    private String classStatus;

    public Student(String name, String address, String phone, String email, String classStatus) {
        super(name, address, phone, email); //uses Person constructor
        setClassStatus(classStatus);
    }

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }

    @Override
    public String toString() {
        return "Student class, " + name;
    }
}