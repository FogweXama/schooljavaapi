package domain;

public class Permission {
    private int permissionID;
    private String permissionDescription;
    
    public Permission(int permissionID, String permissionDescription) {
        this.permissionID = permissionID;
        this.permissionDescription = permissionDescription;
    }
    public int getPermissionID() {
        return permissionID;
    }
    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }
    public String getPermissionDescription() {
        return permissionDescription;
    }
    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }
}
