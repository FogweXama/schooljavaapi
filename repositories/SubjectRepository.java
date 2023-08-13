package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Student;
import domain.Subject;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class SubjectRepository implements ISubjectRepository, IGeneralRepository {
        private static final String SQL_SUBJECT_FIND_ALL = "SELECT subjectID, subjectname, subjectcode, departmentid, subjectcoefficient\n"
                        + //
                        "\tFROM public.tblsubject";
        private static final String SQL_SUBJECT_FIND_BY_ID = "SELECT subjectID, subjectname, subjectcode, departmentid, subjectcoefficient\n"
                        + //
                        "\tFROM public.tblsubject where subjectID=?";
        private static final String SQL_SUBJECT_CREATE = "INSERT INTO public.tblsubject(subjectID, subjectname, subjectcode, departmentid, subjectcoefficient) VALUES (NEXTVAL('tblsubject_SEQ'), ?, ?, ?, ?)";
        private static final String SQL_SUBJECT_UPDATE = "UPDATE public.tblsubject SET subjectname=?, subjectcode=?, departmentid=?, subjectcoefficient=? WHERE subjectID=?";
        private static final String SQL_SUBJECT_DELETE = "DELETE FROM public.tblsubject WHERE subjectID=?";
        private static final String SQL_SUBJECT_DELETE_ALL = "DELETE FROM public.tblsubject";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Subject> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_SUBJECT_FIND_ALL, subjectRowMapper, new Object[] { userID });
        }

        @Override
        public Subject findById(Integer userID, Integer subjectID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_SUBJECT_FIND_BY_ID, subjectRowMapper,
                                        new Object[] { userID, subjectID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String SubjectName, String SubjectCode,
                        Integer DepartmentID,
                        Integer SubjectCoefficient) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_SUBJECT_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, SubjectName);
                                ps.setString(2, SubjectCode);
                                ps.setInt(3, DepartmentID);
                                ps.setInt(4, SubjectCoefficient);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("subjectID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer subjectID, String SubjectName, String SubjectCode,
                        Integer DepartmentID,
                        Integer SubjectCoefficient, Subject subject) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_SUBJECT_UPDATE, new Object[] {
                                        subject.getSubjectName(),
                                        subject.getSubjectCode(),
                                        subject.getDepartmentID(),
                                        subject.getSubjectCoefficient(),
                                        subjectID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer subjectID) {
                jdbcTemplate.update(SQL_SUBJECT_DELETE, new Object[] { userID, subjectID });
        }

        private RowMapper<Subject> subjectRowMapper = ((rs, rowNum) -> {
                return new Subject(
                                rs.getInt("subjectID"),
                                rs.getString("subjectname"),
                                rs.getString("subjectcode"),
                                rs.getInt("departmentid"),
                                rs.getInt("subjectcoefficient"));
        });
}
