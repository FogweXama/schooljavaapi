package domain; 

public class Department {
    private int departmentID;
    private int schoolID;
    private String departmentName;
    public Department(int departmentID, int schoolID, String departmentName) {
        this.departmentID = departmentID;
        this.schoolID = schoolID;
        this.departmentName = departmentName;
    }
    public int getDepartmentID() {
        return departmentID;
    }
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
    public int getSchoolID() {
        return schoolID;
    }
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
