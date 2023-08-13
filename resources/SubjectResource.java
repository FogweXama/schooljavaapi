package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Subject;
import services.SubjectService;


@RestController
@RestMapping('cmeduc/school/{schoolID}/department/{departmentID}/subject')
public class SubjectResource {
    @Autowired

    SubjectService SubjectService;
    
    @GetMapping("")
    public ResponseEntity<List<Subject>> getAllSubjects(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Subject> Subjects=SubjectService.fetchAllCategories(userID);
        return new ResponseEntity<>(Subjects, HttpStatus.OK);
    }
    
    @GetMapping("/{subjectID}")
    public ResponseEntity<Subject> getSubjectById(HttpServletRequest request, @PathVariable("subjectID") Integer subjectID){
        int userID=(Integer)request.getAttribute("userID");
        Subject Subject=SubjectService.fetchSubjectById(userID, subjectID);
        return new ResponseEntity<>(Subject, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Subject> addSubject(HttpServletRequest request, @RequestBody Map<String, Object> SubjectMap){
        int userID=(Integer)request.getAttribute("userID");
        String subjectName=(String)SubjectMap.get("subjectName");
        String subjectCode=(String)SubjectMap.get("subjectCode");
        int departmentID=(Integer)SubjectMap.get("departmentID");
        int subjectCoefficient=(Integer)SubjectMap.get("subjectCoefficient");
        Subject Subject=SubjectService.addSubject(userID, subjectName, subjectCode, departmentID, subjectCoefficient);
        return new ResponseEntity<>(Subject, HttpStatus.CREATED);   
    } 
    @PutMapping("/{subjectID}")
    public ResponseEntity<Map<String, Boolean>> updateSubject(HttpServletRequest request,
                                                @PathVariable("subjectID") Integer subjectID,
                                                @RequestBody Map<String, Object> SubjectMap,
                                                @RequestBody Subject Subject){
        int userID=(Integer)request.getAttribute("userID");
        String subjectName=(String)SubjectMap.get("subjectName");
        String subjectCode=(String)SubjectMap.get("subjectCode");
        int departmentID=(Integer)SubjectMap.get("departmentID");
        int subjectCoefficient=(Integer)SubjectMap.get("subjectCoefficient");
        SubjectService.updateSubject(userID, subjectID, subjectName, subjectCode, departmentID, subjectCoefficient, Subject);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{subjectID}")
    public ResponseEntity<Map<String, Boolean>> deleteSubject(HttpServletRequest request, 
                                    @PathVariable("subjectID") Integer subjectID){
            int userID=(Integer)request.getAttribute("userID");
            SubjectService.removeSubjectWithAllTransactions(userID, subjectID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
