package services;

import java.util.Date;
import java.util.List;

import domain.Period;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IPeriodService {
    List<Period> fetchAllCategories(Integer userId);
    
    Period fetchPeriodById(Integer userId, Integer PeriodId) throws ResourceNotFoundException;
    
    Period addPeriod(Integer userID, String PeriodName, Date startTime, Date endTime) throws BadRequestException;
    
    void updatePeriod(Integer userID, Integer PeriodID, String PeriodName, Date startTime, Date endTime, Period Period) throws BadRequestException;
    
    void removePeriodWithAllTransactions(Integer userId, Integer PeriodId) throws ResourceNotFoundException;
}
