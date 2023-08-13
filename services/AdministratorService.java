package services;

import java.util.List;

import domain.Administrator;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.AdministratorRepository;

@service
@Transactional
public class AdministratorService implements IAdministratorService {
@Autowired
AdministratorRepository administratorRepository;
    @Override
    public List<Administrator> fetchAllCategories(Integer userId) {
        administratorRepository.findAll(userId);
    }

    @Override
    public Administrator fetchAdministratorById(Integer userId, Integer AdministratorId)
            throws ResourceNotFoundException {
        administratorRepository.findById(userId, AdministratorId);
    }

    @Override
    public Administrator addAdministrator(Integer userID, Integer StaffID, 
            String AdministratorName, Integer RoleID, String Office) throws BadRequestException {
        administratorRepository.create(userID, StaffID, AdministratorName, RoleID, Office);
    }

    @Override
    public void updateAdministrator(Integer userID, Integer StaffID, Integer AdministratorID, String AdministratorName,
            Integer RoleID, String Office, Administrator Administrator) throws BadRequestException {
        administratorRepository.update(userID, StaffID, AdministratorID, AdministratorName, RoleID, Office, Administrator);
    }

    @Override
    public void removeAdministrator(Integer userId, Integer AdministratorId)
            throws ResourceNotFoundException {
        administratorRepository.removeById(userId, AdministratorId);
    }
    
}
