package domain; 

public class Student {
    private int studentID;
    private String studentAdmissionNo;
    private String reportNo;
    private String classIn;
    private String productDescription;
    private int personID;
    public Student(int studentID, String studentAdmissionNo, String reportNo, String classIn, String productDescription,
            int personID) {
        this.studentID = studentID;
        this.studentAdmissionNo = studentAdmissionNo;
        this.reportNo = reportNo;
        this.classIn = classIn;
        this.productDescription = productDescription;
        this.personID = personID;
    }
    public int getStudentID() {
        return studentID;
    }
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    public String getStudentAdmissionNo() {
        return studentAdmissionNo;
    }
    public void setStudentAdmissionNo(String studentAdmissionNo) {
        this.studentAdmissionNo = studentAdmissionNo;
    }
    public String getReportNo() {
        return reportNo;
    }
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }
    public String getClassIn() {
        return classIn;
    }
    public void setClassIn(String classIn) {
        this.classIn = classIn;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }
}