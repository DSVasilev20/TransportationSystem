package Dataaccesslayer.models;

public class Employee {
    // Employee attributes
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // Default constructor initializing attributes with default values
    public Employee() {
        setId(0);
        setFirstName("NotDefined");
        setLastName("NotDefined");
        setEmail("NotDefined");
        setPassword("NotDefined");
    }

    // Parameterized constructor to set attributes with provided values
    public Employee(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Overridden toString() method for better representation of the object
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
