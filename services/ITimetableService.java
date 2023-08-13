package services;

import java.util.List;

import domain.Timetable;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ITimetableService {
    List<Timetable> fetchAllCategories(Integer userId);
    
    Timetable fetchTimetableById(Integer userId, Integer TimetableId) throws ResourceNotFoundException;
    
    Timetable addTimetable(Integer userID, Integer BuildingID, Integer PeriodID, Integer TslID, Integer DayID
            ) throws BadRequestException;
    
    void updateTimetable(Integer userID, Integer TimetableID, Integer BuildingID, Integer PeriodID, Integer TslID, Integer DayID,
            Timetable Timetable) throws BadRequestException;
    
    void removeTimetableWithAllTransactions(Integer userId, Integer TimetableId) throws ResourceNotFoundException;
}
