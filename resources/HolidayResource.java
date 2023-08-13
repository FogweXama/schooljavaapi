package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Holiday;
import services.HolidayService;


@RestController
@RestMapping("cmeduc/academicyear/{academicyearID}/term/{termID}/holiday")
public class HolidayResource {
    @Autowired

    HolidayService HolidayService;
    
    @GetMapping("")
    public ResponseEntity<List<Holiday>> getAllHolidays(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Holiday> Holidays=HolidayService.fetchAllCategories(userID);
        return new ResponseEntity<>(Holidays, HttpStatus.OK);
    }
    
    @GetMapping("/{holidayID}")
    public ResponseEntity<Holiday> getHolidayById(HttpServletRequest request, @PathVariable("holidayID") Integer holidayID){
        int userID=(Integer)request.getAttribute("userID");
        Holiday Holiday=HolidayService.fetchHolidayById(userID, holidayID);
        return new ResponseEntity<>(Holiday, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Holiday> addHoliday(HttpServletRequest request, @RequestBody Map<String, Object> HolidayMap){
        int userID=(Integer)request.getAttribute("userID");
        String HolidayName=(String)HolidayMap.get("HolidayName");
        Date startDate=(Date)HolidayMap.get("start_time");
        Date endDate=(Date)HolidayMap.get("end_time"); 
        int termID=(Integer)HolidayMap.get("termID"); 
        Holiday Holiday=HolidayService.addHoliday(userID, HolidayName, startDate, endDate, termID);
        return new ResponseEntity<>(Holiday, HttpStatus.CREATED);   
    } 

    @PutMapping("/{holidayID}")
    public ResponseEntity<Map<String, Boolean>> updateHoliday(HttpServletRequest request,
                                                @PathVariable("holidayID") Integer holidayID,
                                                @RequestBody Map<String, Object> HolidayMap,
                                                @RequestBody Holiday Holiday){
        int userID=(Integer)request.getAttribute("userID");
        String HolidayName=(String)HolidayMap.get("HolidayName");
        Date startDate=(Date)HolidayMap.get("start_time");
        Date endDate=(Date)HolidayMap.get("end_time"); 
        int termID=(Integer)HolidayMap.get("termID");
        HolidayService.updateHoliday(userID, holidayID, HolidayName, startDate, endDate, termID, Holiday);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{holidayID}")
    public ResponseEntity<Map<String, Boolean>> deleteHoliday(HttpServletRequest request, 
                                    @PathVariable("holidayID") Integer holidayID){
            int userID=(Integer)request.getAttribute("userID");
            HolidayService.removeHolidayWithAllTransactions(userID, holidayID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
