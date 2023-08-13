package repositories;
 
import java.util.List;

import domain.Role;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IRoleRepository {
        List<Role> findAll(Integer userID) throws ResourceNotFoundException;
        
        Role findById(Integer userID, Integer RoleID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String RoleName, Integer PermissionID, String RoleDescription) throws BadRequestException;
        
        void update(Integer userID, Integer RoleID, String RoleName, Integer PermissionID, String RoleDescription, Role Role) throws BadRequestException;
        
        void removeById(Integer userID, Integer RoleID);
}
