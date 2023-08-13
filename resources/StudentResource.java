package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Student;
import services.StudentService;


@RestController
@RestMapping('cmeduc/person/{personID}/student')
public class StudentResource {
    @Autowired

    StudentService StudentService;
    
    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Student> Students=StudentService.fetchAllCategories(userID);
        return new ResponseEntity<>(Students, HttpStatus.OK);
    }
    
    @GetMapping("/{studentID}")
    public ResponseEntity<Student> getStudentById(HttpServletRequest request, @PathVariable("studentID") Integer studentID){
        int userID=(Integer)request.getAttribute("userID");
        Student Student=StudentService.fetchStudentById(userID, studentID);
        return new ResponseEntity<>(Student, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Student> addStudent(HttpServletRequest request, @RequestBody Map<String, Object> StudentMap){
        int userID=(Integer)request.getAttribute("userID");
        int reportNumber=(Integer)StudentMap.get("reportNumber");
        String studentAdmissionNumber=(String)StudentMap.get("studentAdmissionNumber");
        String classIn=(String)StudentMap.get("classIn");
        String productDescription=(String)StudentMap.get("productDescription"); 
        Student Student=StudentService.addStudent(userID, studentAdmissionNumber, reportNumber, classIn, productDescription);
        return new ResponseEntity<>(Student, HttpStatus.CREATED);   
    } 
    @PutMapping("/{studentID}")
    public ResponseEntity<Map<String, Boolean>> updateStudent(HttpServletRequest request,
                                                @PathVariable("studentID") Integer studentID,
                                                @RequestBody Map<String, Object> StudentMap,
                                                @RequestBody Student Student){
        int userID=(Integer)request.getAttribute("userID");
        int reportNumber=(Integer)StudentMap.get("reportNumber");
        String studentAdmissionNumber=(String)StudentMap.get("studentAdmissionNumber");
        String classIn=(String)StudentMap.get("classIn");
        String productDescription=(String)StudentMap.get("productDescription"); 
        StudentService.updateStudent(userID, studentID, studentAdmissionNumber, reportNumber, classIn, productDescription, Student);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{studentID}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(HttpServletRequest request, 
                                    @PathVariable("studentID") Integer studentID){
            int userID=(Integer)request.getAttribute("userID");
            StudentService.removeStudentWithAllTransactions(userID, studentID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
