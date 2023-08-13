package repositories;

import java.util.Date;
import java.util.List;

import domain.Person; 
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IPersonRepository {
List<Person> findAll(Integer userID) throws ResourceNotFoundException;
        
        Person findById(Integer userID, Integer PersonID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, String PersonName, String PersonAddress, String PersonTelephone, Date PersonDOB, Byte OfUndeclaredSex, Date YearIn, Integer RoleID, Boolean IsFullTime, String PersonMatricule, Boolean IsCurrentlyActive, Date ExitDate) throws BadRequestException;
        
        void update(Integer userID, Integer PersonID, String PersonName, String PersonAddress, String PersonTelephone, Date PersonDOB, Byte OfUndeclaredSex, Date YearIn, Integer RoleID, Boolean IsFullTime, String PersonMatricule, Boolean IsCurrentlyActive, Date ExitDate, Person Person) throws BadRequestException;
        
        void removeById(Integer userID, Integer PersonID);
}
