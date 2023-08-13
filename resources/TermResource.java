package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Term;
import services.TermService;


@RestController
@RestMapping('cmeduc/academicyear/{academicYearID}/term')
public class TermResource {
    @Autowired

    TermService TermService;
    
    @GetMapping("")
    public ResponseEntity<List<Term>> getAllTerms(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Term> Terms=TermService.fetchAllCategories(userID);
        return new ResponseEntity<>(Terms, HttpStatus.OK);
    }
    
    @GetMapping("/{termID}")
    public ResponseEntity<Term> getTermById(HttpServletRequest request, @PathVariable("termID") Integer termID){
        int userID=(Integer)request.getAttribute("userID");
        Term Term=TermService.fetchTermById(userID, termID);
        return new ResponseEntity<>(Term, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Term> addTerm(HttpServletRequest request, @RequestBody Map<String, Object> TermMap){
        int userID=(Integer)request.getAttribute("userID");
        String TermName=(String)TermMap.get("TermName");
        Date startDate=(Date)TermMap.get("startDate");
        Date endDate=(Date)TermMap.get("endDate"); 
        int coefficient=(Integer)request.getAttribute("coefficient");
        int academicYearID=(Integer)request.getAttribute("academicYearID");
        Term Term=TermService.addTerm(userID, startDate, endDate, coefficient, academicYearID, TermName);
        return new ResponseEntity<>(Term, HttpStatus.CREATED);   
    } 
    @PutMapping("/{termID}")
    public ResponseEntity<Map<String, Boolean>> updateTerm(HttpServletRequest request,
                                                @PathVariable("termID") Integer termID,
                                                @RequestBody Map<String, Object> TermMap,
                                                @RequestBody Term Term){
        int userID=(Integer)request.getAttribute("userID");
        String TermName=(String)TermMap.get("TermName");
        Date startDate=(Date)TermMap.get("startDate");
        Date endDate=(Date)TermMap.get("endDate"); 
        int coefficient=(Integer)request.getAttribute("coefficient");
        int academicYearID=(Integer)request.getAttribute("academicYearID");
        TermService.updateTerm(userID, termID, startDate, endDate, coefficient, academicYearID, TermName, Term);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{termID}")
    public ResponseEntity<Map<String, Boolean>> deleteTerm(HttpServletRequest request, 
                                    @PathVariable("termID") Integer termID){
            int userID=(Integer)request.getAttribute("userID");
            TermService.removeTermWithAllTransactions(userID, termID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
