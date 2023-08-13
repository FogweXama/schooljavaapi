package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Student;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class StudentRepository implements IStudentRepository, IGeneralRepository {
        private static final String SQL_STUDENT_FIND_ALL = "SELECT studentID, personid, studentadmissionno, reportno, classin, productdescription\n"
                        + //
                        "\tFROM public.tblstudent";
        private static final String SQL_STUDENT_FIND_BY_ID = "SELECT studentID, personid, studentadmissionno, reportno, classin, productdescription\n"
                        + //
                        "\tFROM public.tblstudent where studentID=?";
        private static final String SQL_STUDENT_CREATE = "INSERT INTO public.tblstudent(studentID, personid, studentadmissionno, reportno, classin, productdescription) VALUES (NEXTVAL('tblstudent_SEQ'), ?, ?, ?, ?, ?)";
        private static final String SQL_STUDENT_UPDATE = "UPDATE public.tblstudent SET personid=?, studentadmissionno=?, reportno=?, classin=?, productdescription=? WHERE studentID=?";
        private static final String SQL_STUDENT_DELETE = "DELETE FROM public.tblstudent WHERE studentID=?";
        private static final String SQL_STUDENT_DELETE_ALL = "DELETE FROM public.tblstudent";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Student> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_STUDENT_FIND_ALL, studentRowMapper, new Object[] { userID });
        }

        @Override
        public Student findById(Integer userID, Integer studentID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_STUDENT_FIND_BY_ID, studentRowMapper,
                                        new Object[] { userID, studentID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String StudentAdmissionNumber, Integer ReportNumber,
                        String ClassIn, String ProductDescription) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_STUDENT_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setDate(1, PersonID);
                                ps.setString(2, StudentAdmissionNumber);
                                ps.setInt(3, ReportNumber);
                                ps.setString(4, ClassIn);
                                ps.setString(5, ProductDescription);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("studentID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer studentID, String StudentAdmissionNumber, Integer ReportNumber,
                        String ClassIn, String ProductDescription, Student student) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_STUDENT_UPDATE, new Object[] {
                                        student.getPersonID(),
                                        student.getStudentAdmissionNo(),
                                        student.getReportNo(),
                                        student.getClassIn(),
                                        student.getProductDescription(),
                                        studentID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer studentID) {
                jdbcTemplate.update(SQL_STUDENT_DELETE, new Object[] { userID, studentID });
        }

        private RowMapper<Student> studentRowMapper = ((rs, rowNum) -> {
                return new Student(
                                rs.getInt("studentID"),
                                rs.getString("studentadmissionno"),
                                rs.getInt("reportno"),
                                rs.getString("classin"),
                                rs.getString("productdescription"),
                                rs.getInt("personid"));
        });
}
