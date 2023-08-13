package services;

import java.util.List;

import domain.Level;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ILevelService {
    List<Level> fetchAllCategories(Integer userId);
    
    Level fetchLevelById(Integer userId, Integer LevelId) throws ResourceNotFoundException;
    
    Level addLevel(Integer userID, String LevelName, Integer CycleID) throws BadRequestException;
    
    void updateLevel(Integer userID, Integer LevelID, String LevelName, Integer CycleID, Level Level) throws BadRequestException;
    
    void removeLevelWithAllTransactions(Integer userId, Integer LevelId) throws ResourceNotFoundException;
}
