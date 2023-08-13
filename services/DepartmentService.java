package services;

import java.util.List;

import domain.Department;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.DepartmentRepository;

@service
@Transactional
public class DepartmentService implements IDepartmentService{
@Autowired
DepartmentRepository departmentRepository;
    @Override
    public List<Department> fetchAllCategories(Integer userId) {
        departmentRepository.findAll(userId);
    }

    @Override
    public Department fetchDepartmentById(Integer userId, Integer DepartmentId) throws ResourceNotFoundException {
        departmentRepository.findById(userId, DepartmentId);
    }

    @Override
    public Department addDepartment(Integer userID, String DepartmentName, Integer SchoolID)
            throws BadRequestException {
        departmentRepository.create(userID, DepartmentName, SchoolID);
    }

    @Override
    public void updateDepartment(Integer userID, Integer DepartmentID, String DepartmentName, Integer SchoolID,
            Department Department) throws BadRequestException {
        departmentRepository.update(userID, DepartmentID, DepartmentName, SchoolID, Department);;
    }

    @Override
    public void removeDepartmentWithAllTransactions(Integer userId, Integer DepartmentId)
            throws ResourceNotFoundException {
        departmentRepository.removeById(userId, DepartmentId);;
    }
    
}
