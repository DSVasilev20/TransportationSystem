package Dataaccesslayer.models;

public class Package {
    // Package attributes
    private int packageID;
    private int senderUserID;
    private int receiveUserID;
    private String description;
    private String status;
    private String currentLocation;
    private String deliveryDate;

    // Default constructor initializing attributes with default values
    public Package() {
        setPackageID(0);
        setSenderUserID(0);
        setReceiveUserID(0);
        setDescription("NotDefined");
        setStatus("NotDefined");
        setCurrentLocation("NotDefined");
        setDeliveryDate("NotDefined");
    }

    // Parameterized constructor to set attributes with provided values
    public Package(int packageID, int senderUserID, int receiveUserID, String description, String status, String currentLocation, String deliveryDate) {
        this.packageID = packageID;
        this.senderUserID = senderUserID;
        this.receiveUserID = receiveUserID;
        this.description = description;
        this.status = status;
        this.currentLocation = currentLocation;
        this.deliveryDate = deliveryDate;
    }

    // Getter and setter methods for each attribute
    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public int getSenderUserID() {
        return senderUserID;
    }

    public void setSenderUserID(int senderUserID) {
        this.senderUserID = senderUserID;
    }

    public int getReceiveUserID() {
        return receiveUserID;
    }

    public void setReceiveUserID(int receiveUserID) {
        this.receiveUserID = receiveUserID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    // Overridden toString() method for better representation of the object
    @Override
    public String toString() {
        return "Package{" +
                "packageID=" + packageID +
                ", senderUserID=" + senderUserID +
                ", receiveUserID=" + receiveUserID +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                '}';
    }
}
