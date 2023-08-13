package services;

import java.util.List;

import domain.Subject;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ISubjectService {
    List<Subject> fetchAllCategories(Integer userId);
    
    Subject fetchSubjectById(Integer userId, Integer SubjectId) throws ResourceNotFoundException;
    
    Subject addSubject(Integer userID, String SubjectName, String SubjectCode, Integer DepartmentID, Integer SubjectCoefficient) throws BadRequestException;
    
    void updateSubject(Integer userID, Integer SubjectID, String SubjectName, String SubjectCode, Integer DepartmentID, Integer SubjectCoefficient, Subject Subject) throws BadRequestException;
    
    void removeSubjectWithAllTransactions(Integer userId, Integer SubjectId) throws ResourceNotFoundException;
}
