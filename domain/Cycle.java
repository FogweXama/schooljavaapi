package domain; 

public class Cycle {
    private int cycleId;
    private int sectionID;
    private String cycleName;
    private String certificationToBeObtained;
    
    public int getCycleId() {
        return cycleId;
    }

    public void setCycleId(int cycleId) {
        this.cycleId = cycleId;
    }

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getCycleName() {
        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public String getCertificationToBeObtained() {
        return certificationToBeObtained;
    }

    public void setCertificationToBeObtained(String certificationToBeObtained) {
        this.certificationToBeObtained = certificationToBeObtained;
    }

    public Cycle(int cycleId, int sectionID, String cycleName, String certificationToBeObtained) {
        this.cycleId = cycleId;
        this.sectionID = sectionID;
        this.cycleName = cycleName;
        this.certificationToBeObtained = certificationToBeObtained;
    }
}
