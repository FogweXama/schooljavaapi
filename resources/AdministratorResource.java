package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Administrator;
import services.AdministratorService;


@RestController
@RestMapping("cmeduc/staff/{staffID}/administrator")
public class AdministratorResource {
    @Autowired

    AdministratorService AdministratorService;
    
    @GetMapping("")
    public ResponseEntity<List<Administrator>> getAllAdministrators(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Administrator> Administrators=AdministratorService.fetchAllCategories(userID);
        return new ResponseEntity<>(Administrators, HttpStatus.OK);
    }
    
    @GetMapping("/{administratorID}")
    public ResponseEntity<Administrator> getAdministratorById(HttpServletRequest request, @PathVariable("administratorID") Integer administratorID){
        int userID=(Integer)request.getAttribute("userID");
        Administrator Administrator=AdministratorService.fetchAdministratorById(userID, administratorID);
        return new ResponseEntity<>(Administrator, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Administrator> addAdministrator(HttpServletRequest request, @RequestBody Map<String, Object> AdministratorMap){
        int userID=(Integer)request.getAttribute("userID");
        int staffID=(Integer)request.getAttribute("staffID");
        int roleID=(Integer)request.getAttribute("roleID");
        String office=(String)AdministratorMap.get("office");
        String administratorName=(String)AdministratorMap.get("administratorName");
        Administrator Administrator=AdministratorService.addAdministrator(userID, staffID, administratorName, roleID, office);
        return new ResponseEntity<>(Administrator, HttpStatus.CREATED);   
    } 
    @PutMapping("/{administratorID}")
    public ResponseEntity<Map<String, Boolean>> updateAdministrator(HttpServletRequest request,
                                                @PathVariable("administratorID") Integer administratorID,
                                                @PathVariable("staffID") Integer staffID,
                                                @RequestBody Map<String, Object> AdministratorMap,
                                                @RequestBody Administrator Administrator){
        int userID=(Integer)request.getAttribute("userID");
        int roleID=(Integer)request.getAttribute("roleID");
        String administratorName=(String)AdministratorMap.get("administratorName");
        String office=(String)AdministratorMap.get("office");
        AdministratorService.updateAdministrator(userID, staffID, administratorID, administratorName, roleID, office, Administrator);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{administratorID}")
    public ResponseEntity<Map<String, Boolean>> deleteAdministrator(HttpServletRequest request, 
                                    @PathVariable("administratorID") Integer administratorID){
            int userID=(Integer)request.getAttribute("userID");
            AdministratorService.removeAdministrator(userID, administratorID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
