package repositories;

import java.util.List;

import domain.HostBuilding; 
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IHostBuildingRepository {
        List<HostBuilding> findAll(Integer userID) throws ResourceNotFoundException;
        
        HostBuilding findById(Integer userID, Integer HostBuildingId) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String HostBuildingName, String BuildingDescription, Double Latitude, Double Longitude) throws BadRequestException;
        
        void update(Integer userID, Integer HostBuildingID, String HostBuildingName, String BuildingDescription, Double Latitude, Double Longitude, HostBuilding HostBuilding) throws BadRequestException;
        
        void removeById(Integer userID, Integer HostBuildingId);
}
