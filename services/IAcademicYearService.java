package services;

import java.util.Date;
import java.util.List;

import domain.AcademicYear;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IAcademicYearService {
    List<AcademicYear> fetchAllAcademicYears(Integer userId);
    
    AcademicYear fetchAcademicYearById(Integer userId, Integer AcademicYearId) throws ResourceNotFoundException;
    
    AcademicYear addAcademicYear(Integer userID, String academicYearName, Date startDate, Date endDate) throws BadRequestException;
    
    void updateAcademicYear(Integer userID, Integer academicYearID, String academicYearName, Date startDate, Date endDate, AcademicYear AcademicYear) throws BadRequestException;
    
    void removeAcademicYear(Integer userId, Integer AcademicYearId) throws ResourceNotFoundException;
}