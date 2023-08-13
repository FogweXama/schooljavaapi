package domain;

import java.util.Date;

public class Term {
    public Term(int termID, Date startDate, Date endDate, int coefficient, int academicYearID, String termName) {
        this.termID = termID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coefficient = coefficient;
        this.academicYearID = academicYearID;
        this.termName = termName;
    }
    private int termID;
    private Date startDate;
    private Date endDate;
    private int coefficient;
    private int academicYearID;
    private String termName;
    public int getTermID() {
        return termID;
    }
    public void setTermID(int termID) {
        this.termID = termID;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public int getCoefficient() {
        return coefficient;
    }
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
    public int getAcademicYearID() {
        return academicYearID;
    }
    public void setAcademicYearID(int academicYearID) {
        this.academicYearID = academicYearID;
    }
    public String getTermName() {
        return termName;
    }
    public void setTermName(String termName) {
        this.termName = termName;
    }
}
