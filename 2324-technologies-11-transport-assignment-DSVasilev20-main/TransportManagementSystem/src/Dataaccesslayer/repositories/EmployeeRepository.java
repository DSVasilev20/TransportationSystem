package Dataaccesslayer.repositories;

import Dataaccesslayer.models.Employee;
import Dataaccesslayer.models.User;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    // Singleton instance for the repository
    private static EmployeeRepository instance = null;

    // Database connection instance
    Connection connection = new DBConnection().getInstance();

    // Singleton pattern: get an instance of EmployeeRepository
    public static EmployeeRepository getInstance() {
        if (EmployeeRepository.instance == null) {
            EmployeeRepository.instance = new EmployeeRepository();
        }
        return EmployeeRepository.instance;
    }

    // Default constructor
    public EmployeeRepository() {
    }

    // Retrieve a list of all employees from the database
    public List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        // SQL query to retrieve all employees
        String sqlQuery = "SELECT * FROM Employees";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process each row in the result set
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("Id"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setEmail(resultSet.getString("Email"));
                employee.setPassword(resultSet.getString("Password"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return employeeList;
    }

    // Retrieve an employee by their ID from the database
    public Employee getEmployeeById(int id) {
        Employee employee = new Employee();

        // SQL query to retrieve an employee by ID
        String sqlQuery = "SELECT * FROM Employees WHERE Id = " + id;

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process the result set and set attributes for the employee
            while (resultSet.next()) {
                employee.setId(resultSet.getInt("Id"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setEmail(resultSet.getString("Email"));
                employee.setPassword(resultSet.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return employee;
    }

    // Retrieve an employee by their email from the database
    public Employee getEmployeeByEmail(String email) {
        Employee employee = new Employee();

        // SQL query to retrieve an employee by email
        String sqlQuery = "SELECT * FROM Employees WHERE Email = '" + email + "'";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process the result set and set attributes for the employee
            while (resultSet.next()) {
                employee.setId(resultSet.getInt("Id"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setEmail(resultSet.getString("Email"));
                employee.setPassword(resultSet.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return employee;
    }
}