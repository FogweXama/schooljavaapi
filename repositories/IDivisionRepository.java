package repositories;

import java.util.List;

import domain.LevelDivision; 
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IDivisionRepository {
        List<LevelDivision> findAll(Integer userID) throws ResourceNotFoundException;
        
        LevelDivision findById(Integer userID, Integer LevelDivisionId) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String LevelDivisionName, Integer LevelID) throws BadRequestException;
        
        void update(Integer userID, Integer LevelDivisionID, String LevelDivisionName, Integer LevelID, LevelDivision LevelDivision) throws BadRequestException;
        
        void removeById(Integer userID, Integer LevelDivisionId);
}
