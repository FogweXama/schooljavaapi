package domain;

import java.util.Date;

public class Person {
    private int personID;
    private String personName;
    private String personAddress;
    private String personTelephone;
    private Date personDOB;
    private byte ofDeclareSex;
    private Date yearIn;
    private int roleID;
    private boolean isFullTime;
    private String personMatricule;
    private boolean isCurrentlyActive;
    private Date exitDate;
    
    public Person(int personID, String personName, String personAddress, String personTelephone, Date personDOB,
            byte ofDeclareSex, Date yearIn, int roleID, boolean isFullTime, String personMatricule,
            boolean isCurrentlyActive, Date exitDate) {
        this.personID = personID;
        this.personName = personName;
        this.personAddress = personAddress;
        this.personTelephone = personTelephone;
        this.personDOB = personDOB;
        this.ofDeclareSex = ofDeclareSex;
        this.yearIn = yearIn;
        this.roleID = roleID;
        this.isFullTime = isFullTime;
        this.personMatricule = personMatricule;
        this.isCurrentlyActive = isCurrentlyActive;
        this.exitDate = exitDate;
    }
    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public String getPersonAddress() {
        return personAddress;
    }
    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }
    public String getPersonTelephone() {
        return personTelephone;
    }
    public void setPersonTelephone(String personTelephone) {
        this.personTelephone = personTelephone;
    }
    public Date getPersonDOB() {
        return personDOB;
    }
    public void setPersonDOB(Date personDOB) {
        this.personDOB = personDOB;
    }
    public byte getOfDeclareSex() {
        return ofDeclareSex;
    }
    public void setOfDeclareSex(byte ofDeclareSex) {
        this.ofDeclareSex = ofDeclareSex;
    }
    public Date getYearIn() {
        return yearIn;
    }
    public void setYearIn(Date yearIn) {
        this.yearIn = yearIn;
    }
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    public boolean isFullTime() {
        return isFullTime;
    }
    public void setFullTime(boolean isFullTime) {
        this.isFullTime = isFullTime;
    }
    public String getPersonMatricule() {
        return personMatricule;
    }
    public void setPersonMatricule(String personMatricule) {
        this.personMatricule = personMatricule;
    }
    public boolean isCurrentlyActive() {
        return isCurrentlyActive;
    }
    public void setCurrentlyActive(boolean isCurrentlyActive) {
        this.isCurrentlyActive = isCurrentlyActive;
    }
    public Date getExitDate() {
        return exitDate;
    }
    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }
}
