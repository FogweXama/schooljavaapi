package domain;

import java.util.Date;

public class Sequence {
    private int sequenceID;
    private Date startDate;
    private Date endDate;
    private int coefficient;
    private int termID;
    private String sequenceName;
    
    public int getSequenceID() {
        return sequenceID;
    }

    public void setSequenceID(int sequenceID) {
        this.sequenceID = sequenceID;
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

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public Sequence(int sequenceID, Date startDate, Date endDate, int coefficient, int termID, String sequenceName) {
        this.sequenceID = sequenceID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coefficient = coefficient;
        this.termID = termID;
        this.sequenceName = sequenceName;
    }
}
