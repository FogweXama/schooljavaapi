package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Student;
import domain.Teacher;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class TeacherRepository implements ITeacherRepository, IGeneralRepository {
        private static final String SQL_TEACHER_FIND_ALL = "SELECT teacherID, personid, holderof\n" + //
                        "\tFROM public.tbl_teacher";
        private static final String SQL_TEACHER_FIND_BY_ID = "SELECT teacherID, personid, holderof\n" + //
                        "\tFROM public.tbl_teacher where teacherID=?";
        private static final String SQL_TEACHER_CREATE = "INSERT INTO public.tbl_teacher(teacherID, personid, holderof) VALUES (NEXTVAL('tbl_teacher_SEQ'), ?, ?)";
        private static final String SQL_TEACHER_UPDATE = "UPDATE public.tbl_teacher SET personid=?, holderof=? WHERE teacherID=?";
        private static final String SQL_TEACHER_DELETE = "DELETE FROM public.tbl_teacher WHERE teacherID=?";
        private static final String SQL_TEACHER_DELETE_ALL = "DELETE FROM public.tbl_teacher";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Teacher> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_TEACHER_FIND_ALL, teacherRowMapper, new Object[] { userID });
        }

        @Override
        public Teacher findById(Integer userID, Integer teacherID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_TEACHER_FIND_BY_ID, teacherRowMapper,
                                        new Object[] { userID, teacherID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, Integer PersonID, String HolderOf)
                        throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_TEACHER_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setInt(1, PersonID);
                                ps.setString(2, HolderOf); 
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("teacherID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer teacherID, Integer PersonID, String HolderOf, Teacher teacher)
                        throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_TEACHER_UPDATE, new Object[] {
                                        teacher.getPersonID(),
                                        teacher.getHolderOf(),
                                        teacherID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer teacherID) {
                jdbcTemplate.update(SQL_TEACHER_DELETE, new Object[] { userID, teacherID });
        }

        private RowMapper<Teacher> teacherRowMapper = ((rs, rowNum) -> {
                return new Teacher(
                                rs.getInt("teacherID"),
                                rs.getInt("personid"),
                                rs.getString("holderof"));
        });

}
