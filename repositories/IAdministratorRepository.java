package repositories;

import java.util.List;

import domain.Administrator;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IAdministratorRepository {
        List<Administrator> findAll(Integer userID) throws ResourceNotFoundException;
        
        Administrator findById(Integer userID, Integer AdministratorId) throws ResourceNotFoundException;
        
        Integer create(Integer userID, Integer StaffID, String AdministratorName, Integer RoleID, String Office) throws BadRequestException;
        
        void update( Integer userID, Integer StaffID, Integer AdministratorID, String AdministratorName, Integer RoleID, String Office, Administrator Administrator) throws BadRequestException;
        
        void removeById(Integer userID, Integer AdministratorId);
}
