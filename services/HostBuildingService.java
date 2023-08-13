package services;

import java.util.List;

import domain.HostBuilding;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.HostBuildingRepository;

@service
@Transactional
public class HostBuildingService implements IHostBuildingService {
@Autowired
HostBuildingRepository hostBuildingRepository;
    @Override
    public List<HostBuilding> fetchAllCategories(Integer userId) {
        hostBuildingRepository.findAll(userId);
    }

    @Override
    public HostBuilding fetchHostBuildingById(Integer userId, Integer HostBuildingId) throws ResourceNotFoundException {
        hostBuildingRepository.findById(userId, HostBuildingId);
    }

    @Override
    public HostBuilding addHostBuilding(Integer userID, String HostBuildingName,
            String BuildingDescription, Double Latitude, Double Longitude) throws BadRequestException {
        hostBuildingRepository.create(userID, HostBuildingName, BuildingDescription, Latitude, Longitude);
    }

    @Override
    public void updateHostBuilding(Integer userID, Integer HostBuildingID, String HostBuildingName,
            String BuildingDescription, Double Latitude, Double Longitude, HostBuilding HostBuilding)
            throws BadRequestException {
        hostBuildingRepository.update(userID, HostBuildingID, HostBuildingName, BuildingDescription, Latitude, Longitude, HostBuilding);;
    }

    @Override
    public void removeHostBuildingWithAllTransactions(Integer userId, Integer HostBuildingId)
            throws ResourceNotFoundException {
        hostBuildingRepository.removeById(userId, HostBuildingId);;
    }
    
}
