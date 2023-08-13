package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Permission;
import services.PermissionService;


@RestController
@RestMapping('cmeduc/permission')
public class PermissionResource {
    @Autowired

    PermissionService PermissionService;
    
    @GetMapping("")
    public ResponseEntity<List<Permission>> getAllPermissions(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Permission> Permissions=PermissionService.fetchAllCategories(userID);
        return new ResponseEntity<>(Permissions, HttpStatus.OK);
    }
    
    @GetMapping("/{permissionID}")
    public ResponseEntity<Permission> getPermissionById(HttpServletRequest request, @PathVariable("permissionID") Integer permissionID){
        int userID=(Integer)request.getAttribute("userID");
        Permission Permission=PermissionService.fetchPermissionById(userID, permissionID);
        return new ResponseEntity<>(Permission, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Permission> addPermission(HttpServletRequest request, @RequestBody Map<String, Object> PermissionMap){
        int userID=(Integer)request.getAttribute("userID");
        String permissionDescription=(String)PermissionMap.get("PermissionDescripton");
        Permission Permission=PermissionService.addPermission(userID, permissionDescription);
        return new ResponseEntity<>(Permission, HttpStatus.CREATED);   
    } 
    @PutMapping("/{permissionID}")
    public ResponseEntity<Map<String, Boolean>> updatePermission(HttpServletRequest request,
                                                @PathVariable("permissionID") Integer permissionID,
                                                @RequestBody Map<String, Object> PermissionMap,
                                                @RequestBody Permission Permission){
        int userID=(Integer)request.getAttribute("userID");
        String permissionDescription=(String)PermissionMap.get("PermissionDescripton");
        PermissionService.updatePermission(userID, permissionID, permissionDescription, Permission);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{permissionID}")
    public ResponseEntity<Map<String, Boolean>> deletePermission(HttpServletRequest request, 
                                    @PathVariable("permissionID") Integer permissionID){
            int userID=(Integer)request.getAttribute("userID");
            PermissionService.removePermissionWithAllTransactions(userID, permissionID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
