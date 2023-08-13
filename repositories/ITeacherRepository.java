package repositories;

import java.util.List;

import domain.Teacher;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ITeacherRepository {
        List<Teacher> findAll(Integer userID) throws ResourceNotFoundException;
        
        Teacher findById(Integer userID, Integer TeacherID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, Integer PersonID, String HolderOf) throws BadRequestException;
        
        void update(Integer userID, Integer TeacherID, Integer PersonID, String HolderOf, Teacher Teacher) throws BadRequestException;
        
        void removeById(Integer userID, Integer TeacherID);
}
