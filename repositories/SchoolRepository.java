package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.School;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class SchoolRepository implements ISchoolRepository, IGeneralRepository {

        private static final String SQL_SCHOOL_FIND_ALL = "SELECT schoolID, schoolname, centernumber, schooldescription\n"
                        + //
                        "\tFROM public.tblschool";
        private static final String SQL_SCHOOL_FIND_BY_ID = "SELECT schoolID, schoolname, centernumber, schooldescription\n"
                        + //
                        "\tFROM public.tblschool where schoolID=?";
        private static final String SQL_SCHOOL_CREATE = "INSERT INTO public.tblschool(schoolID, schoolname, centernumber, schooldescription) VALUES (NEXTVAL('tblschool_SEQ'), ?, ?, ?)";
        private static final String SQL_SCHOOL_UPDATE = "UPDATE public.tblschool SET schoolname=?, centernumber=?, schooldescription=? WHERE schoolID=?";
        private static final String SQL_SCHOOL_DELETE = "DELETE FROM public.tblschool WHERE schoolID=?";
        private static final String SQL_SCHOOL_DELETE_ALL = "DELETE FROM public.tblschool";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<School> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_SCHOOL_FIND_ALL, schoolRowMapper, new Object[] { userID });
        }

        @Override
        public School findById(Integer userID, Integer schoolID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_SCHOOL_FIND_BY_ID, schoolRowMapper,
                                        new Object[] { userID, schoolID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String SchoolName, String CenterNumber,
                        String SchoolDescription) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_SCHOOL_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setDate(1, SchoolName);
                                ps.setString(2, CenterNumber);
                                ps.setInt(3, SchoolDescription);
                                ps.setString(4, schoolcoefficient);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("schoolID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer schoolID, String SchoolName, String CenterNumber,
                        String SchoolDescription,
                        School school) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_SCHOOL_UPDATE, new Object[] {
                                        school.getSchoolName(),
                                        school.getCenterNumber(),
                                        school.getSchoolDescription(), 
                                        schoolID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer schoolID) {
                jdbcTemplate.update(SQL_SCHOOL_DELETE, new Object[] { userID, schoolID });
        }

        private RowMapper<School> SchoolRowMapper = ((rs, rowNum) -> {
                return new School(
                                rs.getInt("schoolID"),
                                rs.getString("schoolname"),
                                rs.getString("centernumber"),
                                rs.getString("schooldescription"));
        });

}