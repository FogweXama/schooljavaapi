package domain;

public class Role {
    private int roleID;
    private String roleName;
    private int permissionID;
    private String roleDescription;
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public int getPermissionID() {
        return permissionID;
    }
    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }
    public String getRoleDescription() {
        return roleDescription;
    }
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    public Role(int roleID, String roleName, int permissionID, String roleDescription) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.permissionID = permissionID;
        this.roleDescription = roleDescription;
    }
}
