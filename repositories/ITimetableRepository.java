package repositories;

import java.util.List;

import domain.Timetable;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ITimetableRepository {
    List<Timetable> findAll(Integer userID) throws ResourceNotFoundException;

    Timetable findById(Integer userID, Integer TimetableID) throws ResourceNotFoundException;

    Integer create(Integer userID, Integer BuildingID, Integer PeriodID, Integer TslID,
            Integer DayID) throws BadRequestException;

    void update(Integer userID, Integer TimetableID, Integer BuildingID, Integer PeriodID, Integer TslID, Integer DayID,
            Timetable Timetable) throws BadRequestException;

    void removeById(Integer userID, Integer TimetableID);
}
