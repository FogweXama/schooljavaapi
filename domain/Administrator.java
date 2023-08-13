package domain;

public class Administrator {
    private int staffID;
    private int administratorID;
    private String administrationname;
    private int roleID;
    private String office;

    public Administrator(int staffID, int administratorID, String administrationname, int roleID, String office) {
        this.staffID = staffID;
        this.administratorID = administratorID;
        this.administrationname = administrationname;
        this.roleID = roleID;
        this.office = office;
    }
    
    public int getStaffID() {
        return staffID;
    }
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    public int getAdministratorID() {
        return administratorID;
    }
    public void setAdministratorID(int administratorID) {
        this.administratorID = administratorID;
    }
    public String getAdministrationname() {
        return administrationname;
    }
    public void setAdministrationname(String administrationname) {
        this.administrationname = administrationname;
    }
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    public String getOffice() {
        return office;
    }
    public void setOffice(String office) {
        this.office = office;
    }

}
