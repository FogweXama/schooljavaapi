package services;

import java.util.Date;
import java.util.List;

import domain.Person;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.PersonRepository;

@service
@Transactional
public class PersonService implements IPersonService{
@Autowired
PersonRepository personRepository;
    @Override
    public List<Person> fetchAllCategories(Integer userId) {
        personRepository.findAll(userId);
    }

    @Override
    public Person fetchPersonById(Integer userId, Integer PersonId) throws ResourceNotFoundException {
        personRepository.findById(userId, PersonId);
    }

    @Override
    public Person addPerson(Integer userID, String PersonName, String PersonAddress,
            String PersonTelephone, Date PersonDOB, Byte OfUndeclaredSex, Date YearIn, Integer RoleID,
            Boolean IsFullTime, String PersonMatricule, Boolean IsCurrentlyActive, Date ExitDate)
            throws BadRequestException {
        personRepository.create(userID, PersonName, PersonAddress, PersonTelephone, PersonDOB, OfUndeclaredSex, YearIn, RoleID, IsFullTime, PersonMatricule, IsCurrentlyActive, ExitDate);
    }

    @Override
    public void updatePerson(Integer userID, Integer PersonID, String PersonName, String PersonAddress,
            String PersonTelephone, Date PersonDOB, Byte OfUndeclaredSex, Date YearIn, Integer RoleID,
            Boolean IsFullTime, String PersonMatricule, Boolean IsCurrentlyActive, Date ExitDate, Person Person)
            throws BadRequestException {
        personRepository.update(userID, PersonID, PersonName, PersonAddress, PersonTelephone, PersonDOB, OfUndeclaredSex, YearIn, RoleID, IsFullTime, PersonMatricule, IsCurrentlyActive, ExitDate, Person);
    }

    @Override
    public void removePersonWithAllTransactions(Integer userId, Integer PersonId) throws ResourceNotFoundException {
        personRepository.removeById(userId, PersonId);
    }
    
}
