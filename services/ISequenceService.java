package services;

import java.util.Date;
import java.util.List;

import domain.Sequence;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ISequenceService {
    List<Sequence> fetchAllCategories(Integer userId);
    
    Sequence fetchSequenceById(Integer userId, Integer SequenceId) throws ResourceNotFoundException;
    
    Sequence addSequence(Integer userID, String SequenceName, Date startDate, Date endDate, Integer Coefficient, Integer TermID) throws BadRequestException;
    
    void updateSequence(Integer userID, Integer SequenceID, String SequenceName, Date startDate, Date endDate, Integer Coefficient, Integer TermID, Sequence Sequence) throws BadRequestException;
    
    void removeSequenceWithAllTransactions(Integer userId, Integer SequenceId) throws ResourceNotFoundException;
}
