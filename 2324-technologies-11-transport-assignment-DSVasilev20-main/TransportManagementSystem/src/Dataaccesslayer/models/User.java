package Dataaccesslayer.models;

public class User {
    // User attributes
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    // Default constructor initializing attributes with default values
    public User() {
        setId(0);
        setFirstName("NotDefined");
        setLastName("NotDefined");
        setEmail("NotDefined");
        setPhoneNumber("NotDefined");
    }

    // Parameterized constructor to set attributes with provided values
    public User(int id, String firstName, String lastName, String email, String phoneNumber) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    // Getter and setter methods for each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    // Overridden toString() method for better representation of the object
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
