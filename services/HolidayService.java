package services;

import java.util.Date;
import java.util.List;

import domain.Holiday;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.HolidayRepository;

@service
@Transactional
public class HolidayService implements IHolidayService{
@Autowired
HolidayRepository holidayRepository;
    @Override
    public List<Holiday> fetchAllCategories(Integer userId) {
        holidayRepository.findAll(userId);
    }

    @Override
    public Holiday fetchHolidayById(Integer userId, Integer HolidayId) throws ResourceNotFoundException {
        holidayRepository.findById(userId, HolidayId);
    }

    @Override
    public Holiday addHoliday(Integer userID, String HolidayName, Date Start_Time, Date End_Time,
            Integer TermID) throws BadRequestException {
        holidayRepository.create(userID, HolidayName, Start_Time, End_Time, TermID);
    }

    @Override
    public void updateHoliday(Integer userID, Integer HolidayID, String HolidayName, Date Start_Time, Date End_Time,
            Integer TermID, Holiday Holiday) throws BadRequestException {
        holidayRepository.update(userID, HolidayID, HolidayName, Start_Time, End_Time, TermID, Holiday);
    }

    @Override
    public void removeHolidayWithAllTransactions(Integer userId, Integer HolidayId) throws ResourceNotFoundException {
        holidayRepository.removeById(userId, HolidayId);
    }
    
}
