package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.School;
import services.SchoolService;


@RestController
@RestMapping('cmeduc/school')

public class SchoolResource {
    @Autowired

    SchoolService SchoolService;
    
    @GetMapping("")
    public ResponseEntity<List<School>> getAllSchools(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<School> Schools=SchoolService.fetchAllCategories(userID);
        return new ResponseEntity<>(Schools, HttpStatus.OK);
    }
    
    @GetMapping("/{schoolID}")
    public ResponseEntity<School> getSchoolById(HttpServletRequest request, @PathVariable("schoolID") Integer schoolID){
        int userID=(Integer)request.getAttribute("userID");
        School School=SchoolService.fetchSchoolById(userID, schoolID);
        return new ResponseEntity<>(School, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<School> addSchool(HttpServletRequest request, @RequestBody Map<String, Object> SchoolMap){
        int userID=(Integer)request.getAttribute("userID");
        String SchoolName=(String)SchoolMap.get("SchoolName");
        String centerNumber=(String)SchoolMap.get("centerNumber");
        String schoolDescription=(String)SchoolMap.get("schoolDescription"); 
        School School=SchoolService.addSchool(userID, SchoolName, centerNumber, schoolDescription);
        return new ResponseEntity<>(School, HttpStatus.CREATED);   
    } 
    @PutMapping("/{schoolID}")
    public ResponseEntity<Map<String, Boolean>> updateSchool(HttpServletRequest request,
                                                @PathVariable("schoolID") Integer schoolID,
                                                @RequestBody Map<String, Object> SchoolMap,
                                                @RequestBody School School){
        int userID=(Integer)request.getAttribute("userID");
        String SchoolName=(String)SchoolMap.get("SchoolName");
        String centerNumber=(String)SchoolMap.get("centerNumber");
        String schoolDescription=(String)SchoolMap.get("schoolDescription"); 
        SchoolService.updateSchool(userID, schoolID, SchoolName, centerNumber, schoolDescription, School);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{schoolID}")
    public ResponseEntity<Map<String, Boolean>> deleteSchool(HttpServletRequest request, 
                                    @PathVariable("schoolID") Integer schoolID){
            int userID=(Integer)request.getAttribute("userID");
            SchoolService.removeSchoolWithAllTransactions(userID, schoolID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
