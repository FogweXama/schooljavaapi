package resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Reminder;
import services.ReminderService;


@RestController
@RestMapping('cmeduc/reminder')
public class ReminderResource {
    
    @Autowired
    ReminderService ReminderService;
    
    @GetMapping("")
    public ResponseEntity<List<Reminder>> getAllReminders(HttpServletRequest request){
        int userID=(Integer)request.getAttribute("userID");
        List<Reminder> Reminders=ReminderService.fetchAllCategories(userID);
        return new ResponseEntity<>(Reminders, HttpStatus.OK);
    }
    
    @GetMapping("/{reminderID}")
    public ResponseEntity<Reminder> getReminderById(HttpServletRequest request, @PathVariable("reminderID") Integer reminderID){
        int userID=(Integer)request.getAttribute("userID");
        Reminder Reminder=ReminderService.fetchReminderById(userID, reminderID);
        return new ResponseEntity<>(Reminder, HttpStatus.OK);
    
    }
    
    @PostMapping("")
    public ResponseEntity<Reminder> addReminder(HttpServletRequest request, @RequestBody Map<String, Object> ReminderMap){
        int userID=(Integer)request.getAttribute("userID");
        Byte hasBeenNotified=(Byte)ReminderMap.get("hasBeenNotified");
        Date dayOfClass=(Date)ReminderMap.get("dayOfClass");
        Date timeOfClass=(Date)ReminderMap.get("timeOfClass"); 
        Reminder Reminder=ReminderService.addReminder(userID, dayOfClass, timeOfClass, hasBeenNotified);
        return new ResponseEntity<>(Reminder, HttpStatus.CREATED);   
    } 
    @PutMapping("/{reminderID}")
    public ResponseEntity<Map<String, Boolean>> updateReminder(HttpServletRequest request,
                                                @PathVariable("reminderID") Integer reminderID,
                                                @RequestBody Map<String, Object> ReminderMap,
                                                @RequestBody Reminder Reminder){
        int userID=(Integer)request.getAttribute("userID");
        Byte hasBeenNotified=(Byte)ReminderMap.get("hasBeenNotified");
        Date dayOfClass=(Date)ReminderMap.get("dayOfClass");
        Date timeOfClass=(Date)ReminderMap.get("timeOfClass"); 
        ReminderService.updateReminder(userID, reminderID, dayOfClass, timeOfClass, hasBeenNotified, Reminder);
        Map<String, Boolean> map=new HashMap<>();
        map.put("Success", Boolean.TRUE);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{reminderID}")
    public ResponseEntity<Map<String, Boolean>> deleteReminder(HttpServletRequest request, 
                                    @PathVariable("reminderID") Integer reminderID){
            int userID=(Integer)request.getAttribute("userID");
            ReminderService.removeReminderWithAllTransactions(userID, reminderID);
            Map<String, Boolean> map=new HashMap<>();
            map.put("Success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
