package domain; 
public class School {
    private int schoolID;
    private String schoolName;
    private String centerNumber;
    private String SchoolDescription;
    public School(int schoolID, String schoolName, String centerNumber, String schoolDescription) {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.centerNumber = centerNumber;
        SchoolDescription = schoolDescription;
    }
    public int getSchoolID() {
        return schoolID;
    }
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getCenterNumber() {
        return centerNumber;
    }
    public void setCenterNumber(String centerNumber) {
        this.centerNumber = centerNumber;
    }
    public String getSchoolDescription() {
        return SchoolDescription;
    }
    public void setSchoolDescription(String schoolDescription) {
        SchoolDescription = schoolDescription;
    }
}
