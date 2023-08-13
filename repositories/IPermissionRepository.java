package repositories;  

import java.util.List;

import domain.Permission;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IPermissionRepository {
        List<Permission> findAll(Integer userID) throws ResourceNotFoundException;
        
        Permission findById(Integer userID, Integer PermissionID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String PermissionDescription) throws BadRequestException;
        
        void update(Integer userID, Integer PermissionID, String PermissionDescription, Permission Permission) throws BadRequestException;
        
        void removeById(Integer userID, Integer PermissionID);
}
