package services;

import java.util.List;

import domain.Student;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.StudentRepository;

@service
@Transactional
public class StudentService implements IStudentService{
@Autowired
StudentRepository studentRepository;
    @Override
    public List<Student> fetchAllCategories(Integer userId) {
        studentRepository.findAll(userId);
    }

    @Override
    public Student fetchStudentById(Integer userId, Integer StudentId) throws ResourceNotFoundException {
        studentRepository.findById(userId, StudentId);
    }

    @Override
    public Student addStudent(Integer userID, String StudentAdmissionNumber, Integer ReportNumber,
            String ClassIn, String ProductDescription) throws BadRequestException {
        studentRepository.create(userID,  StudentAdmissionNumber, ReportNumber, ClassIn, ProductDescription);
    }

    @Override
    public void updateStudent(Integer userID, Integer StudentID, String StudentAdmissionNumber, Integer ReportNumber,
            String ClassIn, String ProductDescription, Student Student) throws BadRequestException {
        studentRepository.update(userID, StudentID, StudentAdmissionNumber, ReportNumber, ClassIn, ProductDescription, Student);;
    }

    @Override
    public void removeStudentWithAllTransactions(Integer userId, Integer StudentId) throws ResourceNotFoundException {
        studentRepository.removeById(userId, StudentId);;
    }
    
}
