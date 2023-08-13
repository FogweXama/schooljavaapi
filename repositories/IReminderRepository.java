package repositories;

import java.util.Date;
import java.util.List;

import domain.Reminder;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IReminderRepository {
        List<Reminder> findAll(Integer userID) throws ResourceNotFoundException;
        
        Reminder findById(Integer userID, Integer ReminderID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, Date DayOfClass, Date TimeOfClass, Byte HasBeenNotified) throws BadRequestException;
        
        void update(Integer userID, Integer ReminderID, Date DayOfClass, Date TimeOfClass, Byte HasBeenNotified, Reminder Reminder) throws BadRequestException;
        
        void removeById(Integer userID, Integer ReminderID);
}
