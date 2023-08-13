package repositories;

import java.util.Date;
import java.util.List;

import domain.Period;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IPeriodRepository {
        List<Period> findAll(Integer userID) throws ResourceNotFoundException;
        
        Period findById(Integer userID, Integer PeriodID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String PeriodName, Date startTime, Date endTime) throws BadRequestException;
        
        void update(Integer userID, Integer PeriodID, String PeriodName, Date startTime, Date endTime, Period Period) throws BadRequestException;
        
        void removeById(Integer userID, Integer PeriodID);
}
