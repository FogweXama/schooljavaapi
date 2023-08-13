package services;

import java.util.Date;
import java.util.List;

import domain.Term;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public interface ITermService {
    List<Term> fetchAllCategories(Integer userId);
    
    Term fetchTermById(Integer userId, Integer TermId) throws ResourceNotFoundException;
    
    Term addTerm(Integer userID, Date startDate, Date endDate, Integer Coefficient, Integer AcademicYearID, String TermName) throws BadRequestException;
    
    void updateTerm(Integer userID, Integer TermID,  Date startDate, Date endDate, Integer Coefficient, Integer AcademicYearID, String TermName, Term Term) throws BadRequestException;
    
    void removeTermWithAllTransactions(Integer userId, Integer TermId) throws ResourceNotFoundException;
}
