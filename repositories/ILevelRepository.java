package repositories;

import java.util.List;

import domain.Level;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ILevelRepository {
        List<Level> findAll(Integer userID) throws ResourceNotFoundException;
        
        Level findById(Integer userID, Integer LevelID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String LevelName, Integer CycleID) throws BadRequestException;
        
        void update(Integer userID, Integer LevelID, String LevelName, Integer CycleID, Level Level) throws BadRequestException;
        
        void removeById(Integer userID, Integer LevelID);
}
