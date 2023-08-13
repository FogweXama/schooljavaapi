package repositories;

import java.util.List;

import domain.Subject;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ISubjectRepository {
        List<Subject> findAll(Integer userID) throws ResourceNotFoundException;
        
        Subject findById(Integer userID, Integer SubjectID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String SubjectName, String SubjectCode, Integer DepartmentID, Integer SubjectCoefficient) throws BadRequestException;
        
        void update(Integer userID, Integer SubjectID, String SubjectName, String SubjectCode, Integer DepartmentID, Integer SubjectCoefficient, Subject Subject) throws BadRequestException;
        
        void removeById(Integer userID, Integer SubjectID);
}
