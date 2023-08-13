package repositories;

import java.util.List;

import domain.Student;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IStudentRepository {
        List<Student> findAll(Integer userID) throws ResourceNotFoundException;
        
        Student findById(Integer userID, Integer StudentID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String StudentAdmissionNumber, Integer ReportNumber, String ClassIn, String ProductDescription) throws BadRequestException;
        
        void update(Integer userID, Integer StudentID, String StudentAdmissionNumber, Integer ReportNumber, String ClassIn, String ProductDescription, Student Student) throws BadRequestException;
        
        void removeById(Integer userID, Integer StudentID);
}
