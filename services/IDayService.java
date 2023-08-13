package services;

import java.util.List;

import domain.Day;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IDayService {
    List<Day> fetchAllCategories(Integer userId);
    
    Day fetchDayById(Integer userId, Integer DayId) throws ResourceNotFoundException;
    
    Day addDay(Integer userID, String DayName) throws BadRequestException;
    
    void updateDay(Integer userID, Integer DayID, String DayName, Day Day) throws BadRequestException;
    
    void removeDayWithAllTransactions(Integer userId, Integer DayId) throws ResourceNotFoundException;
}
