package repositories;

import java.util.Date;
import java.util.List;

import domain.Term;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ITermRepository {
        List<Term> findAll(Integer userID) throws ResourceNotFoundException;
        
        Term findById(Integer userID, Integer TermID) throws ResourceNotFoundException;
        
        Integer create(Integer userID, Date startDate, Date endDate, Integer Coefficient, Integer AcademicYearID, String TermName) throws BadRequestException;
        
        void update(Integer userID, Integer TermID,  Date startDate, Date endDate, Integer Coefficient, Integer AcademicYearID, String TermName, Term Term) throws BadRequestException;
        
        void removeById(Integer userID, Integer TermID);
}
