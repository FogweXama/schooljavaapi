package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Department;
import services.DepartmentService;

@RestController
@RestMapping('cmeduc/school/{schoolID}/Department')
public class DepartmentResource {
    @Autowired

    DepartmentService DepartmentService;
    
    @GetMapping("")
    public ResponseEntity<List<Department>> getAllDepartments(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Department> Departments=DepartmentService.fetchAllCategories(userID);
        return new ResponseEntity<>(Departments, HttpStatus.OK);
    }
    
    @GetMapping("/{departmentID}")
    public ResponseEntity<Department> getDepartmentById(HttpServletRequest request, @PathVariable("departmentID") Integer departmentID){
        int userID=(Integer)request.getAttribute("userID");
        Department Department=DepartmentService.fetchDepartmentById(userID, departmentID);
        return new ResponseEntity<>(Department, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Department> addDepartment(HttpServletRequest request, @RequestBody Map<String, Object> DepartmentMap){
        int userID=(Integer)request.getAttribute("userID");
        String DepartmentName=(String)DepartmentMap.get("DepartmentName");
        int schoolID=(Integer)DepartmentMap.get("schoolID");
        Department Department=DepartmentService.addDepartment(userID, DepartmentName, schoolID);
        return new ResponseEntity<>(Department, HttpStatus.CREATED);   
    } 
    @PutMapping("/{departmentID}")
    public ResponseEntity<Map<String, Boolean>> updateDepartment(HttpServletRequest request,
                                                @PathVariable("schoolID") Integer schoolID,
                                                @RequestBody Map<String, Object> DepartmentMap,
                                                @RequestBody Department Department){
        int userID=(Integer)request.getAttribute("userID");
        String DepartmentName=(String)DepartmentMap.get("DepartmentName");
        int departmentID=(Integer)DepartmentMap.get("departmentID");
        DepartmentService.updateDepartment(userID, departmentID, DepartmentName, schoolID, Department);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{departmentID}")
    public ResponseEntity<Map<String, Boolean>> deleteDepartment(HttpServletRequest request, 
                                    @PathVariable("departmentID") Integer departmentID){
            Integer userID=(Integer)request.getAttribute("userID");
            DepartmentService.removeDepartmentWithAllTransactions(userID, departmentID);;
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
