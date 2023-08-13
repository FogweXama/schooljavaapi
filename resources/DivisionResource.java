package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.LevelDivision;
import services.DivisionService;


@RestController
@RestMapping('cmeduc/school/{schoolID}/section/{sectionID}/cycle/{cycleID}/level/{levelID}/LevelDivision')
public class DivisionResource {
    @Autowired

    DivisionService LevelDivisionService;
    
    @GetMapping("")
    public ResponseEntity<List<LevelDivision>> getAllLevelDivisions(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<LevelDivision> LevelDivisions=LevelDivisionService.fetchAllCategories(userID);
        return new ResponseEntity<>(LevelDivisions, HttpStatus.OK);
    }
    
    @GetMapping("/{levelDivisionID}")
    public ResponseEntity<LevelDivision> getLevelDivisionById(HttpServletRequest request, @PathVariable("levelDivisionID") Integer levelDivisionID){
        int userID=(Integer)request.getAttribute("userID");
        LevelDivision LevelDivision=LevelDivisionService.fetchLevelDivisionById(userID, levelDivisionID);
        return new ResponseEntity<>(LevelDivision, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<LevelDivision> addLevelDivision(HttpServletRequest request, @RequestBody Map<String, Object> LevelDivisionMap){
        int userID=(Integer)request.getAttribute("userID");
        String LevelDivisionName=(String)LevelDivisionMap.get("LevelDivisionName");
        Integer levelID=(Integer)LevelDivisionMap.get("levelID"); 
        LevelDivision LevelDivision=LevelDivisionService.addLevelDivision(userID, LevelDivisionName, levelID);
        return new ResponseEntity<>(LevelDivision, HttpStatus.CREATED);   
    } 
    @PutMapping("/{levelDivisionID}")
    public ResponseEntity<Map<String, Boolean>> updateLevelDivision(HttpServletRequest request,
                                                @PathVariable("levelDivisionID") Integer levelDivisionID,
                                                @RequestBody Map<String, Object> LevelDivisionMap,
                                                @RequestBody LevelDivision LevelDivision){
        int userID=(Integer)request.getAttribute("userID");
        String LevelDivisionName=(String)LevelDivisionMap.get("LevelDivisionName");
        Integer levelID=(Integer)LevelDivisionMap.get("levelID"); 
        LevelDivisionService.updateLevelDivision(userID, levelDivisionID, LevelDivisionName, levelID, LevelDivision);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{levelDivisionID}")
    public ResponseEntity<Map<String, Boolean>> deleteLevelDivision(HttpServletRequest request, 
                                    @PathVariable("levelDivisionID") Integer levelDivisionID){
            int userID=(Integer)request.getAttribute("userID");
            LevelDivisionService.removeLevelDivisionWithAllTransactions(userID, levelDivisionID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
