package services;

import java.util.List;

import domain.Section;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.SectionRepository;

@service
@Transactional
public class SectionService implements ISectionService{
@Autowired
SectionRepository sectionRepository;
    @Override
    public List<Section> fetchAllCategories(Integer userId) {
        sectionRepository.findAll(userId);
    }

    @Override
    public Section fetchSectionById(Integer userId, Integer SectionId) throws ResourceNotFoundException {
        sectionRepository.findById(userId, SectionId);
    }

    @Override
    public Section addSection(Integer userID, String SectionName, Integer SchoolID)
            throws BadRequestException {
        sectionRepository.create(userID,  SectionName, SchoolID);
    }

    @Override
    public void updateSection(Integer userID, Integer SectionID, String SectionName, Integer SchoolID, Section Section)
            throws BadRequestException {
        sectionRepository.update(userID, SectionID, SectionName, SchoolID, Section);;
    }

    @Override
    public void removeSectionWithAllTransactions(Integer userId, Integer SectionId) throws ResourceNotFoundException {
        sectionRepository.removeById(userId, SectionId);;
    }
    
}
