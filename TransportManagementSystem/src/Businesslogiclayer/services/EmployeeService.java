package Businesslogiclayer.services;
import Dataaccesslayer.models.Employee;
import Dataaccesslayer.models.User;
import Dataaccesslayer.repositories.EmployeeRepository;
import Dataaccesslayer.repositories.UserRepository;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    // Singleton instance of the EmployeeService
    private static EmployeeService instance = null;

    // Repository instance to interact with Employee data
    private static final EmployeeRepository repositoryInstance  = EmployeeRepository.getInstance();

    // Private constructor to enforce singleton pattern
    private EmployeeService() {
    }

    // Method to retrieve the singleton instance of EmployeeService
    public static EmployeeService getInstance(){
        if(EmployeeService.instance == null){
            EmployeeService.instance = new EmployeeService();
        }
        return EmployeeService.instance;
    }

    // Method to get a list of all employees
    public List<Employee> getEmployees(){
        return repositoryInstance.getEmployees();
    }

    // Method to get an employee by their ID
    public Employee getEmployeeById(int id) {
        return repositoryInstance.getEmployeeById(id);
    }

    // Method to get an employee by their email
    public Employee getEmployeeByEmail(String email) {
        return repositoryInstance.getEmployeeByEmail(email);
    }
}