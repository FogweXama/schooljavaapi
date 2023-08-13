package repositories;

import java.util.List;

import domain.Cycle;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ICycleRepository {
        List<Cycle> findAll(Integer userID) throws ResourceNotFoundException;
        
        Cycle findById(Integer userID, Integer cycleId) throws ResourceNotFoundException;
        
        Integer create(Integer userID, Integer SectionID, String CycleName, String certificationtobeobtained) throws BadRequestException;
        
        void update(Integer userID, Integer cycleID, Integer SectionID, String CycleName, String certificationtobeobtained, Cycle cycle) throws BadRequestException;
        
        void removeById(Integer userID, Integer CycleId);
}
