package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Teacher;
import services.TeacherService;


@RestController
@RestMapping("cmeduc/person/{personID}/teacher")
public class TeacherResource {
    @Autowired

    TeacherService TeacherService;
    
    @GetMapping("")
    public ResponseEntity<List<Teacher>> getAllTeachers(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Teacher> Teachers=TeacherService.fetchAllCategories(userID);       
        return new ResponseEntity<>(Teachers, HttpStatus.OK);
    }
    
    @GetMapping("/{teacherID}")
    public ResponseEntity<Teacher> getTeacherById(HttpServletRequest request, @PathVariable("teacherID") Integer teacherID){
        int userID=(Integer)request.getAttribute("userID");
        Teacher Teacher=TeacherService.fetchTeacherById(userID, teacherID);
        return new ResponseEntity<>(Teacher, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Teacher> addTeacher(HttpServletRequest request, @RequestBody Map<String, Object> TeacherMap){
        int userID=(Integer)request.getAttribute("userID");
        int personID=(Integer)request.getAttribute("personID");
        String holderOf=(String)TeacherMap.get("holderOf");
        Teacher Teacher=TeacherService.addTeacher(userID, personID, holderOf);
        return new ResponseEntity<>(Teacher, HttpStatus.CREATED);   
    } 
    @PutMapping("/{teacherID}")
    public ResponseEntity<Map<String, Boolean>> updateTeacher(HttpServletRequest request,
                                                @PathVariable("teacherID") Integer teacherID,
                                                @RequestBody Map<String, Object> TeacherMap,
                                                @RequestBody Teacher Teacher){
        int userID=(Integer)request.getAttribute("userID");
        int personID=(Integer)request.getAttribute("personID");
        String holderOf=(String)TeacherMap.get("holderOf");
        TeacherService.updateTeacher(userID, teacherID, personID, holderOf, Teacher);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{teacherID}")
    public ResponseEntity<Map<String, Boolean>> deleteTeacher(HttpServletRequest request, 
                                    @PathVariable("teacherID") Integer teacherID){
            int userID=(Integer)request.getAttribute("userID");
            TeacherService.removeTeacherWithAllTransactions(userID, teacherID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
