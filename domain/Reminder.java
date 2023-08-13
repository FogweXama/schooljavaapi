package domain;

import java.util.Date;

public class Reminder {
    private int reminderID;
    private Date dayOfClass;
    private Date timeOfClass;
    private boolean hasBeenNotified;

    public int getReminderID() {
        return reminderID;
    }

    public void setReminderID(int reminderID) {
        this.reminderID = reminderID;
    }

    public Date getDayOfClass() {
        return dayOfClass;
    }

    public void setDayOfClass(Date dayOfClass) {
        this.dayOfClass = dayOfClass;
    }

    public Date getTimeOfClass() {
        return timeOfClass;
    }

    public void setTimeOfClass(Date timeOfClass) {
        this.timeOfClass = timeOfClass;
    }

    public boolean isHasBeenNotified() {
        return hasBeenNotified;
    }

    public void setHasBeenNotified(boolean hasBeenNotified) {
        this.hasBeenNotified = hasBeenNotified;
    }

    public Reminder(int reminderID, Date dayOfClass, Date timeOfClass, boolean hasBeenNotified) {
        this.reminderID = reminderID;
        this.dayOfClass = dayOfClass;
        this.timeOfClass = timeOfClass;
        this.hasBeenNotified = hasBeenNotified;
    }
}
