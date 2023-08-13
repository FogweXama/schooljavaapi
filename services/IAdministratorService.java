package services;

import java.util.List;

import domain.Administrator; 
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IAdministratorService {
    List<Administrator> fetchAllCategories(Integer userId);
    
    Administrator fetchAdministratorById(Integer userId, Integer AdministratorId) throws ResourceNotFoundException;
    
    Administrator addAdministrator(Integer userID, Integer StaffID, String AdministratorName, Integer RoleID, String Office) throws BadRequestException;
    
    void updateAdministrator(Integer userID, Integer StaffID, Integer AdministratorID, String AdministratorName, Integer RoleID, String Office, Administrator Administrator) throws BadRequestException;
    
    void removeAdministrator(Integer userId, Integer AdministratorId) throws ResourceNotFoundException;
}
