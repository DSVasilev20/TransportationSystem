package Dataaccesslayer.repositories;

import Dataaccesslayer.models.Package;
import Dataaccesslayer.models.User;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackageRepository {
    // Singleton instance for the repository
    private static PackageRepository instance = null;

    // Database connection instance
    Connection connection = new DBConnection().getInstance();

    // Singleton pattern: get an instance of PackageRepository
    public static PackageRepository getInstance() {
        if (PackageRepository.instance == null) {
            PackageRepository.instance = new PackageRepository();
        }
        return PackageRepository.instance;
    }

    // Default constructor
    public PackageRepository() {
    }

    // Retrieve a list of all packages from the database
    public List<Package> getPackages() {
        List<Package> packList = new ArrayList<>();

        // SQL query to retrieve all packages
        String sqlQuery = "SELECT * FROM Packages";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process each row in the result set
            while (resultSet.next()) {
                Package pack = new Package();
                pack.setPackageID(resultSet.getInt("Id"));
                pack.setSenderUserID(resultSet.getInt("SenderUserID"));
                pack.setReceiveUserID(resultSet.getInt("ReceiveUserID"));
                pack.setDescription(resultSet.getString("Description"));
                pack.setStatus(resultSet.getString("Status"));
                pack.setCurrentLocation(resultSet.getString("CurrentLocation"));
                pack.setDeliveryDate(resultSet.getString("DeliveryDate"));
                packList.add(pack);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return packList;
    }

    // Retrieve a package by its ID from the database
    public Package getPackageById(int id) {
        Package pack = new Package();
        String sqlQuery = "SELECT * FROM Packages WHERE Id = " + id;

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process the result set and set attributes for the package
            while (resultSet.next()) {
                pack.setPackageID(resultSet.getInt("Id"));
                pack.setSenderUserID(resultSet.getInt("SenderUserID"));
                pack.setReceiveUserID(resultSet.getInt("ReceiveUserID"));
                pack.setDescription(resultSet.getString("Description"));
                pack.setStatus(resultSet.getString("Status"));
                pack.setCurrentLocation(resultSet.getString("CurrentLocation"));
                pack.setDeliveryDate(resultSet.getString("DeliveryDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return pack;
    }

    // Retrieve a list of all package IDs from the database
    public List<Integer> getPackageIds() {
        List<Integer> packageIds = new ArrayList<>();
        String sqlQuery = "SELECT Id FROM Packages";
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process each row in the result set and add package IDs to the list
            while (resultSet.next()) {
                packageIds.add(resultSet.getInt("Id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return packageIds;
    }

    // Add a new package to the database
    public void addPackage(Package pack) {
        String sqlQuery = "INSERT INTO Packages (SenderUserID, ReceiveUserID, Description, Status, CurrentLocation, DeliveryDate) " + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Set parameters for the prepared statement
            PreparedStatementsPacked(pack, preparedStatement);

            // Execute the SQL update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing package in the database
    public void updatePackage(Package pack) {
        String sqlQuery = "UPDATE Packages SET SenderUserID = ?, ReceiveUserID = ?, Description = ?, Status = ?, CurrentLocation = ?, DeliveryDate = ? WHERE Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Set parameters for the prepared statement
            PreparedStatementsPacked(pack, preparedStatement);

            // Set the ID parameter for the update
            preparedStatement.setInt(7, pack.getPackageID());

            // Execute the SQL update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to pack prepared statements for consistency
    private void PreparedStatementsPacked(Package pack, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, pack.getSenderUserID());
        preparedStatement.setInt(2, pack.getReceiveUserID());
        preparedStatement.setString(3, pack.getDescription());
        preparedStatement.setString(4, pack.getStatus());
        preparedStatement.setString(5, pack.getCurrentLocation());
        preparedStatement.setString(6, pack.getDeliveryDate());
    }

    // Delete a package by its ID from the database
    public void deletePackage(int id) {
        String sqlQuery = "DELETE FROM Packages WHERE Id = ?";
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
