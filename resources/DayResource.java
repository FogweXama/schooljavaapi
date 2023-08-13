package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Day;
import services.DayService;

@RestController
@RestMapping('cmeduc/Day')
public class DayResource {
    @Autowired

    DayService DayService;
    
    @GetMapping("")
    public ResponseEntity<List<Day>> getAllDays(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Day> Days=DayService.fetchAllDays(userID);
        return new ResponseEntity<>(Days, HttpStatus.OK);
    }
    
    @GetMapping("/{dayID}")
    public ResponseEntity<Day> getDayById(HttpServletRequest request, @PathVariable("dayID") Integer dayID){
        int userID=(Integer)request.getAttribute("userID");
        Day Day=DayService.fetchDayById(userID, dayID);
        return new ResponseEntity<>(Day, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Day> addDay(HttpServletRequest request, @RequestBody Map<String, Object> DayMap){
        int userID=(Integer)request.getAttribute("userID");
        String DayName=(String)DayMap.get("DayName");
        Day Day=DayService.addDay(userID, DayName);
        return new ResponseEntity<>(Day, HttpStatus.CREATED);   
    } 
    @PutMapping("/{dayID}")
    public ResponseEntity<Map<String, Boolean>> updateDay(HttpServletRequest request,
                                                @PathVariable("dayID") Integer dayID,
                                                @RequestBody Map<String, Object> DayMap,
                                                @RequestBody Day Day){
        int userID=(Integer)request.getAttribute("userID");
        String DayName=(String)DayMap.get("DayName");
        DayService.updateDay(userID, dayID, DayName, Day);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{dayID}")
    public ResponseEntity<Map<String, Boolean>> deleteDay(HttpServletRequest request, 
                                    @PathVariable("dayID") Integer dayID){
            int userID=(Integer)request.getAttribute("userID");
            DayService.removeDayWithAllTransactions(userID, dayID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
