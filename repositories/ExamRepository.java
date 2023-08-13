package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Exam;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class ExamRepository implements IExamRepository, IGeneralRepository {
        private static final String SQL_EXAMINATION_FIND_ALL = "SELECT examid, examname, examcoefficient, examdescription, tslid, buildingid, periodid\n"
                        + //
                        "\tFROM public.tblexam";
        private static final String SQL_EXAMINATION_FIND_BY_ID = "SELECT examid, examname, examcoefficient, examdescription, tslid, buildingid, periodid\n"
                        + //
                        "\tFROM public.tblexam where examid=?";
        private static final String SQL_EXAMINATION_CREATE = "INSERT INTO public.tblexam(examid, examname, examcoefficient, examdescription, tslid, buildingid, periodid) VALUES (NEXTVAL('tblEXAMINATION_SEQ'), ?, ?, ?, ?, ?, ?)";
        private static final String SQL_EXAMINATION_UPDATE = "UPDATE public.tblexam SET examname=?, examcoefficient=?, examdescription=?, tslid=?, buildingid=?, periodid=? WHERE examid=?";
        private static final String SQL_EXAMINATION_DELETE = "DELETE FROM public.tblexam WHERE examid=?";
        private static final String SQL_EXAMINATION_DELETE_ALL = "DELETE FROM public.tblexam";
        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Exam> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_EXAMINATION_FIND_ALL, examRowMapper, new Object[] { userID });
        }

        @Override
        public Exam findById(Integer userID, Integer examID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_EXAMINATION_FIND_BY_ID, examRowMapper,
                                        new Object[] { userID, examID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String ExamName, Integer ExamCoefficient,
                        String ExamDescription,
                        Integer Tslid, Integer BuildingID, Integer PeriodID) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_EXAMINATION_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, ExamName);
                                ps.setInt(2, ExamCoefficient);
                                ps.setString(3, ExamDescription);
                                ps.setInt(4, Tslid);
                                ps.setInt(5, BuildingID);
                                ps.setInt(6, PeriodID);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("examID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }
        @Override
        public void update(Integer userID, Integer ExamID, String ExamName, Integer ExamCoefficient,
                        String ExamDescription,
                        Integer Tslid, Integer BuildingID, Integer PeriodID, Exam exam) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_EXAMINATION_UPDATE, new Object[] {
                                        exam.getExamName(),
                                        exam.getExamCoefficient(),
                                        exam.getExamDescription(),
                                        exam.getTslID(),
                                        exam.getBuildingID(),
                                        exam.getPeriodID(),
                                        ExamID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer ExamID) {
                jdbcTemplate.update(SQL_EXAMINATION_DELETE, new Object[] { userID, ExamID });
        }

        private RowMapper<Exam> examRowMapper = ((rs, rowNum) -> {
                return new Exam(
                                rs.getInt("ExamID"),
                                rs.getString("Examname"),
                                rs.getString("ExamDescription"),
                                rs.getInt("examcoefficient"),
                                rs.getInt("tslid"),
                                rs.getInt("buildingID"),
                                rs.getInt("periodID"));
        });
}
