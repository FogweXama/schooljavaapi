import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Assignment;
import services.AssignmentService;

@RestController
@RestMapping('cmeduc/assignment')
public class AssignmentResource{
    @Autowired

    AssignmentService assignmentService;
    
    @GetMapping("")
    public ResponseEntity<List<Assignment>> getAllassignments(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Assignment> assignments=assignmentService.fetchAllCategories(userID);
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }
    
    @GetMapping("/{assignmentID}")
    public ResponseEntity<Assignment> getAssignmentById(HttpServletRequest request, @PathVariable("assignmentID") Integer assignmentID){
        int userID=(Integer)request.getAttribute("userID");
        Assignment Assignment=assignmentService.fetchAssignmentById(userID, assignmentID);
        return new ResponseEntity<>(Assignment, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Assignment> addAssignment(HttpServletRequest request, @RequestBody Map<String, Object> assignmentMap){
        int userID=(Integer)request.getAttribute("userID");
        String taskGiven=(String)assignmentMap.get("taskGiven");
        Date dueDate=(Date)assignmentMap.get("dueDate");
        int tslid=(Integer)assignmentMap.get("tslid"); 
        int assignmentCoefficient=(Integer)assignmentMap.get("assignmentCoefficient"); 
        Assignment Assignment=assignmentService.addAssignment(userID, dueDate, taskGiven,tslid, assignmentCoefficient)
        return new ResponseEntity<>(Assignment, HttpStatus.CREATED);   
    } 
    @PutMapping("/{assignmentID}")
    public ResponseEntity<Map<String, Boolean>> updateAssignment(HttpServletRequest request,
                                                @PathVariable("assignmentID") Integer assignmentID,
                                                @RequestBody Map<String, Object> assignmentMap,
                                                @RequestBody Assignment Assignment){
        int userID=(Integer)request.getAttribute("userID");
        String taskGiven=(String)assignmentMap.get("taskGiven");
        Date dueDate=(Date)assignmentMap.get("dueDate");
        int tslid=(Integer)assignmentMap.get("tslid"); 
        int assignmentCoefficient=(Integer)assignmentMap.get("assignmentCoefficient"); 
        assignmentService.updateAssignment(userID, assignmentID, dueDate, taskGiven, tslid, assignmentCoefficient, Assignment);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{assignmentID}")
    public ResponseEntity<Map<String, Boolean>> deleteAssignment(HttpServletRequest request, 
                                    @PathVariable("assignmentID") Integer assignmentID){
            int userID=(Integer)request.getAttribute("userID");
            assignmentService.removeAssignment(userID, assignmentID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}