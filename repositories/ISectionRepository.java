package repositories;
 
import java.util.List;

import domain.Section;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ISectionRepository {
        List<Section> findAll(Integer userID) throws ResourceNotFoundException;
        
        Section findById(Integer userID, Integer SectionID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String SectionName, Integer SchoolID) throws BadRequestException;
        
        void update(Integer userID, Integer SectionID, String SectionName, Integer SchoolID, Section Section) throws BadRequestException;
        
        void removeById(Integer userID, Integer SectionID);
}
