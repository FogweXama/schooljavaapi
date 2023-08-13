package repositories;

import java.util.Date;
import java.util.List;

import domain.AcademicYear;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IAcademicYearRepository {
        List<AcademicYear> findAll(Integer userID) throws ResourceNotFoundException;
        
        AcademicYear findById(Integer userID, Integer academicyearId) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String academicYearName, Date startDate, Date endDate) throws BadRequestException;
        
        void update(Integer userID, Integer academicYearID, String academicYearName, Date startDate, Date endDate, AcademicYear academicyear) throws BadRequestException;
        
        void removeById(Integer userID, Integer academicyearId);
}
