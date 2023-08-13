package repositories;

import java.SQL_ACADEMIC.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;

import javax.swing.tree.RowMapper;

import domain.AcademicYear;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class AcademicYearRepository implements IAcademicYearRepository, IGeneralRepository {
    private static final String SQL_ACADEMIC_FIND_ALL = "SELECT academicyearid, startdate, enddate, academicyname FROM public.tblacademic_year";
    private static final String SQL_ACADEMIC_FIND_BY_ID = "SELECT academicyearid, startdate, enddate, academicyname FROM public.tblacademic_year where academicyearid=?";
    private static final String SQL_ACADEMIC_CREATE = "INSERT INTO public.tblacademic_year(academicyearid, startdate, enddate, academicyname) VALUES (NEXTVAL('tblacademic_year_SEQ'))";
    private static final String SQL_ACADEMIC_UPDATE = "UPDATE public.tblacademic_year SET startdate=?, enddate=?, academicyname=? WHERE academicyearid=?";
    private static final String SQL_ACADEMIC_DELETE = "DELETE FROM public.tblacademic_year WHERE academicyearid=?";
    private static final String SQL_ACADEMIC_DELETE_ALL = "DELETE FROM public.tblacademic_year";
    private static final String SQL_ACADEMINCTERM_DELETE = "DELETE FROM public.tblterm WHERE academicyearid=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<AcademicYear> findAll(Integer userId) throws ResourceNotFoundException {
        return jdbcTemplate.query(SQL_ACADEMIC_FIND_ALL, academicyearRowMapper, new Object[] { userId });
    }

    @Override
    public AcademicYear findById(Integer userId, Integer academicyearId) throws ResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_ACADEMIC_FIND_BY_ID, academicyearRowMapper,
                    new Object[] { userId, academicyearId });
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("Entry not found");
        }
    }

    @Override
    public Integer create(Integer userID, String academicYearName, Date startDate, Date endDate)
            throws BadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_ACADEMIC_CREATE,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(3, academicYearName);
                ps.setDate(1, startDate);
                ps.setDate(2, endDate);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ACADEMICYEARID");
        } catch (DataAccessException e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer userID, Integer academicYearID, String academicYearName, Date startDate, Date endDate,
            AcademicYear academicyear) throws BadRequestException {
        try {
            jdbcTemplate.update(SQL_ACADEMIC_UPDATE, new Object[] {
                    academicyear.getstartDate(),
                    academicyear.getendDate(),
                    academicyear.getacademicYearName(),
                    academicYearID
            });
        } catch (DataAccessException e) {
            throw new BadRequestException("Invalid Request");
        }

    }

    @Override
    public void removeById(Integer userID, Integer academicyearId) {
        this.removeAll(userID, academicyearId);
        jdbcTemplate.update(SQL_ACADEMIC_DELETE, new Object[] { userID, academicyearId });
    }
    private void removeAll(Integer userID, Integer academicyearId) {
        jdbcTemplate.update(SQL_ACADEMINCTERM_DELETE, new Object[] { academicyearId });
    }

    private void removeAllTermsInDB(Integer userID, Integer academicyearId) {
        jdbcTemplate.update(SQL_ACADEMIC_DELETE_ALL, new Object[] { academicyearId });
    }

    

    private RowMapper<AcademicYear> academicyearRowMapper = ((rs, rowNum) -> {
        return new AcademicYear(
                rs.getInt("academicyearid"),
                rs.getDate("academicyname"),
                rs.getDate("startdate"),
                rs.getString("enddate"));
    });

}
