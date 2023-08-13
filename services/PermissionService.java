package services;

import java.util.List;

import domain.Permission;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.PermissionRepository;

@service
@Transactional
public class PermissionService implements IPermissionService{
@Autowired
PermissionRepository permissionRepository;
    @Override
    public List<Permission> fetchAllCategories(Integer userId) {
        permissionRepository.findAll(userId);
    }

    @Override
    public Permission fetchPermissionById(Integer userId, Integer PermissionId) throws ResourceNotFoundException {
        permissionRepository.findById(userId, PermissionId);
    }

    @Override
    public Permission addPermission(Integer userId, String description) throws BadRequestException {
        permissionRepository.create(userId, description);
    }

    @Override
    public void updatePermission(Integer userId, Integer PermissionId, String PermissionDescription, Permission Permission)
            throws BadRequestException {
        permissionRepository.update(userId, PermissionId, PermissionDescription, Permission);
    }

    @Override
    public void removePermissionWithAllTransactions(Integer userId, Integer PermissionId)
            throws ResourceNotFoundException {
        permissionRepository.removeById(userId, PermissionId);
    }
    
}
