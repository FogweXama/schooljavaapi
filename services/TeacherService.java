package services;

import java.util.List;

import domain.Teacher;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.TeacherRepository;

@service
@Transactional
public class TeacherService implements ITeacherService{
@Autowired
TeacherRepository teacherRepository;
    @Override
    public List<Teacher> fetchAllCategories(Integer userId) {
        teacherRepository.findAll(userId);
    }

    @Override
    public Teacher fetchTeacherById(Integer userId, Integer TeacherId) throws ResourceNotFoundException {
        teacherRepository.findById(userId, TeacherId);
    }

    @Override
    public Teacher addTeacher(Integer userID,  Integer PersonID, String HolderOf)
            throws BadRequestException {
        teacherRepository.create(userID, PersonID, HolderOf);
    }

    @Override
    public void updateTeacher(Integer userID, Integer TeacherID, Integer PersonID, String HolderOf, Teacher Teacher)
            throws BadRequestException {
        teacherRepository.update(userID, TeacherID, PersonID, HolderOf, Teacher);;
    }

    @Override
    public void removeTeacherWithAllTransactions(Integer userId, Integer TeacherId) throws ResourceNotFoundException {
        teacherRepository.removeById(userId, TeacherId);;
    }
    
}
