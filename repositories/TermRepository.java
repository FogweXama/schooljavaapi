package repositories;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.AcademicYear;
import domain.Student;
import domain.Term;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class TermRepository implements ITermRepository, IGeneralRepository {
        private static final String SQL_TERM_FIND_ALL = "SELECT termID, startdate, enddate, coefficient, academicyearid, termname\n"
                        + //
                        "\tFROM public.tblterm";
        private static final String SQL_TERM_FIND_BY_ID = "SELECT termID, startdate, enddate, coefficient, academicyearid, termname\n"
                        + //
                        "\tFROM public.tblterm where termID=?";
        private static final String SQL_TERM_CREATE = "INSERT INTO public.tblterm(termID, startdate, enddate, coefficient, academicyearid, termname) VALUES (NEXTVAL('tblterm_SEQ'), ?, ?, ?, ?, ?)";
        private static final String SQL_TERM_UPDATE = "UPDATE public.tblterm SET startdate=?, enddate=?, coefficient=?, academicyearid=?, termname=? WHERE termID=?";
        private static final String SQL_TERM_DELETE = "DELETE FROM public.tblterm WHERE termID=?";
        private static final String SQL_TERM_DELETE_ALL = "DELETE FROM public.tblterm";
        private static final String SQL_TERMSEQUENCE_DELETE = "DELETE FROM public.tblsequence WHERE termID=?";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Term> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_TERM_FIND_ALL, termRowMapper, new Object[] { userID });
        }

        @Override
        public Term findById(Integer userID, Integer termID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_TERM_FIND_BY_ID, termRowMapper,
                                        new Object[] { userID, termID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, Date startDate, Date endDate, Integer Coefficient,
                        Integer AcademicYearID, String TermName) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_TERM_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setDate(1, startDate);
                                ps.setDate(2, endDate);
                                ps.setInt(3, Coefficient);
                                ps.setInt(4, AcademicYearID);
                                ps.setString(5, TermName);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("termID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer termID, Date startDate, Date endDate, Integer Coefficient,
                        Integer AcademicYearID, String TermName, Term term) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_TERM_UPDATE, new Object[] {
                                        term.getStartDate(),
                                        term.getEndDate(),
                                        term.getCoefficient(),
                                        term.getAcademicYearID(),
                                        term.getTermName(),
                                        termID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer termID) {
                removeAllSequence(termID);
                jdbcTemplate.update(SQL_TERM_DELETE, new Object[] { userID, termID });
        }

        public void removeAllSequence(Integer termID){
                jdbcTemplate.update(SQL_TERMSEQUENCE_DELETE, new Object[] { termID });
        }


        private RowMapper<Term> termRowMapper = ((rs, rowNum) -> {
                return new Term(
                                rs.getInt("termID"),
                                rs.getDate("startdate"),
                                rs.getDate("enddate"),
                                rs.getInt("coefficient"),
                                rs.getInt("academicyearid"),
                                rs.getString("termname"));
        });
}
