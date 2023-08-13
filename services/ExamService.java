package services;

import java.util.List;

import domain.Exam;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.ExamRepository;

@service
@Transactional
public class ExamService implements IExamService{
@Autowired
ExamRepository examRepository;
    @Override
    public List<Exam> fetchAllCategories(Integer userId) {
        examRepository.findAll(userId);
    }

    @Override
    public Exam fetchExamById(Integer userId, Integer ExamId) throws ResourceNotFoundException {
        examRepository.findById(userId, ExamId);
    }

    @Override
    public Exam addExam(Integer userID, String ExamName, Integer ExamCoefficient,
            String ExamDescription, Integer Tslid, Integer BuildingID, Integer PeriodID) throws BadRequestException {
        examRepository.create(userID, ExamName, ExamCoefficient, ExamDescription, Tslid, BuildingID, PeriodID);
    }

    @Override
    public void updateExam(Integer userID, Integer ExamID, String ExamName, Integer ExamCoefficient,
            String ExamDescription, Integer Tslid, Integer BuildingID, Integer PeriodID, Exam Exam)
            throws BadRequestException {
        examRepository.update(userID, ExamID, ExamName, ExamCoefficient, ExamDescription, Tslid, BuildingID, PeriodID, Exam);;
    }

    @Override
    public void removeExamWithAllTransactions(Integer userId, Integer ExamId) throws ResourceNotFoundException {
        examRepository.removeById(userId, ExamId);;
    }
    
}
