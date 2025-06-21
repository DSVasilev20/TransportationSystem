package Businesslogiclayer.services;
import Dataaccesslayer.models.User;
import Dataaccesslayer.repositories.UserRepository;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    // Singleton instance for the UserService
    private static UserService instance = null;

    // Singleton instance for the UserRepository
    private static final UserRepository repositoryInstance = UserRepository.getInstance();

    // Private constructor to prevent direct instantiation
    private UserService() {
    }

    // Method to get the singleton instance of UserService
    public static UserService getInstance(){
        if(UserService.instance == null){
            UserService.instance = new UserService();
        }
        return UserService.instance;
    }

    // Method to retrieve a list of all users
    public List<User> getUsers(){
        return repositoryInstance.getUsers();
    }

    // Method to retrieve a user by their ID
    public User getUserById(int id) {
        return repositoryInstance.getUserById(id);
    }

    // Method to add a new user
    public void addUser(User user) {
        repositoryInstance.addUser(user);
    }

    // Method to update an existing user
    public void updateUser(User user) {
        repositoryInstance.updateUser(user);
    }

    // Method to delete a user by their ID
    public void deleteUser(int id) {
        repositoryInstance.deleteUser(id);
    }

    // Method to retrieve a list of all user IDs
    public List<Integer> getUserIds() {
        return repositoryInstance.getUserIds();
    }
}