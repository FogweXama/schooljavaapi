package services;

import java.util.List;

import domain.Department; 
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IDepartmentService {
    List<Department> fetchAllCategories(Integer userId);
    
    Department fetchDepartmentById(Integer userId, Integer DepartmentId) throws ResourceNotFoundException;
    
    Department addDepartment(Integer userID, String DepartmentName, Integer SchoolID) throws BadRequestException;
    
    void updateDepartment(Integer userID, Integer DepartmentID, String DepartmentName, Integer SchoolID, Department Department) throws BadRequestException;
    
    void removeDepartmentWithAllTransactions(Integer userId, Integer DepartmentId) throws ResourceNotFoundException;
}
