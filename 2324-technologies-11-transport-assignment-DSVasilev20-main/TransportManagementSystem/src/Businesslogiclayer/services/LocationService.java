package Businesslogiclayer.services;
import Dataaccesslayer.models.Location;
import Dataaccesslayer.repositories.LocationRepository;

import java.util.List;

public class LocationService {
    // Singleton instance for the LocationService
    private static LocationService instance = null;

    // Singleton instance for the LocationRepository
    private static final LocationRepository repositoryInstance = LocationRepository.getInstance();

    // Private constructor to prevent direct instantiation
    private LocationService() {
    }

    // Method to get the singleton instance of LocationService
    public static LocationService getInstance() {
        if (LocationService.instance == null) {
            LocationService.instance = new LocationService();
        }
        return LocationService.instance;
    }

    // Method to retrieve a list of all locations
    public List<Location> getLocations() {
        return repositoryInstance.getLocations();
    }

    // Method to retrieve a location by its ID
    public Location getLocationById(int id) {
        return repositoryInstance.getLocationById(id);
    }

    // Method to add a new location
    public void addLocation(Location location) {
        repositoryInstance.addLocation(location);
    }

    // Method to update an existing location
    public void updateLocation(Location location) {
        repositoryInstance.updateLocation(location);
    }

    // Method to delete a location by its ID
    public void deleteLocation(int id) {
        repositoryInstance.deleteLocation(id);
    }
}