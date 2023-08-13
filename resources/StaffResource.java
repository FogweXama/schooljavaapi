package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Staff;
import services.StaffService;


@RestController
@RestMapping('cmeduc/person/{personID}/staff')
public class StaffResource {
    @Autowired

    StaffService StaffService;
    
    @GetMapping("")
    public ResponseEntity<List<Staff>> getAllStaffs(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Staff> Staffs=StaffService.fetchAllCategories(userID);
        return new ResponseEntity<>(Staffs, HttpStatus.OK);
    }
    
    @GetMapping("/{staffID}")
    public ResponseEntity<Staff> getStaffById(HttpServletRequest request, @PathVariable("staffID") Integer staffID){
        int userID=(Integer)request.getAttribute("userID");
        Staff Staff=StaffService.fetchStaffById(userID, staffID);
        return new ResponseEntity<>(Staff, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Staff> addStaff(HttpServletRequest request, @RequestBody Map<String, Object> StaffMap){
        int userID=(Integer)request.getAttribute("userID");
        int personID=(Integer)request.getAttribute("personID");
        String shift=(String)StaffMap.get("shift");
        String username=(String)StaffMap.get("username");
        String password=(String)StaffMap.get("password");
        Double salary=(Double)StaffMap.get("salary");
        String productdescription=(String)StaffMap.get("productdescription");
        Boolean ptastaff=(Boolean)StaffMap.get("ptastaff");  
        Staff Staff=StaffService.addStaff(userID, personID, shift, ptastaff, username, password, salary, productdescription);
        return new ResponseEntity<>(Staff, HttpStatus.CREATED);   
    } 
    @PutMapping("/{staffID}")
    public ResponseEntity<Map<String, Boolean>> updateStaff(HttpServletRequest request,
                                                @PathVariable("staffID") Integer staffID,
                                                @RequestBody Map<String, Object> StaffMap,
                                                @RequestBody Staff Staff){
        int userID=(Integer)request.getAttribute("userID");
        int personID=(Integer)request.getAttribute("personID");
        String shift=(String)StaffMap.get("shift");
        String username=(String)StaffMap.get("username");
        String password=(String)StaffMap.get("password");
        Double salary=(Double)StaffMap.get("salary");
        String productdescription=(String)StaffMap.get("productdescription");
        Boolean ptastaff=(Boolean)StaffMap.get("ptastaff"); 
        StaffService.updateStaff(userID, staffID, personID, shift, ptastaff, username, password, salary, productdescription, Staff);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{staffID}")
    public ResponseEntity<Map<String, Boolean>> deleteStaff(HttpServletRequest request, 
                                    @PathVariable("staffID") Integer staffID){
            int userID=(Integer)request.getAttribute("userID");
            StaffService.removeStaffWithAllTransactions(userID, staffID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
