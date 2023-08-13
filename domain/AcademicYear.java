package domain;

import java.util.Date;

public class AcademicYear {
    private Integer academicYearID;
    private String academicYearName;
    private Date endDate;
    private Date startDate;

    public AcademicYear(Integer academicYearID, String academicYearName, Date startDate, Date endDate) {
        this.academicYearID = academicYearID;
        this.academicYearName = academicYearName;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public Integer getacademicYearID() {
        return academicYearID;
    }

    public void setacademicYearID(Integer academicYearID) {
        this.academicYearID = academicYearID;
    }

    public String getacademicYearName() {
        return academicYearName;
    }

    public void setacademicYearName(String academicYearName) {
        this.academicYearName = academicYearName;
    }

    public Date getendDate() {
        return endDate;
    }

    public void setendDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getstartDate() {
        return startDate;
    }

    public void setstartDate(Date startDate) {
        this.startDate = startDate;
    }
}
