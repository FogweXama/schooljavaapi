package services;

import java.util.List;

import domain.Student;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IStudentService {
    List<Student> fetchAllCategories(Integer userId);

    Student fetchStudentById(Integer userId, Integer StudentId) throws ResourceNotFoundException;

    Student addStudent(Integer userID, String StudentAdmissionNumber, Integer ReportNumber, String ClassIn, String ProductDescription) throws BadRequestException;

    void updateStudent(Integer userID, Integer StudentID, String StudentAdmissionNumber, Integer ReportNumber, String ClassIn, String ProductDescription, Student Student) throws BadRequestException;

    void removeStudentWithAllTransactions(Integer userId, Integer StudentId) throws ResourceNotFoundException;
}
