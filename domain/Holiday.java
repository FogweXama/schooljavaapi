package domain;

import java.util.Date;

public class Holiday {
    private int holidayID;
    private String holidayName;
    private Date start_time;
    private Date end_time;
    private int termID;

    public Holiday(int holidayID, String holidayName, Date start_time, Date end_time, int termID) {
        this.holidayID = holidayID;
        this.holidayName = holidayName;
        this.start_time = start_time;
        this.end_time = end_time;
        this.termID = termID;
    }

    public int getHolidayID() {
        return holidayID;
    }

    public void setHolidayID(int holidayID) {
        this.holidayID = holidayID;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
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

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }
}
