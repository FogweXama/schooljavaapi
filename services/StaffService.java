package services;

import java.util.List;

import domain.Staff;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.StaffRepository;

@service
@Transactional
public class StaffService implements IStaffService{
@Autowired
StaffRepository staffRepository;
    @Override
    public List<Staff> fetchAllCategories(Integer userId) {
        staffRepository.findAll(userId);
    }

    @Override
    public Staff fetchStaffById(Integer userId, Integer StaffId) throws ResourceNotFoundException {
        staffRepository.findById(userId, StaffId);
    }

    @Override
    public Staff addStaff(Integer userID, Integer PersonID, String Shift, Boolean PTAStaff,
            String Username, String Password, Double Salary, String ProductDescription) throws BadRequestException {
        staffRepository.create(userID, PersonID, Shift, PTAStaff, Username, Password, Salary, ProductDescription);
    }

    @Override
    public void updateStaff(Integer userID, Integer StaffID, Integer PersonID, String Shift, Boolean PTAStaff,
            String Username, String Password, Double Salary, String ProductDescription, Staff Staff)
            throws BadRequestException {
        staffRepository.update(userID, StaffID, PersonID, Shift, PTAStaff, Username, Password, Salary, ProductDescription, Staff);
    }

    @Override
    public void removeStaffWithAllTransactions(Integer userId, Integer StaffId) throws ResourceNotFoundException {
        staffRepository.removeById(userId, StaffId);
    }
    
}
