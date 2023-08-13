package services;

import java.util.List;

import domain.Cycle;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.CycleRepository;

@service
@Transactional
public class CycleService implements ICycleService {
@Autowired
CycleRepository cycleRepository;
    @Override
    public List<Cycle> fetchAllCategories(Integer userId) {
        cycleRepository.findAll(userId);
    }

    @Override
    public Cycle fetchCycleById(Integer userId, Integer CycleId) throws ResourceNotFoundException {
        cycleRepository.findById(userId, CycleId);
    }

    @Override
    public Cycle addCycle(Integer userID, Integer SectionID, String CycleName,
            String certificationtobeobtained) throws BadRequestException {
        cycleRepository.create(userID,SectionID, CycleName, certificationtobeobtained);
    }

    @Override
    public void updateCycle(Integer userID, Integer cycleID, Integer SectionID, String CycleName,
            String certificationtobeobtained, Cycle Cycle) throws BadRequestException {
        cycleRepository.update(userID, cycleID, SectionID, CycleName, certificationtobeobtained, Cycle);;
    }

    @Override
    public void removeCycle(Integer userId, Integer CycleId) throws ResourceNotFoundException {
        cycleRepository.removeById(userId, CycleId);;
    }
    
}
