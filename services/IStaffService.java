package services;

import java.util.List;

import domain.Staff;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IStaffService {
    List<Staff> fetchAllCategories(Integer userId);
    
    Staff fetchStaffById(Integer userId, Integer StaffId) throws ResourceNotFoundException;
    
    Staff addStaff(Integer userID, Integer PersonID, String Shift, Boolean PTAStaff, String Username, String Password, Double Salary, String ProductDescription) throws BadRequestException;
    
    void updateStaff(Integer userID, Integer StaffID, Integer PersonID, String Shift, Boolean PTAStaff, String Username, String Password, Double Salary, String ProductDescription, Staff Staff) throws BadRequestException;
    
    void removeStaffWithAllTransactions(Integer userId, Integer StaffId) throws ResourceNotFoundException;
}
