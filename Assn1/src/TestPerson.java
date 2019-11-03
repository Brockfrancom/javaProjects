/**
 * GUI's Homework Assn1
 * Brock Francom, A02052161
 *
 * Note, this program will accept a date as mm/dd/yyyy.
 */
public class TestPerson {
    public static void main(String[] args) {

        //Creates a Employee object and uses methods.
        Employee e = new Employee("John", "20 N 40 S, Logan, UT", "8011234567", "john@gmail.com", "$20,000", "ENG 405", "12/12/2012");
        System.out.println(e.toString());
        System.out.println(e.getSalary());
        System.out.println(e.getOffice());
        System.out.println(e.getDateHired());
        instances(e);
        System.out.println();

        //Creates a Faculty object and uses methods.
        Faculty f = new Faculty("Ron", "200 N 500 S, Logan, UT", "8019874567", "ron@gmail.com", "$80,000", "ENG 205", "09/12/2019", "10:00-12:00", "Master");
        System.out.println(f.toString());
        System.out.println(f.getOfficeHours());
        System.out.println(f.getRank());
        instances(f);
        System.out.println();

        //Creates a Staff object and uses methods.
        Staff s = new Staff("Jack", "South Spinner Way, Logan, UT", "385259854", "Jack@gmail.com", "$500,000", "Old Main 201", "01/10/2030", "Dean");
        System.out.println(s.toString());
        System.out.println(s.getTitle());
        instances(s);
        System.out.println();

        //Creates a Student object and uses methods.
        Student stu = new Student("Brandon", "30 E 200 N, Logan, UT", "3851234567", "brandon@gmail.com", "Junior");
        System.out.println(stu.toString());
        System.out.println(stu.getClassStatus());
        instances(stu);
    }

    // method to test instancesof
    public static void instances(Object o) {
        if (o instanceof Person) {
            System.out.println("Is instance of Person? Yes");
        }
        else {
            System.out.println("Is instance of Person? No");
        }
        if (o instanceof Employee) {
            System.out.println("Is instance of Employee? Yes");
        }
        else {
            System.out.println("Is instance of Employee? No");
        }
        if (o instanceof Faculty) {
            System.out.println("Is instance of Faculty? Yes");
        }
        else {
            System.out.println("Is instance of Faculty? No");
        }
        if (o instanceof Staff) {
            System.out.println("Is instance of Staff? Yes");
        }
        else {
            System.out.println("Is instance of Staff? No");
        }
        if (o instanceof Student) {
            System.out.println("Is instance of Student? Yes");
        }
        else {
            System.out.println("Is instance of Student? No");
        }
    }
}