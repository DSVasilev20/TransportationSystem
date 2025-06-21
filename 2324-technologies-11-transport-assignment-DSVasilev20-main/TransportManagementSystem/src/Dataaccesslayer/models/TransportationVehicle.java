package Dataaccesslayer.models;

public class TransportationVehicle {
    // TransportationVehicle attributes
    private int vehicleID;
    private String vehicleType;
    private String currentLocation;
    private int capacity;

    // Default constructor initializing attributes with default values
    public TransportationVehicle() {
        setVehicleID(0);
        setVehicleType("NotDefined");
        setCurrentLocation("NotDefined");
        setCapacity(0);
    }

    // Parameterized constructor to set attributes with provided values
    public TransportationVehicle(int vehicleID, String vehicleType, String currentLocation, int capacity) {
        this.vehicleID = vehicleID;
        this.vehicleType = vehicleType;
        this.currentLocation = currentLocation;
        this.capacity = capacity;
    }

    // Getter and setter methods for each attribute
    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Overridden toString() method for better representation of the object
    @Override
    public String toString() {
        return "TransportationVehicle{" +
                "vehicleID=" + vehicleID +
                ", vehicleType='" + vehicleType + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
