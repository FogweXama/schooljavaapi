package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Section;
import services.SectionService;


@RestController
@RestMapping('cmeduc/school/{schoolID}/section')
public class SectionResource {
    @Autowired

    SectionService SectionService;
    
    @GetMapping("")
    public ResponseEntity<List<Section>> getAllSections(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Section> Sections=SectionService.fetchAllCategories(userID);
        return new ResponseEntity<>(Sections, HttpStatus.OK);
    }
    
    @GetMapping("/{sectionID}")
    public ResponseEntity<Section> getSectionById(HttpServletRequest request, @PathVariable("sectionID") Integer sectionID){
        int userID=(Integer)request.getAttribute("userID");
        Section Section=SectionService.fetchSectionById(userID, sectionID);
        return new ResponseEntity<>(Section, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Section> addSection(HttpServletRequest request, @RequestBody Map<String, Object> SectionMap){
        int userID=(Integer)request.getAttribute("userID");
        String SectionName=(String)SectionMap.get("SectionName");
        int schoolID=(int)SectionMap.get("schoolID");
        Date endDate=(Date)SectionMap.get("endDate"); 
        Section Section=SectionService.addSection(userID, SectionName, schoolID);
        return new ResponseEntity<>(Section, HttpStatus.CREATED);   
    } 
    @PutMapping("/{sectionID}")
    public ResponseEntity<Map<String, Boolean>> updateSection(HttpServletRequest request,
                                                @PathVariable("sectionID") Integer sectionID,
                                                @PathVariable("schoolID") Integer schoolID,
                                                @RequestBody Map<String, Object> SectionMap,
                                                @RequestBody Section Section){
        int userID=(Integer)request.getAttribute("userID");
        String SectionName=(String)SectionMap.get("SectionName");
        Date endDate=(Date)SectionMap.get("endDate"); 
        SectionService.updateSection(userID, sectionID, SectionName, schoolID, Section);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{sectionID}")
    public ResponseEntity<Map<String, Boolean>> deleteSection(HttpServletRequest request, 
                                    @PathVariable("sectionID") Integer sectionID){
            int userID=(Integer)request.getAttribute("userID");
            SectionService.removeSectionWithAllTransactions(userID, sectionID);;
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
