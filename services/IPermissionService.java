package services;

import java.util.List;

import domain.Permission;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IPermissionService {
    List<Permission> fetchAllCategories(Integer userId);
    
    Permission fetchPermissionById(Integer userId, Integer PermissionId) throws ResourceNotFoundException;
    
    Permission addPermission(Integer userId, String description) throws BadRequestException;
    
    void updatePermission(Integer userId, Integer PermissionId, String PermissionDescription, Permission Permission) throws BadRequestException;
    
    void removePermissionWithAllTransactions(Integer userId, Integer PermissionId) throws ResourceNotFoundException;
}
