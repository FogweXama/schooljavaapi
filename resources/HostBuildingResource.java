package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.HostBuilding;
import services.HostBuildingService;


@RestController
@RestMapping('cmeduc/school/{schoolID}/HostBuilding')
public class HostBuildingResource {
    @Autowired

    HostBuildingService HostBuildingService;
    
    @GetMapping("")
    public ResponseEntity<List<HostBuilding>> getAllHostBuildings(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<HostBuilding> HostBuildings=HostBuildingService.fetchAllCategories(userID);
        return new ResponseEntity<>(HostBuildings, HttpStatus.OK);
    }
    
    @GetMapping("/{hostBuildingID}")
    public ResponseEntity<HostBuilding> getHostBuildingById(HttpServletRequest request, @PathVariable("hostBuildingID") Integer hostBuildingID){
        int userID=(Integer)request.getAttribute("userID");
        HostBuilding HostBuilding=HostBuildingService.fetchHostBuildingById(userID, hostBuildingID);
        return new ResponseEntity<>(HostBuilding, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<HostBuilding> addHostBuilding(HttpServletRequest request, @RequestBody Map<String, Object> HostBuildingMap){
        int userID=(Integer)request.getAttribute("userID");
        String buildingDescription=(String)HostBuildingMap.get("buildingDescription");
        String HostBuildingName=(String)HostBuildingMap.get("HostBuildingName");
        Double latitude=(Double)HostBuildingMap.get("latitude");
        Double longitude=(Double)HostBuildingMap.get("longitude"); 
        HostBuilding HostBuilding=HostBuildingService.addHostBuilding(userID, HostBuildingName, buildingDescription, latitude, longitude);
        return new ResponseEntity<>(HostBuilding, HttpStatus.CREATED);   
    } 
    @PutMapping("/{hostBuildingID}")
    public ResponseEntity<Map<String, Boolean>> updateHostBuilding(HttpServletRequest request,
                                                @PathVariable("hostBuildingID") Integer hostBuildingID,
                                                @RequestBody Map<String, Object> HostBuildingMap,
                                                @RequestBody HostBuilding HostBuilding){
        int userID=(Integer)request.getAttribute("userID");
        String buildingDescription=(String)HostBuildingMap.get("buildingDescription");
        String HostBuildingName=(String)HostBuildingMap.get("HostBuildingName");
        Double latitude=(Double)HostBuildingMap.get("latitude");
        Double longitude=(Double)HostBuildingMap.get("longitude"); 
        HostBuildingService.updateHostBuilding(userID, hostBuildingID, buildingDescription, buildingDescription, latitude, longitude, HostBuilding);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{hostBuildingID}")
    public ResponseEntity<Map<String, Boolean>> deleteHostBuilding(HttpServletRequest request, 
                                    @PathVariable("hostBuildingID") Integer hostBuildingID){
            int userID=(Integer)request.getAttribute("userID");
            HostBuildingService.removeHostBuildingWithAllTransactions(userID, hostBuildingID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
