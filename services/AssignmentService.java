package services;

import java.util.Date;
import java.util.List;

import domain.Assignment;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.AssignmentRepository;

@service
@Transactional
public class AssignmentService implements IAssignmentService{
@Autowired
AssignmentRepository assignmentRepository;
    @Override
    public List<Assignment> fetchAllCategories(Integer userId) {
        assignmentRepository.findAll(userId);
    }

    @Override
    public Assignment fetchAssignmentById(Integer userId, Integer AssignmentId) throws ResourceNotFoundException {
        assignmentRepository.findById(userId, AssignmentId);
    }

    @Override
    public Assignment addAssignment(Integer userID, Date DueDate, String TaskGiven, Integer tslid,
            Integer AssignmentCoefficient) throws BadRequestException {
        assignmentRepository.create(userID, DueDate, TaskGiven, tslid, AssignmentCoefficient);
    }

    @Override
    public void updateAssignment(Integer userID, Integer AssignmentID, Date DueDate, String TaskGiven, Integer tslid,
            Integer AssignmentCoefficient, Assignment Assignment) throws BadRequestException {
        assignmentRepository.update(userID, AssignmentID, DueDate, TaskGiven, tslid, AssignmentCoefficient, Assignment);;
    }

    @Override
    public void removeAssignment(Integer userId, Integer AssignmentId)
            throws ResourceNotFoundException {
        assignmentRepository.removeById(userId, AssignmentId);;
    }
    
}
