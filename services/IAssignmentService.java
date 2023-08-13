package services;

import java.util.Date;
import java.util.List;

import domain.Assignment;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IAssignmentService {
    List<Assignment> fetchAllCategories(Integer userId);
    
    Assignment fetchAssignmentById(Integer userId, Integer AssignmentId) throws ResourceNotFoundException;
    
    Assignment addAssignment(Integer userID, Date DueDate, String TaskGiven, Integer tslid, Integer AssignmentCoefficient) throws BadRequestException;
    
    void updateAssignment(Integer userID, Integer AssignmentID, Date DueDate, String TaskGiven, Integer tslid, Integer AssignmentCoefficient, Assignment Assignment) throws BadRequestException;
    
    void removeAssignment(Integer userId, Integer AssignmentId) throws ResourceNotFoundException;
}
