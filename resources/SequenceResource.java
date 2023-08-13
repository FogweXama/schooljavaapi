package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Sequence;
import services.SequenceService;


@RestController
@RestMapping('cmeduc/academicyear/{academicYearID}/term/{termID}')
public class SequenceResource {
    @Autowired

    SequenceService SequenceService;
    
    @GetMapping("")
    public ResponseEntity<List<Sequence>> getAllSequences(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Sequence> Sequences=SequenceService.fetchAllCategories(userID);
        return new ResponseEntity<>(Sequences, HttpStatus.OK);
    }
    
    @GetMapping("/{sequenceID}")
    public ResponseEntity<Sequence> getSequenceById(HttpServletRequest request, @PathVariable("sequenceID") Integer sequenceID){
        int userID=(Integer)request.getAttribute("userID");
        Sequence Sequence=SequenceService.fetchSequenceById(userID, sequenceID);
        return new ResponseEntity<>(Sequence, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Sequence> addSequence(HttpServletRequest request, @RequestBody Map<String, Object> SequenceMap){
        int userID=(Integer)request.getAttribute("userID");
        String SequenceName=(String)SequenceMap.get("SequenceName");
        int termID=(Integer)SequenceMap.get("termID");
        int coefficient=(Integer)SequenceMap.get("coefficient");
        Date startDate=(Date)SequenceMap.get("startDate");
        Date endDate=(Date)SequenceMap.get("endDate"); 
        Sequence Sequence=SequenceService.addSequence(userID, SequenceName, startDate, endDate, coefficient, termID);
        return new ResponseEntity<>(Sequence, HttpStatus.CREATED);   
    } 
    @PutMapping("/{sequenceID}")
    public ResponseEntity<Map<String, Boolean>> updateSequence(HttpServletRequest request,
                                                @PathVariable("sequenceID") Integer sequenceID,
                                                @RequestBody Map<String, Object> SequenceMap,
                                                @RequestBody Sequence Sequence){
        int userID=(Integer)request.getAttribute("userID");
        String SequenceName=(String)SequenceMap.get("SequenceName");
        int termID=(Integer)SequenceMap.get("termID");
        int coefficient=(Integer)SequenceMap.get("coefficient");
        Date startDate=(Date)SequenceMap.get("startDate");
        Date endDate=(Date)SequenceMap.get("endDate"); 
        SequenceService.updateSequence(userID, sequenceID, SequenceName, startDate, endDate, coefficient, termID, Sequence);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{sequenceID}")
    public ResponseEntity<Map<String, Boolean>> deleteSequence(HttpServletRequest request, 
                                    @PathVariable("sequenceID") Integer sequenceID){
            int userID=(Integer)request.getAttribute("userID");
            SequenceService.removeSequenceWithAllTransactions(userID, sequenceID);;
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
