package Businesslogiclayer.services;
import Dataaccesslayer.models.TransportationVehicle;
import Dataaccesslayer.repositories.TransportationVehicleRepository;

import java.util.List;

public class TransportationVehicleService {
    // Singleton instance for the TransportationVehicleService
    private static TransportationVehicleService instance = null;

    // Singleton instance for the TransportationVehicleRepository
    private static final TransportationVehicleRepository repositoryInstance =
            TransportationVehicleRepository.getInstance();

    // Private constructor to prevent direct instantiation
    private TransportationVehicleService() {
    }

    // Method to get the singleton instance of TransportationVehicleService
    public static TransportationVehicleService getInstance() {
        if (TransportationVehicleService.instance == null) {
            TransportationVehicleService.instance = new TransportationVehicleService();
        }
        return TransportationVehicleService.instance;
    }

    // Method to retrieve a list of all transportation vehicles
    public List<TransportationVehicle> getTransportationVehicles() {
        return repositoryInstance.getTransportationVehicles();
    }

    // Method to retrieve a transportation vehicle by its ID
    public TransportationVehicle getTransportationVehicleById(int id) {
        return repositoryInstance.getTransportationVehicleById(id);
    }

    // Method to add a new transportation vehicle
    public void addTransportationVehicle(TransportationVehicle vehicle) {
        repositoryInstance.addTransportationVehicle(vehicle);
    }

    // Method to update an existing transportation vehicle
    public void updateTransportationVehicle(TransportationVehicle vehicle) {
        repositoryInstance.updateTransportationVehicle(vehicle);
    }

    // Method to delete a transportation vehicle by its ID
    public void deleteTransportationVehicle(int id) {
        repositoryInstance.deleteTransportationVehicle(id);
    }
}
