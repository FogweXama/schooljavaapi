package services;

import java.util.List;

import domain.Subject;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.SubjectRepository;

@service
@Transactional
public class SubjectService implements ISubjectService{
@Autowired
SubjectRepository subjectRepository;
    @Override
    public List<Subject> fetchAllCategories(Integer userId) {
        subjectRepository.findAll(userId);
    }

    @Override
    public Subject fetchSubjectById(Integer userId, Integer SubjectId) throws ResourceNotFoundException {
        subjectRepository.findById(userId, SubjectId);
    }

    @Override
    public Subject addSubject(Integer userID,  String SubjectName, String SubjectCode,
            Integer DepartmentID, Integer SubjectCoefficient) throws BadRequestException {
        subjectRepository.create(userID,  SubjectName, SubjectCode, DepartmentID, SubjectCoefficient);
    }

    @Override
    public void updateSubject(Integer userID, Integer SubjectID, String SubjectName, String SubjectCode,
            Integer DepartmentID, Integer SubjectCoefficient, Subject Subject) throws BadRequestException {
        subjectRepository.update(userID, SubjectID, SubjectName, SubjectCode, DepartmentID, SubjectCoefficient, Subject);;
    }

    @Override
    public void removeSubjectWithAllTransactions(Integer userId, Integer SubjectId) throws ResourceNotFoundException {
        subjectRepository.removeById(userId, SubjectId);;
    }
    
}
