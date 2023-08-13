package services;

import java.util.Date;
import java.util.List;

import domain.Term;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import repositories.TermRepository;

@service
@Transactional
public class TermService implements ITermService {
@Autowired
TermRepository termRepository;
    @Override
    public List<Term> fetchAllCategories(Integer userId) {
        termRepository.findAll(userId);
    }

    @Override
    public Term fetchTermById(Integer userId, Integer TermId) throws ResourceNotFoundException {
        termRepository.findById(userId, TermId);
    }

    @Override
    public Term addTerm(Integer userID, Date startDate, Date endDate, Integer Coefficient,
            Integer AcademicYearID, String TermName) throws BadRequestException {
        termRepository.create(userID, startDate, endDate, Coefficient, AcademicYearID, TermName);
    }

    @Override
    public void updateTerm(Integer userID, Integer TermID, Date startDate, Date endDate, Integer Coefficient,
            Integer AcademicYearID, String TermName, Term Term) throws BadRequestException {
        termRepository.update(userID, TermID, startDate, endDate, Coefficient, AcademicYearID, TermName, Term);
    }

    @Override
    public void removeTermWithAllTransactions(Integer userId, Integer TermId) throws ResourceNotFoundException {
        termRepository.removeById(userId, TermId);;
    }
    
}
