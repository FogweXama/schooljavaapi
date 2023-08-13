package services;

import java.util.Date;
import java.util.List;

import domain.Period;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.PeriodRepository;

@service
@Transactional
public class PeriodService implements IPeriodService{
@Autowired
PeriodRepository periodRepository;
    @Override
    public List<Period> fetchAllCategories(Integer userId) {
        periodRepository.findAll(userId);
    }

    @Override
    public Period fetchPeriodById(Integer userId, Integer PeriodId) throws ResourceNotFoundException {
        periodRepository.findById(userId, PeriodId);
    }

    @Override
    public Period addPeriod(Integer userID, String PeriodName, Date startTime, Date endTime)
            throws BadRequestException {
        periodRepository.create(userID, PeriodName, startTime, endTime);
    }

    @Override
    public void updatePeriod(Integer userID, Integer PeriodID, String PeriodName, Date startTime, Date endTime,
            Period Period) throws BadRequestException {
        periodRepository.update(userID, PeriodID, PeriodName, startTime, endTime, Period);;
    }

    @Override
    public void removePeriodWithAllTransactions(Integer userId, Integer PeriodId) throws ResourceNotFoundException {
        periodRepository.removeById(userId, PeriodId);
    }
    
}
