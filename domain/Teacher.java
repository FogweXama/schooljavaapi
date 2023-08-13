package domain;

public class Teacher {
    private int teacherID;
    private int personID;
    private String holderOf;
    public int getTeacherID() {
        return teacherID;
    }
    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }
    public String getHolderOf() {
        return holderOf;
    }
    public void setHolderOf(String holderOf) {
        this.holderOf = holderOf;
    }
    public Teacher(int teacherID, int personID, String holderOf) {
        this.teacherID = teacherID;
        this.personID = personID;
        this.holderOf = holderOf;
    }
}
