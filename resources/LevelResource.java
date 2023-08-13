package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Level;
import services.LevelService;


@RestController
@RestMapping("cmeduc/school/{schoolID}/section/{sectionID}/cycle/{cycleID}/level")
public class LevelResource {
    @Autowired

    LevelService LevelService;
    
    @GetMapping("")
    public ResponseEntity<List<Level>> getAllLevels(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Level> Levels=LevelService.fetchAllCategories(userID);
        return new ResponseEntity<>(Levels, HttpStatus.OK);
    }
    
    @GetMapping("/{levelID}")
    public ResponseEntity<Level> getLevelById(HttpServletRequest request, @PathVariable("levelID") Integer levelID){
        int userID=(Integer)request.getAttribute("userID");
        Level Level=LevelService.fetchLevelById(userID, levelID);
        return new ResponseEntity<>(Level, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Level> addLevel(HttpServletRequest request, @RequestBody Map<String, Object> LevelMap){
        int userID=(Integer)request.getAttribute("userID");
        String LevelName=(String)LevelMap.get("LevelName");
        Integer cycleID=(Integer)LevelMap.get("cycleID");
        Level Level=LevelService.addLevel(userID, LevelName, cycleID);
        return new ResponseEntity<>(Level, HttpStatus.CREATED);   
    } 
    @PutMapping("/{levelID}")
    public ResponseEntity<Map<String, Boolean>> updateLevel(HttpServletRequest request,
                                                @PathVariable("levelID") Integer levelID,
                                                @RequestBody Map<String, Object> LevelMap,
                                                @RequestBody Level Level){
        int userID=(Integer)request.getAttribute("userID");
        String LevelName=(String)LevelMap.get("LevelName");
        Integer cycleID=(Integer)LevelMap.get("cycleID");
        LevelService.updateLevel(userID, levelID, LevelName, cycleID, Level);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{levelID}")
    public ResponseEntity<Map<String, Boolean>> deleteLevel(HttpServletRequest request, 
                                    @PathVariable("levelID") Integer levelID){
            int userID=(Integer)request.getAttribute("userID");
            LevelService.removeLevelWithAllTransactions(userID, levelID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
