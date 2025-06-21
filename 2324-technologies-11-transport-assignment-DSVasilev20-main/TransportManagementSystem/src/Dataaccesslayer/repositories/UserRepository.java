package Dataaccesslayer.repositories;

import Dataaccesslayer.models.User;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    // Singleton instance for the repository
    private static UserRepository instance = null;

    // Database connection instance
    Connection connection = new DBConnection().getInstance();

    // Singleton pattern: get an instance of UserRepository
    public static UserRepository getInstance() {
        if (UserRepository.instance == null) {
            UserRepository.instance = new UserRepository();
        }
        return UserRepository.instance;
    }

    // Default constructor
    public UserRepository() {
    }

    // Retrieve a list of all users from the database
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        // SQL query to retrieve all users
        String sqlQuery = "SELECT * FROM Users";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process each row in the result set
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("Id"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setEmail(resultSet.getString("Email"));
                user.setPhoneNumber(resultSet.getString("PhoneNumber"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return userList;
    }

    // Retrieve a user by their ID from the database
    public User getUserById(int id) {
        User user = new User();
        String sqlQuery = "SELECT * FROM Users WHERE Id = " + id;

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process the result set and set attributes for the user
            while (resultSet.next()) {
                user.setId(resultSet.getInt("Id"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setEmail(resultSet.getString("Email"));
                user.setPhoneNumber(resultSet.getString("PhoneNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return user;
    }

    // Retrieve a list of user IDs from the database
    public List<Integer> getUserIds() {

        List<Integer> userIds = new ArrayList<>();
        String sqlQuery = "SELECT Id FROM Users";
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process each row in the result set and add user IDs to the list
            while (resultSet.next()) {
                userIds.add(resultSet.getInt("Id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return userIds;
    }

    // Add a new user to the database
    public void addUser(User user) {
        String sqlQuery = "INSERT INTO Users (FirstName, LastName, Email, PhoneNumber) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Set parameters for the prepared statement
            PreparedStatementsPacked(user, preparedStatement);

            // Execute the SQL update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing user in the database
    public void updateUser(User user) {
        String sqlQuery = "UPDATE Users SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ? WHERE Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Set parameters for the prepared statement
            PreparedStatementsPacked(user, preparedStatement);

            // Set the ID parameter for the update
            preparedStatement.setInt(5, user.getId());

            // Execute the SQL update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to pack prepared statements for consistency
    private void PreparedStatementsPacked(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPhoneNumber());
    }

    // Delete a user by their ID from the database
    public void deleteUser(int id) {
        String sqlQuery = "DELETE FROM Users WHERE Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Set the ID parameter for the deletion
            preparedStatement.setInt(1, id);

            // Execute the SQL deletion
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}