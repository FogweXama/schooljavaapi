package services;

import java.util.Date;
import java.util.List;

import domain.Reminder;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.ReminderRepository;

@service
@Transactional
public class ReminderService implements IReminderService{
@Autowired
ReminderRepository reminderRepository;
    @Override
    public List<Reminder> fetchAllCategories(Integer userId) {
        reminderRepository.findAll(userId);
    }

    @Override
    public Reminder fetchReminderById(Integer userId, Integer ReminderId) throws ResourceNotFoundException {
        reminderRepository.findById(userId, ReminderId);
    }

    @Override
    public Reminder addReminder(Integer userID, Date DayOfClass, Date TimeOfClass,
            Byte HasBeenNotified) throws BadRequestException {
        reminderRepository.create(userID, DayOfClass, TimeOfClass, HasBeenNotified);
    }

    @Override
    public void updateReminder(Integer userID, Integer ReminderID, Date DayOfClass, Date TimeOfClass,
            Byte HasBeenNotified, Reminder Reminder) throws BadRequestException {
        reminderRepository.update(userID, ReminderID, DayOfClass, TimeOfClass, HasBeenNotified, Reminder);;
    }

    @Override
    public void removeReminderWithAllTransactions(Integer userId, Integer ReminderId) throws ResourceNotFoundException {
        reminderRepository.removeById(userId, ReminderId);;
    }
    
}
