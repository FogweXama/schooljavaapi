package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Period;
import services.PeriodService;


@RestController
@RestMapping('cmeduc/period')
public class PeriodResource {
    @Autowired

    PeriodService PeriodService;
    
    @GetMapping("")
    public ResponseEntity<List<Period>> getAllPeriods(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Period> Periods=PeriodService.fetchAllCategories(userID);
        return new ResponseEntity<>(Periods, HttpStatus.OK);
    }
    
    @GetMapping("/{periodID}")
    public ResponseEntity<Period> getPeriodById(HttpServletRequest request, @PathVariable("periodID") Integer periodID){
        int userID=(Integer)request.getAttribute("userID");
        Period Period=PeriodService.fetchPeriodById(userID, periodID);
        return new ResponseEntity<>(Period, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Period> addPeriod(HttpServletRequest request, @RequestBody Map<String, Object> PeriodMap){
        int userID=(Integer)request.getAttribute("userID");
        String PeriodName=(String)PeriodMap.get("PeriodName");
        Date startTime=(Date)PeriodMap.get("startTime");
        Date endTime=(Date)PeriodMap.get("endTime"); 
        Period Period=PeriodService.addPeriod(userID, PeriodName, startTime, endTime);
        return new ResponseEntity<>(Period, HttpStatus.CREATED);   
    } 
    @PutMapping("/{periodID}")
    public ResponseEntity<Map<String, Boolean>> updatePeriod(HttpServletRequest request,
                                                @PathVariable("periodID") Integer periodID,
                                                @RequestBody Map<String, Object> PeriodMap,
                                                @RequestBody Period Period){
        int userID=(Integer)request.getAttribute("userID");
        String PeriodName=(String)PeriodMap.get("PeriodName");
        Date startTime=(Date)PeriodMap.get("startTime");
        Date endTime=(Date)PeriodMap.get("endTime"); 
        PeriodService.updatePeriod(userID, periodID, PeriodName, startTime, endTime, Period);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{periodID}")
    public ResponseEntity<Map<String, Boolean>> deletePeriod(HttpServletRequest request, 
                                    @PathVariable("periodID") Integer periodID){
            int userID=(Integer)request.getAttribute("userID");
            PeriodService.removePeriodWithAllTransactions(userID, periodID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
