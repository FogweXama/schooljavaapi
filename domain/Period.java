package domain;

import java.util.Date;

public class Period {
    private int periodID;
    private String periodName; 
    private Date start_time;
    private Date end_time;
    public Period(int periodID, String periodName, Date start_time, Date end_time) {
        this.periodID = periodID;
        this.periodName = periodName;
        this.start_time = start_time;
        this.end_time = end_time;
    }
    public int getPeriodID() {
        return periodID;
    }
    public void setPeriodID(int periodID) {
        this.periodID = periodID;
    }
    public String getPeriodName() {
        return periodName;
    }
    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }
    public Date getStart_time() {
        return start_time;
    }
    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }
    public Date getEnd_time() {
        return end_time;
    }
    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
}
