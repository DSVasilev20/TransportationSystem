package Businesslogiclayer.services;

import Businesslogiclayer.services.TransportationVehicleService;
import Dataaccesslayer.models.TransportationVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransportationVehicleServiceTest {
    // Instance of TransportationVehicleService to be tested
    private TransportationVehicleService transportationVehicleService;

    // Set up the TransportationVehicleService instance before each test
    @BeforeEach
    void setUp() {
        transportationVehicleService = TransportationVehicleService.getInstance();
    }

    // Test to retrieve a list of transportation vehicles
    @Test
    void getTransportationVehicles() {
        List<TransportationVehicle> vehicles = transportationVehicleService.getTransportationVehicles();
        assertNotNull(vehicles);
    }

    // Test to retrieve a transportation vehicle by its ID
    @Test
    void getTransportationVehicleById() {
        TransportationVehicle vehicle = transportationVehicleService.getTransportationVehicleById(1);
        assertNotNull(vehicle);
    }

    // Test to add a new transportation vehicle
    @Test
    void addTransportationVehicle() {
        // Create a new transportation vehicle to be added
        TransportationVehicle newVehicle = new TransportationVehicle(0, "Truck", "Warehouse", 100);
        transportationVehicleService.addTransportationVehicle(newVehicle);

        // Retrieve the list of transportation vehicles and ensure the added vehicle is present
        List<TransportationVehicle> vehicles = transportationVehicleService.getTransportationVehicles();
        assertTrue(vehicles.stream().anyMatch(v -> v.getVehicleType().equals("Truck")));
    }
}
