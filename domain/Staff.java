package domain; 

public class Staff {
    private int staffID;
    private int personID;
    private String shift;
    private boolean ptaStaff;
    private String productDescription;
    private String username;
    private String userpwd;
    private int Salary;
    public Staff(int staffID, int personID, String shift, boolean ptaStaff, String productDescription, String username,
            String userpwd, int salary) {
        this.staffID = staffID;
        this.personID = personID;
        this.shift = shift;
        this.ptaStaff = ptaStaff;
        this.productDescription = productDescription;
        this.username = username;
        this.userpwd = userpwd;
        Salary = salary;
    }
    public int getStaffID() {
        return staffID;
    }
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }
    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }
    public boolean isPtaStaff() {
        return ptaStaff;
    }
    public void setPtaStaff(boolean ptaStaff) {
        this.ptaStaff = ptaStaff;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserpwd() {
        return userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
    public int getSalary() {
        return Salary;
    }
    public void setSalary(int salary) {
        Salary = salary;
    }
}
