package domain; 

public class Section {
    private int sectionID;
    private String sectionName;
    private int schoolID;
    public Section(int sectionID, String sectionName, int schoolID) {
        this.sectionID = sectionID;
        this.sectionName = sectionName;
        this.schoolID = schoolID;
    }
    public int getSectionID() {
        return sectionID;
    }
    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }
    public String getSectionName() {
        return sectionName;
    }
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public int getSchoolID() {
        return schoolID;
    }
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }
}
