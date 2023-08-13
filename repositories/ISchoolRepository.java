package repositories;

import java.util.List;

import domain.School;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ISchoolRepository {
List<School> findAll(Integer userID) throws ResourceNotFoundException;
        
        School findById(Integer userID, Integer SchoolId) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String SchoolName, String CenterNumber, String SchoolDescription) throws BadRequestException;
        
        void update(Integer userID, Integer SchoolID, String SchoolName, String CenterNumber, String SchoolDescription, School School) throws BadRequestException;
        
        void removeById(Integer userID, Integer SchoolId);
}
