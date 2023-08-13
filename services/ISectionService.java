package services;

import java.util.List;

import domain.Section;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ISectionService {
    List<Section> fetchAllCategories(Integer userId);
    
    Section fetchSectionById(Integer userId, Integer SectionId) throws ResourceNotFoundException;
    
    Section addSection(Integer userID, String SectionName, Integer SchoolID) throws BadRequestException;
    
    void updateSection(Integer userID, Integer SectionID, String SectionName, Integer SchoolID, Section Section) throws BadRequestException;
    
    void removeSectionWithAllTransactions(Integer userId, Integer SectionId) throws ResourceNotFoundException;
}
