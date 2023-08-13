package services;

import java.util.List;

import domain.LevelDivision;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.DivisionRepository;

@service
@Transactional
public class DivisionService implements IDivisionService{
@Autowired
DivisionRepository divisionRepository;
    @Override
    public List<LevelDivision> fetchAllCategories(Integer userId) {
        divisionRepository.findAll(userId);
    }

    @Override
    public LevelDivision fetchLevelDivisionById(Integer userId, Integer LevelDivisionId)
            throws ResourceNotFoundException {
        divisionRepository.findById(userId, LevelDivisionId);
    }

    @Override
    public LevelDivision addLevelDivision(Integer userID, String LevelDivisionName,
            Integer LevelID) throws BadRequestException {
         divisionRepository.create(userID, LevelDivisionName, LevelID);
    }

    @Override
    public void updateLevelDivision(Integer userID, Integer LevelDivisionID, String LevelDivisionName, Integer LevelID,
            LevelDivision LevelDivision) throws BadRequestException {
        divisionRepository.update(userID, LevelDivisionID, LevelDivisionName, LevelID, LevelDivision);;
    }

    @Override
    public void removeLevelDivisionWithAllTransactions(Integer userId, Integer LevelDivisionId)
            throws ResourceNotFoundException {
        divisionRepository.removeById(userId, LevelDivisionId);;
    }
    
}
