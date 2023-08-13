package services;

import java.util.List;

import domain.Level;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.LevelRepository;

@service
@Transactional
public class LevelService implements ILevelService{
@Autowired
LevelRepository levelRepository;
    @Override
    public List<Level> fetchAllCategories(Integer userId) {
        levelRepository.findAll(userId);
    }

    @Override
    public Level fetchLevelById(Integer userId, Integer LevelId) throws ResourceNotFoundException {
        levelRepository.findById(userId, LevelId);
    }

    @Override
    public Level addLevel(Integer userID, String LevelName, Integer CycleID)
            throws BadRequestException {
        levelRepository.create(userID, LevelName, CycleID);
    }

    @Override
    public void updateLevel(Integer userID, Integer LevelID, String LevelName, Integer CycleID, Level Level)
            throws BadRequestException {
        levelRepository.update(userID, LevelID, LevelName, CycleID, Level);
    }

    @Override
    public void removeLevelWithAllTransactions(Integer userId, Integer LevelId) throws ResourceNotFoundException {
        levelRepository.removeById(userId, LevelId);;
    }
    
}
