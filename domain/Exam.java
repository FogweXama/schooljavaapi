package domain; 

public class Exam {
    private int examID;
    private String examName;
    private String examDescription; 
    private int examCoefficient;
    private int tslID;
    private int buildingID;
    private int periodID;
    public Exam(int examID, String examName, String examDescription, int examCoefficient, int tslID, int buildingID,
            int periodID) {
        this.examID = examID;
        this.examName = examName;
        this.examDescription = examDescription;
        this.examCoefficient = examCoefficient;
        this.tslID = tslID;
        this.buildingID = buildingID;
        this.periodID = periodID;
    }
    public int getExamID() {
        return examID;
    }
    public void setExamID(int examID) {
        this.examID = examID;
    }
    public String getExamName() {
        return examName;
    }
    public void setExamName(String examName) {
        this.examName = examName;
    }
    public String getExamDescription() {
        return examDescription;
    }
    public void setExamDescription(String examDescription) {
        this.examDescription = examDescription;
    }
    public int getExamCoefficient() {
        return examCoefficient;
    }
    public void setExamCoefficient(int examCoefficient) {
        this.examCoefficient = examCoefficient;
    }
    public int getTslID() {
        return tslID;
    }
    public void setTslID(int tslID) {
        this.tslID = tslID;
    }
    public int getBuildingID() {
        return buildingID;
    }
    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }
    public int getPeriodID() {
        return periodID;
    }
    public void setPeriodID(int periodID) {
        this.periodID = periodID;
    }
}
