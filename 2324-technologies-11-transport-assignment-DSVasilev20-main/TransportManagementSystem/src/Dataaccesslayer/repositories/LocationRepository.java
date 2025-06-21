package Dataaccesslayer.repositories;

import Dataaccesslayer.models.Location;
import Dataaccesslayer.models.User;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository {
    // Singleton instance for the repository
    private static LocationRepository instance = null;

    // Database connection instance
    Connection connection = new DBConnection().getInstance();

    // Singleton pattern: get an instance of LocationRepository
    public static LocationRepository getInstance() {
        if (LocationRepository.instance == null) {
            LocationRepository.instance = new LocationRepository();
        }
        return LocationRepository.instance;
    }

    // Default constructor
    public LocationRepository() {
    }

    // Retrieve a list of all locations from the database
    public List<Location> getLocations() {
        List<Location> locationList = new ArrayList<>();

        // SQL query to retrieve all locations
        String sqlQuery = "SELECT * FROM Locations";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process each row in the result set
            while (resultSet.next()) {
                Location location = new Location();
                location.setId(resultSet.getInt("Id"));
                location.setPackageID(resultSet.getInt("PackageID"));
                location.setTimestamp(resultSet.getString("Timestamp"));
                location.setLatitude(resultSet.getFloat("Latitude"));
                location.setLongitude(resultSet.getFloat("Longitude"));
                locationList.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return locationList;
    }

    // Retrieve a location by its ID from the database
    public Location getLocationById(int id) {
        Location location = new Location();

        // SQL query to retrieve a location by ID
        String sqlQuery = "SELECT * FROM Locations WHERE Id = " + id;

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            // Process the result set and set attributes for the location
            while (resultSet.next()) {
                location.setId(resultSet.getInt("Id"));
                location.setPackageID(resultSet.getInt("PackageID"));
                location.setTimestamp(resultSet.getString("Timestamp"));
                location.setLatitude(resultSet.getFloat("Latitude"));
                location.setLongitude(resultSet.getFloat("Longitude"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query: " + e.getMessage());
        }

        return location;
    }

    // Add a new location to the database
    public void addLocation(Location location) {
        String sqlQuery = "INSERT INTO Locations (PackageID, Timestamp, Latitude, Longitude)" + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Set parameters for the prepared statement
            PreparedStatementsPacked(location, preparedStatement);

            // Execute the SQL update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing location in the database
    public void updateLocation(Location location) {
        String sqlQuery = "UPDATE Locations SET PackageID = ?, Timestamp = ?, Latitude = ?, Longitude = ? WHERE Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Set parameters for the prepared statement
            PreparedStatementsPacked(location, preparedStatement);

            // Set the ID parameter for the update
            preparedStatement.setInt(5, location.getId());

            // Execute the SQL update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to pack prepared statements for consistency
    private void PreparedStatementsPacked(Location location, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, location.getPackageID());
        preparedStatement.setString(2, location.getTimestamp());
        preparedStatement.setFloat(3, location.getLatitude());
        preparedStatement.setFloat(4, location.getLongitude());
    }

    // Delete a location by its ID from the database
    public void deleteLocation(int id) {
        String sqlQuery = "DELETE FROM Locations WHERE Id = ?";
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