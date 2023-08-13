package domain;

public class LevelDivision {
    private int leveldivisionID;
    private String divisionName;
    private int levelID;
    public LevelDivision(int leveldivisionID, String divisionName, int levelID) {
        this.leveldivisionID = leveldivisionID;
        this.divisionName = divisionName;
        this.levelID = levelID;
    }
    public int getLeveldivisionID() {
        return leveldivisionID;
    }
    public void setLeveldivisionID(int leveldivisionID) {
        this.leveldivisionID = leveldivisionID;
    }
    public String getDivisionName() {
        return divisionName;
    }
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    public int getLevelID() {
        return levelID;
    }
    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }
}
