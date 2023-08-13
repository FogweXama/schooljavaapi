package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Role;
import services.RoleService;


@RestController
@RestMapping('cmeduc/permission/{permissionID}/role')
public class RoleResource {
    @Autowired

    RoleService RoleService;
    
    @GetMapping("")
    public ResponseEntity<List<Role>> getAllRoles(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Role> Roles=RoleService.fetchAllCategories(userID);
        return new ResponseEntity<>(Roles, HttpStatus.OK);
    }
    
    @GetMapping("/{roleID}")
    public ResponseEntity<Role> getRoleById(HttpServletRequest request, @PathVariable("roleID") Integer roleID){
        int userID=(Integer)request.getAttribute("userID");
        Role Role=RoleService.fetchRoleById(userID, roleID);
        return new ResponseEntity<>(Role, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Role> addRole(HttpServletRequest request, @RequestBody Map<String, Object> RoleMap){
        int userID=(Integer)request.getAttribute("userID");
        String RoleName=(String)RoleMap.get("RoleName"); 
        int permissionID=(Integer)request.getAttribute("permissionID");
        String RoleDescription=(String)RoleMap.get("RoleDescription"); 
        Role Role=RoleService.addRole(userID, RoleName, permissionID, RoleDescription)
        return new ResponseEntity<>(Role, HttpStatus.CREATED);   
    } 

    @PutMapping("/{roleID}")
    public ResponseEntity<Map<String, Boolean>> updateRole(HttpServletRequest request,
                                                @PathVariable("roleID") Integer roleID,
                                                @PathVariable("permissionID") Integer permissionID,
                                                @RequestBody Map<String, Object> RoleMap,
                                                @RequestBody Role Role){
        int userID=(Integer)request.getAttribute("userID");
        String RoleName=(String)RoleMap.get("RoleName");
        String RoleDescription=(String)RoleMap.get("RoleDescription");
        RoleService.updateRole(userID, roleID, RoleName, permissionID, RoleDescription, Role);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{roleID}")
    public ResponseEntity<Map<String, Boolean>> deleteRole(HttpServletRequest request, 
                                    @PathVariable("roleID") Integer roleID){
            int userID=(Integer)request.getAttribute("userID");
            RoleService.removeRoleWithAllTransactions(userID, roleID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
