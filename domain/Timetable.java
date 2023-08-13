package domain;

public class Timetable {
    private int timetableID;
    private int buildingID;
    private int periodID;
    private int tslID;
    private int dayID;
    
    public int getTimetableID() {
        return timetableID;
    }

    public void setTimetableID(int timetableID) {
        this.timetableID = timetableID;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public int getPeriodID() {
        return periodID;
    }

    public void setPeriodID(int periodID) {
        this.periodID = periodID;
    }

    public int getTslID() {
        return tslID;
    }

    public void setTslID(int tslID) {
        this.tslID = tslID;
    }

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public Timetable(int timetableID, int buildingID, int periodID, int tslID, int dayID) {
        this.timetableID = timetableID;
        this.buildingID = buildingID;
        this.periodID = periodID;
        this.tslID = tslID;
        this.dayID = dayID;
    }
}
