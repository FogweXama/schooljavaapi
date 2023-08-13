package services;

import java.util.Date;
import java.util.List;
import java.util.Locale.Category;

import domain.AcademicYear;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.AcademicYearRepository;

@service
@Transactional
public class AcademicYearService implements IAcademicYearService {
    @Autowired
    AcademicYearRepository academincYearRepository;

    @Override
    public List<AcademicYear> fetchAllAcademicYears(Integer userId) {
        academincYearRepository.findAll(userId);
    }

    @Override
    public AcademicYear fetchAcademicYearById(Integer userId, Integer AcademicYearId) throws ResourceNotFoundException {
        academincYearRepository.findById(userId, AcademicYearId);
    }

    @Override
    public AcademicYear addAcademicYear(Integer userID, String academicYearName, Date startDate,
            Date endDate) throws BadRequestException {
        academincYearRepository.create(userID, academicYearName, startDate, endDate);
    }

    @Override
    public void updateAcademicYear(Integer userID, Integer academicYearID, String academicYearName, Date startDate,
            Date endDate, AcademicYear AcademicYear) throws BadRequestException {
        academincYearRepository.update(userID, academicYearID, academicYearName, startDate, endDate, AcademicYear);;
    }

    @Override
    public void removeAcademicYear(Integer userId, Integer AcademicYearId)
            throws ResourceNotFoundException {
        academincYearRepository.removeById(userId, AcademicYearId);
    }
    
    
    
}
