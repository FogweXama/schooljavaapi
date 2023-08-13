package services;

import java.util.List;

import domain.School;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.SchoolRepository;

@service
@Transactional
public class SchoolService implements ISchoolService{
@Autowired
SchoolRepository schoolRepository;
    @Override
    public List<School> fetchAllCategories(Integer userId) {
        schoolRepository.findAll(userId);
    }

    @Override
    public School fetchSchoolById(Integer userId, Integer SchoolId) throws ResourceNotFoundException {
        schoolRepository.findById(userId, SchoolId);
    }

    @Override
    public School addSchool(Integer userID, String SchoolName, String CenterNumber,
            String SchoolDescription) throws BadRequestException {
        schoolRepository.create(userID, SchoolName, CenterNumber, SchoolDescription);
    }

    @Override
    public void updateSchool(Integer userID, Integer SchoolID, String SchoolName, String CenterNumber,
            String SchoolDescription, School School) throws BadRequestException {
        schoolRepository.update(userID, SchoolID, SchoolName, CenterNumber, SchoolDescription, School);
    }

    @Override
    public void removeSchoolWithAllTransactions(Integer userId, Integer SchoolId) throws ResourceNotFoundException {
        schoolRepository.removeById(userId, SchoolId);
    }
    
}
