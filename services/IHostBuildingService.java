package services;

import java.util.List;

import domain.HostBuilding; 
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IHostBuildingService {
    List<HostBuilding> fetchAllCategories(Integer userId);
    
    HostBuilding fetchHostBuildingById(Integer userId, Integer HostBuildingId) throws ResourceNotFoundException;
    
    HostBuilding addHostBuilding(Integer userID, String HostBuildingName, String BuildingDescription, Double Latitude, Double Longitude) throws BadRequestException;
    
    void updateHostBuilding(Integer userID, Integer HostBuildingID, String HostBuildingName, String BuildingDescription, Double Latitude, Double Longitude, HostBuilding HostBuilding) throws BadRequestException;
    
    void removeHostBuildingWithAllTransactions(Integer userId, Integer HostBuildingId) throws ResourceNotFoundException;
}
