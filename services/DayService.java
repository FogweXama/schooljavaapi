package services;

import java.util.List;

import domain.Day;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.DayRepository;

@service
@Transactional
public class DayService implements IDayService{
@Autowired
DayRepository dayRepository;
    @Override
    public List<Day> fetchAllCategories(Integer userId) {
        dayRepository.findAll(userId);
    }

    @Override
    public Day fetchDayById(Integer userId, Integer DayId) throws ResourceNotFoundException {
        dayRepository.findById(userId, DayId);
    }

    @Override
    public Day addDay(Integer userID, String DayName) throws BadRequestException {
        dayRepository.create(userID, DayName);
    }

    @Override
    public void updateDay(Integer userID, Integer DayID, String DayName, Day Day) throws BadRequestException {
        dayRepository.update(userID, DayID, DayName, Day);;
    }

    @Override
    public void removeDayWithAllTransactions(Integer userId, Integer DayId) throws ResourceNotFoundException {
        dayRepository.removeById(userId, DayId);
    }
}
