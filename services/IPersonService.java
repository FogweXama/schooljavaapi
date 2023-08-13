package services;

import java.util.Date;
import java.util.List;

import domain.Person;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface IPersonService {
    List<Person> fetchAllCategories(Integer userId);
    
    Person fetchPersonById(Integer userId, Integer PersonId) throws ResourceNotFoundException;
    
    Person addPerson(Integer userID, String PersonName, String PersonAddress, String PersonTelephone, Date PersonDOB, Byte OfUndeclaredSex, Date YearIn, Integer RoleID, Boolean IsFullTime, String PersonMatricule, Boolean IsCurrentlyActive, Date ExitDate) throws BadRequestException;
    
    void updatePerson(Integer userID, Integer PersonID, String PersonName, String PersonAddress, String PersonTelephone, Date PersonDOB, Byte OfUndeclaredSex, Date YearIn, Integer RoleID, Boolean IsFullTime, String PersonMatricule, Boolean IsCurrentlyActive, Date ExitDate, Person Person) throws BadRequestException;
    
    void removePersonWithAllTransactions(Integer userId, Integer PersonId) throws ResourceNotFoundException;
}
