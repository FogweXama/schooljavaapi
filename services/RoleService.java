package services;

import java.util.List;

import domain.Role;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.RoleRepository;

@service
@Transactional
public class RoleService implements IRoleService{
@Autowired
RoleRepository roleRepository;
    @Override
    public List<Role> fetchAllCategories(Integer userId) {
        roleRepository.findAll(userId);
    }

    @Override
    public Role fetchRoleById(Integer userId, Integer RoleId) throws ResourceNotFoundException {
        roleRepository.findById(userId, RoleId);
    }

    @Override
    public Role addRole(Integer userID, String RoleName, Integer PermissionID, String RoleDescription)
            throws BadRequestException {
        roleRepository.create(userID, RoleName, PermissionID, RoleDescription);
    }

    @Override
    public void updateRole(Integer userID, Integer RoleID, String RoleName, Integer PermissionID,
            String RoleDescription, Role Role) throws BadRequestException {
        roleRepository.update(userID, RoleID, RoleName, PermissionID, RoleDescription, Role);
    }

    @Override
    public void removeRoleWithAllTransactions(Integer userId, Integer RoleId) throws ResourceNotFoundException {
        roleRepository.removeById(userId, RoleId);
    }
    
}
