package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Cycle;
import services.CycleService;


@RestController
@RestMapping('cmeduc/school/{schoolID}/section/{sectionID}/cycle')
public class CycleResource {
    @Autowired

    CycleService CycleService;
    
    @GetMapping("")
    public ResponseEntity<List<Cycle>> getAllCycles(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Cycle> Cycles=CycleService.fetchAllCycles(userID);
        return new ResponseEntity<>(Cycles, HttpStatus.OK);
    }
    
    @GetMapping("/{cycleID}")
    public ResponseEntity<Cycle> getCycleById(HttpServletRequest request, @PathVariable("cycleID") Integer cycleID){
        int userID=(Integer)request.getAttribute("userID");
        Cycle Cycle=CycleService.fetchCycleById(userID, cycleID);
        return new ResponseEntity<>(Cycle, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Cycle> addCycle(HttpServletRequest request, @RequestBody Map<String, Object> CycleMap){
        int userID=(Integer)request.getAttribute("userID");
        int sectionID=(Integer)request.getAttribute("sectionID");
        String cycleName=(String)CycleMap.get("cycleName");
        String certificateTobeobtained=(String)CycleMap.get("certificatetobeobtained");
        Cycle Cycle=CycleService.addCycle(userID, sectionID, cycleName, certificateTobeobtained);
        return new ResponseEntity<>(Cycle, HttpStatus.CREATED);   
    } 
    @PutMapping("/{cycleID}")
    public ResponseEntity<Map<String, Boolean>> updateCycle(HttpServletRequest request,
                                                @PathVariable("cycleID") Integer cycleID,
                                                @PathVariable("sectionID") Integer sectionID,
                                                @RequestBody Map<String, Object> CycleMap,
                                                @RequestBody Cycle Cycle){
        int userID=(Integer)request.getAttribute("userID");
        String cycleName=(String)CycleMap.get("cycleName");
        String certificateTobeobtained=(String)CycleMap.get("certificatetobeobtained");
        CycleService.updateCycle(userID, cycleID, sectionID, cycleName, certificateTobeobtained, Cycle);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{cycleID}")
    public ResponseEntity<Map<String, Boolean>> deleteCycle(HttpServletRequest request, 
                                    @PathVariable("cycleID") Integer cycleID){
            int userID=(Integer)request.getAttribute("userID");
            CycleService.removeCycle(userID, cycleID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
