package repositories;

import java.util.List;

import domain.Day;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IDayRepository {
        List<Day> findAll(Integer userID) throws ResourceNotFoundException;
        
        Day findById(Integer userID, Integer DayId) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String DayName) throws BadRequestException;
        
        void update(Integer userID, Integer DayID, String DayName, Day Day) throws BadRequestException;
        
        void removeById(Integer userID, Integer DayId);
}
