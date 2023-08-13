package services;

import java.util.List;

import domain.Role;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IRoleService {
    List<Role> fetchAllCategories(Integer userId);
    
    Role fetchRoleById(Integer userId, Integer RoleId) throws ResourceNotFoundException;
    
    Role addRole(Integer userID, String RoleName, Integer PermissionID, String RoleDescription) throws BadRequestException;
    
    void updateRole(Integer userID, Integer RoleID, String RoleName, Integer PermissionID, String RoleDescription, Role Role) throws BadRequestException;
    
    void removeRoleWithAllTransactions(Integer userId, Integer RoleId) throws ResourceNotFoundException;
}
