package domain;

public class HostBuilding {
    private int buildingID;
    private String buildingName;
    private String buildingDescription; 
    private double longitude;
    private double latitude;

    public HostBuilding(int buildingID, String buildingName, String buildingDescription, double longitude,
            double latitude) {
        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.buildingDescription = buildingDescription;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public int getBuildingID() {
        return buildingID;
    }
    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getBuildingDescription() {
        return buildingDescription;
    }
    public void setBuildingDescription(String buildingDescription) {
        this.buildingDescription = buildingDescription;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
