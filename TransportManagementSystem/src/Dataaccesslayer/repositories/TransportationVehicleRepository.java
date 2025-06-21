package Dataaccesslayer.repositories;

import Dataaccesslayer.models.TransportationVehicle;
import Dataaccesslayer.models.User;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportationVehicleRepository {
    // Singleton instance for the repository
    private static TransportationVehicleRepository instance = null;

    // Database connection instance
    Connection connection = new DBConnection().getInstance();

    // Singleton pattern: get an instance of TransportationVehicleRepository
    public static TransportationVehicleRepository getInstance() {
        if (TransportationVehicleRepository.instance == null) {
            TransportationVehicleRepository.instance = new TransportationVehicleRepository();
        }
        return TransportationVehicleRepository.instance;
    }

    // Default constructor
    public TransportationVehicleRepository() {
    }

    // Retrieve a list of all transportation vehicles from the database
    public List<TransportationVehicle> getTransportationVehicles() {
        List<TransportationVehicle> vehicleList = new ArrayList<>();

        // SQL query to retrieve all transportation vehicles
        String sqlQuery = "SELECT * FROM TransportationVehicles";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process each row in the result set
            while (resultSet.next()) {
                TransportationVehicle vehicle = new TransportationVehicle();
                vehicle.setVehicleID(resultSet.getInt("Id"));
                vehicle.setVehicleType(resultSet.getString("VehicleType"));
                vehicle.setCurrentLocation(resultSet.getString("CurrentLocation"));
                vehicle.setCapacity(resultSet.getInt("Capacity"));
                vehicleList.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return vehicleList;
    }

    // Retrieve a transportation vehicle by its ID from the database
    public TransportationVehicle getTransportationVehicleById(int id) {
        TransportationVehicle vehicle = new TransportationVehicle();
        String sqlQuery = "SELECT * FROM TransportationVehicles WHERE Id = " + id;

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process the result set and set attributes for the transportation vehicle
            while (resultSet.next()) {
                vehicle.setVehicleID(resultSet.getInt("Id"));
                vehicle.setVehicleType(resultSet.getString("VehicleType"));
                vehicle.setCurrentLocation(resultSet.getString("CurrentLocation"));
                vehicle.setCapacity(resultSet.getInt("Capacity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return vehicle;
    }

    // Add a new transportation vehicle to the database
    public void addTransportationVehicle(TransportationVehicle vehicle) {
        String sqlQuery = "INSERT INTO TransportationVehicles (VehicleType, CurrentLocation, Capacity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Set parameters for the prepared statement
            PreparedStatementsPacked(vehicle, preparedStatement);

            // Execute the SQL update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing transportation vehicle in the database
    public void updateTransportationVehicle(TransportationVehicle vehicle) {

        String sqlQuery = "UPDATE TransportationVehicles SET VehicleType = ?, CurrentLocation = ?, Capacity = ? WHERE Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Set parameters for the prepared statement
            PreparedStatementsPacked(vehicle, preparedStatement);

            // Set the ID parameter for the update
            preparedStatement.setInt(4, vehicle.getVehicleID());

            // Execute the SQL update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to pack prepared statements for consistency
    private void PreparedStatementsPacked(TransportationVehicle vehicle, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, vehicle.getVehicleType());
        preparedStatement.setString(2, vehicle.getCurrentLocation());
        preparedStatement.setInt(3, vehicle.getCapacity());
    }

    // Delete a transportation vehicle by its ID from the database
    public void deleteTransportationVehicle(int id) {
        String sqlQuery = "DELETE FROM TransportationVehicles WHERE Id = ?";
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