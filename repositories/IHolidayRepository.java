package repositories;

import java.util.Date;
import java.util.List;

import domain.Holiday;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;



public interface IHolidayRepository {
        List<Holiday> findAll(Integer userID) throws ResourceNotFoundException;
        
        Holiday findById(Integer userID, Integer HolidayID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String HolidayName, Date Start_Time, Date End_Time, Integer TermID) throws BadRequestException;
        
        void update(Integer userID, Integer HolidayID, String HolidayName, Date Start_Time, Date End_Time, Integer TermID, Holiday Holiday) throws BadRequestException;
        
        void removeById(Integer userID, Integer HolidayID);

}
