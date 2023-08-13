package domain;

public class Day {
    private int dayID;
    private String dayName;
    public Day(int dayID, String dayName) {
        this.dayID = dayID;
        this.dayName = dayName;
    }
    public int getDayID() {
        return dayID;
    }
    public void setDayID(int dayID) {
        this.dayID = dayID;
    }
    public String getDayName() {
        return dayName;
    }
    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
}
