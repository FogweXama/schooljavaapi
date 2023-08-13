package repositories;

import java.util.Date;
import java.util.List;

import domain.Assignment; 
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IAssignmentRepository {
        List<Assignment> findAll(Integer userID) throws ResourceNotFoundException;
        
        Assignment findById(Integer userID, Integer AssignmentID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, Date DueDate, String TaskGiven, Integer tslid, Integer AssignmentCoefficient) throws BadRequestException;
        
        void update(Integer userID, Integer AssignmentID, Date DueDate, String TaskGiven, Integer tslid, Integer AssignmentCoefficient, Assignment Assignment) throws BadRequestException;
        
        void removeById(Integer userID, Integer AssignmentId);
}
