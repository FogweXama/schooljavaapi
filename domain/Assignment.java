package domain;

import java.util.Date;

public class Assignment {
    private int assignmentID;
    private Date dueDate;
    private String taskGiven;
    private int tslid;
    private int assignmentCoefficient;
    
    public int getAssignmentID() {
        return assignmentID;
    }
    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public String getTaskGiven() {
        return taskGiven;
    }
    public void setTaskGiven(String taskGiven) {
        this.taskGiven = taskGiven;
    }
    public int getTslid() {
        return tslid;
    }
    public void setTslid(int tslid) {
        this.tslid = tslid;
    }
    public int getAssignmentCoefficient() {
        return assignmentCoefficient;
    }
    public void setAssignmentCoefficient(int assignmentCoefficient) {
        this.assignmentCoefficient = assignmentCoefficient;
    }
    public Assignment(int assignmentID, Date dueDate, String taskGiven, int tslid, int assignmentCoefficient) {
        this.assignmentID = assignmentID;
        this.dueDate = dueDate;
        this.taskGiven = taskGiven;
        this.tslid = tslid;
        this.assignmentCoefficient = assignmentCoefficient;
    }
}
