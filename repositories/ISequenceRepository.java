package repositories;

import java.util.Date;
import java.util.List;

import domain.Sequence;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ISequenceRepository {
        List<Sequence> findAll(Integer userID) throws ResourceNotFoundException;
        
        Sequence findById(Integer userID, Integer SequenceID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String SequenceName, Date startDate, Date endDate, Integer Coefficient, Integer TermID) throws BadRequestException;
        
        void update(Integer userID, Integer SequenceID, String SequenceName, Date startDate, Date endDate, Integer Coefficient, Integer TermID, Sequence Sequence) throws BadRequestException;
        
        void removeById(Integer userID, Integer SequenceID);
}
