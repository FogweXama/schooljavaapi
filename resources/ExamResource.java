package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Exam;
import services.ExamService;

@RestController
@RestMapping('cmeduc/exam')
public class ExamResource {
    @Autowired

    ExamService ExamService;
    
    @GetMapping("")
    public ResponseEntity<List<Exam>> getAllExams(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Exam> Exams=ExamService.fetchAllCategories(userID);
        return new ResponseEntity<>(Exams, HttpStatus.OK);
    }
    
    @GetMapping("/{examID}")
    public ResponseEntity<Exam> getExamById(HttpServletRequest request, @PathVariable("examID") Integer examID){
        int userID=(Integer)request.getAttribute("userID");
        Exam Exam=ExamService.fetchExamById(userID, examID);
        return new ResponseEntity<>(Exam, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Exam> addExam(HttpServletRequest request, @RequestBody Map<String, Object> ExamMap){
        int userID=(Integer)request.getAttribute("userID");
        String ExamName=(String)ExamMap.get("ExamName");
        int examCoefficient=(Integer)request.getAttribute("examCoefficient"); 
        String examDescription=(String)ExamMap.get("examDescription");
        int tslid=(Integer)request.getAttribute("tslid");
        int buildingID=(Integer)request.getAttribute("buildingID");
        int periodID=(Integer)request.getAttribute("periodID");
        Exam Exam=ExamService.addExam(userID, ExamName, examCoefficient, examDescription, tslid, buildingID, periodID);
        return new ResponseEntity<>(Exam, HttpStatus.CREATED);   
    } 
    @PutMapping("/{examID}")
    public ResponseEntity<Map<String, Boolean>> updateExam(HttpServletRequest request,
                                                @PathVariable("examID") Integer examID,
                                                @RequestBody Map<String, Object> ExamMap,
                                                @RequestBody Exam Exam){
        int userID=(Integer)request.getAttribute("userID");
        String ExamName=(String)ExamMap.get("ExamName");
        int examCoefficient=(Integer)request.getAttribute("examCoefficient"); 
        String examDescription=(String)ExamMap.get("examDescription");
        int tslid=(Integer)request.getAttribute("tslid");
        int buildingID=(Integer)request.getAttribute("buildingID");
        int periodID=(Integer)request.getAttribute("periodID");
        ExamService.updateExam(userID, examID, ExamName, examCoefficient, examDescription, tslid, buildingID, periodID, Exam);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{examID}")
    public ResponseEntity<Map<String, Boolean>> deleteExam(HttpServletRequest request, 
                                    @PathVariable("examID") Integer examID){
            int userID=(Integer)request.getAttribute("userID");
            ExamService.removeExamWithAllTransactions(userID, examID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
