/**
 * GUI's Homework Assn1
 * Brock Francom, A02052161
 *
 * This will be a Person object.
 */
public class Person {

    public String name;
    private String email;
    private String phoneNumber;
    private String address;

    public Person(String name, String address, String phone, String email) {
        setName(name);
        setAddress(address);
        setPhoneNumber(phone);
        setEmail(email);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String toString() {
        return this.name;
    }
}