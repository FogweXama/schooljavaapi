package repositories;

import java.util.List;

import domain.Exam;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IExamRepository {
        List<Exam> findAll(Integer userID) throws ResourceNotFoundException;
        
        Exam findById(Integer userID, Integer ExamID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String ExamName, Integer ExamCoefficient, String ExamDescription, Integer Tslid, Integer BuildingID, Integer PeriodID) throws BadRequestException;
        
        void update(Integer userID, Integer ExamID, String ExamName, Integer ExamCoefficient, String ExamDescription, Integer Tslid, Integer BuildingID, Integer PeriodID, Exam Exam) throws BadRequestException;
        
        void removeById(Integer userID, Integer ExamID);

}
