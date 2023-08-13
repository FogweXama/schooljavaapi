package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Person;
import services.PersonService;

@RestController
@RestMapping('cmeduc/person')
public class PersonResource {
    @Autowired

    PersonService PersonService;
    
    @GetMapping("")
    public ResponseEntity<List<Person>> getAllPersons(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Person> Persons=PersonService.fetchAllCategories(userID);
        return new ResponseEntity<>(Persons, HttpStatus.OK);
    }
    
    @GetMapping("/{personID}")
    public ResponseEntity<Person> getPersonById(HttpServletRequest request, @PathVariable("personID") Integer personID){
        int userID=(Integer)request.getAttribute("userID");
        Person Person=PersonService.fetchPersonById(userID, personID);
        return new ResponseEntity<>(Person, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Person> addPerson(HttpServletRequest request, @RequestBody Map<String, Object> PersonMap){
        int userID=(Integer)request.getAttribute("userID");
        String personAddress=(String)PersonMap.get("PersonAddress");
        String personTelephone=(String)PersonMap.get("PersonTelephone");
        Date personDOB=(Date)PersonMap.get("personDOB");
        Byte ofUndeclaredSex=(Byte)PersonMap.get("ofUndeclaredSex");
        Boolean isfulltime=(Boolean)PersonMap.get("isfulltime");
        int roleID=(Integer)request.getAttribute("roleID");
        String PersonName=(String)PersonMap.get("PersonName");
        Date yearIn=(Date)PersonMap.get("yearIn");
        Boolean isCurrentlyActive=(Boolean)PersonMap.get("isCurrentlyActive");
        String personMatricule=(String)PersonMap.get("PersonMatricule");
        Date exitDate=(Date)PersonMap.get("exitDate"); 
        Person Person=PersonService.addPerson(userID, PersonName, personAddress, personTelephone, personDOB, 
            ofUndeclaredSex, yearIn, roleID, isfulltime, personMatricule, isCurrentlyActive, exitDate);
        return new ResponseEntity<>(Person, HttpStatus.CREATED);   
    } 
    @PutMapping("/{personID}")
    public ResponseEntity<Map<String, Boolean>> updatePerson(HttpServletRequest request,
                                                @PathVariable("personID") Integer personID,
                                                @RequestBody Map<String, Object> PersonMap,
                                                @RequestBody Person Person){
        int userID=(Integer)request.getAttribute("userID");
        String personAddress=(String)PersonMap.get("PersonAddress");
        String personTelephone=(String)PersonMap.get("PersonTelephone");
        Date personDOB=(Date)PersonMap.get("personDOB");
        Byte ofUndeclaredSex=(Byte)PersonMap.get("ofUndeclaredSex");
        Boolean isfulltime=(Boolean)PersonMap.get("isfulltime");
        int roleID=(Integer)request.getAttribute("roleID");
        String PersonName=(String)PersonMap.get("PersonName");
        Date yearIn=(Date)PersonMap.get("yearIn");
        Boolean isCurrentlyActive=(Boolean)PersonMap.get("isCurrentlyActive");
        String personMatricule=(String)PersonMap.get("PersonMatricule");
        Date exitDate=(Date)PersonMap.get("exitDate"); 
        PersonService.updatePerson(userID, personID, PersonName, personAddress, personTelephone, personDOB, 
            ofUndeclaredSex, yearIn, roleID, isfulltime, personMatricule, isCurrentlyActive, exitDate, Person);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{personID}")
    public ResponseEntity<Map<String, Boolean>> deletePerson(HttpServletRequest request, 
                                    @PathVariable("personID") Integer personID){
            int userID=(Integer)request.getAttribute("userID");
            PersonService.removePersonWithAllTransactions(userID, personID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
