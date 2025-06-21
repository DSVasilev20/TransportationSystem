package Businesslogiclayer.services;

import Dataaccesslayer.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    // Instance of UserService to be tested
    private UserService userService;

    // Set up the UserService instance before each test
    @BeforeEach
    void setUp() {
        userService = UserService.getInstance();
    }

    // Test to retrieve a list of users
    @Test
    void getUsers() {
        List<User> users = userService.getUsers();
        assertNotNull(users);
    }

    // Test to retrieve a user by their ID
    @Test
    void getUserById() {
        User user = userService.getUserById(1);
        assertNotNull(user);
    }

    // Test to add a new user
    @Test
    void addUser() {
        // Create a new user to be added
        User newUser = new User(0, "John", "Doe", "john@example.com", "123456789");
        userService.addUser(newUser);

        // Retrieve the list of users and ensure the added user is present
        List<User> users = userService.getUsers();
        assertTrue(users.stream().anyMatch(u -> u.getEmail().equals("john@example.com")));
    }

    // Test to delete a user
    @Test
    void deleteUser() {
        // Create a new user to be deleted
        User newUser = new User(0, "ToDelete", "User", "todelete@example.com", "987654321");
        userService.addUser(newUser);

        // Get the ID of the user to be deleted
        int userId = userService.getUserIds().stream().max(Integer::compareTo).orElse(-1);

        // Delete the user
        userService.deleteUser(userId);

        // Retrieve the list of users and ensure the deleted user is not present
        List<User> users = userService.getUsers();
        assertFalse(users.stream().anyMatch(u -> u.getEmail().equals("todelete@example.com")));
    }

    // Test to retrieve a list of user IDs
    @Test
    void getUserIds() {
        List<Integer> userIds = userService.getUserIds();
        assertNotNull(userIds);
    }
}
