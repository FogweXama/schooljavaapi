package services;

import java.util.List;

import domain.Cycle;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ICycleService {
    List<Cycle> fetchAllCategories(Integer userId);
    
    Cycle fetchCycleById(Integer userId, Integer CycleId) throws ResourceNotFoundException;
    
    Cycle addCycle(Integer userID, Integer SectionID, String CycleName, String certificationtobeobtained) throws BadRequestException;
    
    void updateCycle(Integer userID, Integer cycleID, Integer SectionID, String CycleName, String certificationtobeobtained, Cycle Cycle) throws BadRequestException;
    
    void removeCycle(Integer userId, Integer CycleId) throws ResourceNotFoundException;
}
