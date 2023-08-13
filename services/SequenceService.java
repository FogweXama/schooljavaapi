package services;

import java.util.Date;
import java.util.List;

import domain.Sequence;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.SequenceRepository;

@service
@Transactional
public class SequenceService  implements ISequenceService{
@Autowired
SequenceRepository sequenceRepository;
    @Override
    public List<Sequence> fetchAllCategories(Integer userId) {
        sequenceRepository.findAll(userId);
    }

    @Override
    public Sequence fetchSequenceById(Integer userId, Integer SequenceId) throws ResourceNotFoundException {
        sequenceRepository.findById(userId, SequenceId);
    }

    @Override
    public Sequence addSequence(Integer userID, String SequenceName, Date startDate, Date endDate,
            Integer Coefficient, Integer TermID) throws BadRequestException {
        sequenceRepository.create(userID,  SequenceName, startDate, endDate, Coefficient, TermID);
    }

    @Override
    public void updateSequence(Integer userID, Integer SequenceID, String SequenceName, Date startDate, Date endDate,
            Integer Coefficient, Integer TermID, Sequence Sequence) throws BadRequestException {
        sequenceRepository.update(userID, SequenceID, SequenceName, startDate, endDate, Coefficient, TermID, Sequence);
    }

    @Override
    public void removeSequenceWithAllTransactions(Integer userId, Integer SequenceId) throws ResourceNotFoundException {
        sequenceRepository.removeById(userId, SequenceId);
    }
    
}
