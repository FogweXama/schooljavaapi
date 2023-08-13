package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Timetable;
import services.TimetableService;


@RestController
@RestMapping('cmeduc/timetable')

public class TimetableResource {
    @Autowired

    TimetableService TimetableService;
    
    @GetMapping("")
    public ResponseEntity<List<Timetable>> getAllTimetables(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Timetable> Timetables=TimetableService.fetchAllCategories(userID);
        return new ResponseEntity<>(Timetables, HttpStatus.OK);
    }
    
    @GetMapping("/{TimetableID}")
    public ResponseEntity<Timetable> getTimetableById(HttpServletRequest request, @PathVariable("TimetableID") Integer TimetableID){
        int userID=(Integer)request.getAttribute("userID");
        Timetable Timetable=TimetableService.fetchTimetableById(userID, TimetableID);
        return new ResponseEntity<>(Timetable, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Timetable> addTimetable(HttpServletRequest request, @RequestBody Map<String, Object> TimetableMap){
        int userID=(Integer)request.getAttribute("userID");
        int buildingID=(Integer)request.getAttribute("buildingID");
        int periodID=(Integer)request.getAttribute("periodID");
        int tslid=(Integer)request.getAttribute("tslid");
        int dayID=(Integer)request.getAttribute("dayID");
        Timetable Timetable=TimetableService.addTimetable(userID, buildingID, periodID, tslid, dayID);
        return new ResponseEntity<>(Timetable, HttpStatus.CREATED);   
    } 
    @PutMapping("/{TimetableID}")
    public ResponseEntity<Map<String, Boolean>> updateTimetable(HttpServletRequest request,
                                                @PathVariable("TimetableID") Integer TimetableID,
                                                @RequestBody Map<String, Object> TimetableMap,
                                                @RequestBody Timetable Timetable){
        int userID=(Integer)request.getAttribute("userID");
        int buildingID=(Integer)request.getAttribute("buildingID");
        int periodID=(Integer)request.getAttribute("periodID");
        int tslid=(Integer)request.getAttribute("tslid");
        int dayID=(Integer)request.getAttribute("dayID");
        TimetableService.updateTimetable(userID, TimetableID, buildingID, periodID, tslid, dayID, Timetable);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{TimetableID}")
    public ResponseEntity<Map<String, Boolean>> deleteTimetable(HttpServletRequest request, 
                                    @PathVariable("TimetableID") Integer TimetableID){
            int userID=(Integer)request.getAttribute("userID");
            TimetableService.removeTimetable(userID, TimetableID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
