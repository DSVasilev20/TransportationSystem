package Dataaccesslayer.models;

public class Location {
    // Location attributes
    private int id;
    private int packageID;
    private String timestamp;
    private float latitude;
    private float longitude;

    // Default constructor initializing attributes with default values
    public Location() {
        setId(0);
        setPackageID(0);
        setTimestamp("NotDefined");
        setLatitude(0);
        setLongitude(0);
    }

    // Parameterized constructor to set attributes with provided values
    public Location(int id, int packageID, String timestamp, float latitude, float longitude) {
        this.id = id;
        this.packageID = packageID;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter and setter methods for each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    // Overridden toString() method for better representation of the object
    @Override
    public String toString() {
        return "Location{" +
                "locationID=" + id +
                ", packageID=" + packageID +
                ", timestamp='" + timestamp + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
