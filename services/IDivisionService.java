package services;

import java.util.List;

import domain.LevelDivision; 
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IDivisionService {
    List<LevelDivision> fetchAllCategories(Integer userId);
    
    LevelDivision fetchLevelDivisionById(Integer userId, Integer LevelDivisionId) throws ResourceNotFoundException;
    
    LevelDivision addLevelDivision(Integer userID, String LevelDivisionName, Integer LevelID) throws BadRequestException;
    
    void updateLevelDivision(Integer userID, Integer LevelDivisionID, String LevelDivisionName, Integer LevelID, LevelDivision LevelDivision) throws BadRequestException;
    
    void removeLevelDivisionWithAllTransactions(Integer userId, Integer LevelDivisionId) throws ResourceNotFoundException;
}
