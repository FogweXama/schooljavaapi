package services;

import java.util.List;

import domain.School;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ISchoolService {
    List<School> fetchAllCategories(Integer userId);
    
    School fetchSchoolById(Integer userId, Integer SchoolId) throws ResourceNotFoundException;
    
    School addSchool(Integer userID, String SchoolName, String CenterNumber, String SchoolDescription) throws BadRequestException;
    
    void updateSchool(Integer userID, Integer SchoolID, String SchoolName, String CenterNumber, String SchoolDescription, School School) throws BadRequestException;
    
    void removeSchoolWithAllTransactions(Integer userId, Integer SchoolId) throws ResourceNotFoundException;
}
