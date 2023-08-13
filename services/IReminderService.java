package services;

import java.util.Date;
import java.util.List;

import domain.Reminder;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IReminderService {
    List<Reminder> fetchAllCategories(Integer userId);
    
    Reminder fetchReminderById(Integer userId, Integer ReminderId) throws ResourceNotFoundException;
    
    Reminder addReminder(Integer userID, Date DayOfClass, Date TimeOfClass, Byte HasBeenNotified) throws BadRequestException;
    
    void updateReminder(Integer userID, Integer ReminderID, Date DayOfClass, Date TimeOfClass, Byte HasBeenNotified, Reminder Reminder) throws BadRequestException;
    
    void removeReminderWithAllTransactions(Integer userId, Integer ReminderId) throws ResourceNotFoundException;
}
