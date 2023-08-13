package domain;

public class Level {
    private int levelID;
    private String levelName;
    private int cycleID;
    public Level(int levelID, String levelName, int cycleID) {
        this.levelID = levelID;
        this.levelName = levelName;
        this.cycleID = cycleID;
    }
    public int getLevelID() {
        return levelID;
    }
    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }
    public String getLevelName() {
        return levelName;
    }
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    public int getCycleID() {
        return cycleID;
    }
    public void setCycleID(int cycleID) {
        this.cycleID = cycleID;
    }
}
