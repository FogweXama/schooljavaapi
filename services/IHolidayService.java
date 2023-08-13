package services;

import java.util.Date;
import java.util.List;

import domain.Holiday;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IHolidayService {
    List<Holiday> fetchAllCategories(Integer userId);
    
    Holiday fetchHolidayById(Integer userId, Integer HolidayId) throws ResourceNotFoundException;
    
    Holiday addHoliday(Integer userID, String HolidayName, Date Start_Time, Date End_Time, Integer TermID) throws BadRequestException;
    
    void updateHoliday(Integer userID, Integer HolidayID, String HolidayName, Date Start_Time, Date End_Time, Integer TermID, Holiday Holiday) throws BadRequestException;
    
    void removeHolidayWithAllTransactions(Integer userId, Integer HolidayId) throws ResourceNotFoundException;
}
