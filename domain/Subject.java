package domain;

public class Subject {
    private int subjectID;
    private String subjectName;
    private String subjectCode;
    private int departmentID;
    private int subjectCoefficient;
    
    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getSubjectCoefficient() {
        return subjectCoefficient;
    }

    public void setSubjectCoefficient(int subjectCoefficient) {
        this.subjectCoefficient = subjectCoefficient;
    }

    public Subject(int subjectID, String subjectName, String subjectCode, int departmentID, int subjectCoefficient) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.departmentID = departmentID;
        this.subjectCoefficient = subjectCoefficient;
    }
}
