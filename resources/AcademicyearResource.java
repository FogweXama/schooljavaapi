package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List; 

import services.AcademicYearService;

import java.util.Map;

import domain.AcademicYear;

@RestController
@RestMapping('cmeduc/academicyear')
public class AcademicyearResource {
    @Autowired

    AcademicYearService academicYearService;
    
    @GetMapping("")
    public ResponseEntity<List<AcademicYear>> getAllacademicYears(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<AcademicYear> academicYears=academicYearService.fetchAllAcademicYears(userID);
        return new ResponseEntity<>(academicYears, HttpStatus.OK);
    }
    
    @GetMapping("/{academicYearID}")
    public ResponseEntity<AcademicYear> getAcademicYearById(HttpServletRequest request, @PathVariable("academicYearID") Integer academicYearID){
        int userID=(Integer)request.getAttribute("userID");
        AcademicYear AcademicYear=academicYearService.fetchAcademicYearById(userID, academicYearID);
        return new ResponseEntity<>(AcademicYear, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<AcademicYear> addAcademicYear(HttpServletRequest request, @RequestBody Map<String, Object> academicYearMap){
        int userID=(Integer)request.getAttribute("userID");
        String academicYearName=(String)academicYearMap.get("academicYearName");
        Date startDate=(Date)academicYearMap.get("startDate");
        Date endDate=(Date)academicYearMap.get("endDate"); 
        AcademicYear AcademicYear=academicYearService.addAcademicYear(userID, academicYearName, startDate, endDate);
        return new ResponseEntity<>(AcademicYear, HttpStatus.CREATED);   
    } 
    @PutMapping("/{academicYearID}")
    public ResponseEntity<Map<String, Boolean>> updateAcademicYear(HttpServletRequest request,
                                                @PathVariable("academicYearID") Integer academicYearID,
                                                @RequestBody Map<String, Object> academicYearMap,
                                                @RequestBody AcademicYear AcademicYear){
        int userID=(Integer)request.getAttribute("userID");
        String academicYearName=(String)academicYearMap.get("academicYearName");
        Date startDate=(Date)academicYearMap.get("startDate");
        Date endDate=(Date)academicYearMap.get("endDate"); 
        academicYearService.updateAcademicYear(userID, academicYearID, academicYearName, startDate, endDate, AcademicYear);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{academicYearID}")
    public ResponseEntity<Map<String, Boolean>> deleteAcademicYear(HttpServletRequest request, 
                                    @PathVariable("academicYearID") Integer academicYearID){
            int userID=(Integer)request.getAttribute("userID");
            academicYearService.removeAcademicYear(userID, academicYearID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
