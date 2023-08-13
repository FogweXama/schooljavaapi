package services;

import java.util.List;

import domain.Exam;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IExamService {
    List<Exam> fetchAllCategories(Integer userId);
    
    Exam fetchExamById(Integer userId, Integer ExamId) throws ResourceNotFoundException;
    
    Exam addExam(Integer userID, String ExamName, Integer ExamCoefficient, String ExamDescription, Integer Tslid, Integer BuildingID, Integer PeriodID) throws BadRequestException;
    
    void updateExam(Integer userID, Integer ExamID, String ExamName, Integer ExamCoefficient, String ExamDescription, Integer Tslid, Integer BuildingID, Integer PeriodID, Exam Exam) throws BadRequestException;
    
    void removeExamWithAllTransactions(Integer userId, Integer ExamId) throws ResourceNotFoundException;
}
