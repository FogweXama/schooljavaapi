package services;

import java.util.List;

import domain.Teacher;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ITeacherService {
    List<Teacher> fetchAllCategories(Integer userId);
    
    Teacher fetchTeacherById(Integer userId, Integer TeacherId) throws ResourceNotFoundException;
    
    Teacher addTeacher(Integer userID, Integer PersonID, String HolderOf) throws BadRequestException;
    
    void updateTeacher(Integer userID, Integer TeacherID, Integer PersonID, String HolderOf, Teacher Teacher) throws BadRequestException;
    
    void removeTeacherWithAllTransactions(Integer userId, Integer TeacherId) throws ResourceNotFoundException;
}
