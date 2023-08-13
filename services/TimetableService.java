package services;

import java.util.List;

import domain.Timetable;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.TimetableRepository;

@service
@Transactional
public class TimetableService implements ITimetableService {
@Autowired
TimetableRepository timetableRepository;
    @Override
    public List<Timetable> fetchAllCategories(Integer userId) {
        timetableRepository.findAll(userId);
    }

    @Override
    public Timetable fetchTimetableById(Integer userId, Integer TimetableId) throws ResourceNotFoundException {
        timetableRepository.findById(userId, TimetableId);
    }

    @Override
    public Timetable addTimetable(Integer userID, Integer BuildingID, Integer PeriodID,
            Integer TslID, Integer DayID) throws BadRequestException {
        timetableRepository.create(userID, BuildingID, PeriodID, TslID, DayID);
    }

    @Override
    public void updateTimetable(Integer userID, Integer TimetableID, Integer BuildingID, Integer PeriodID,
            Integer TslID, Integer DayID, Timetable Timetable) throws BadRequestException {
        timetableRepository.update(userID, TimetableID, BuildingID, PeriodID, TslID, DayID, Timetable);
    }

    @Override
    public void removeTimetableWithAllTransactions(Integer userId, Integer TimetableId)
            throws ResourceNotFoundException {
        timetableRepository.removeById(userId, TimetableId);;
    }
    
}
