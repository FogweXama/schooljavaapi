package repositories;
 
import java.util.List;

import domain.Department;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IDepartmentRepository {
        List<Department> findAll(Integer userID) throws ResourceNotFoundException;
        
        Department findById(Integer userID, Integer DepartmentID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String DepartmentName, Integer SchoolID) throws BadRequestException;
        
        void update(Integer userID, Integer DepartmentID, String DepartmentName, Integer SchoolID, Department Department) throws BadRequestException;
        
        void removeById(Integer userID, Integer DepartmentId);
}
