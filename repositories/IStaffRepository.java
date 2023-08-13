package repositories;
 
import java.util.List;

import domain.Staff;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IStaffRepository {
        List<Staff> findAll(Integer userID) throws ResourceNotFoundException;
        
        Staff findById(Integer userID, Integer StaffID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, Integer PersonID, String Shift, Boolean PTAStaff, String Username, String Password, Double Salary, String ProductDescription) throws BadRequestException;
        
        void update(Integer userID, Integer StaffID, Integer PersonID, String Shift, Boolean PTAStaff, String Username, String Password, Double Salary, String ProductDescription, Staff Staff) throws BadRequestException;
        
        void removeById(Integer userID, Integer StaffID);
}
