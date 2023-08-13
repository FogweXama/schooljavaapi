package repositories;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Assignment;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class AssignmentRepository implements IAssignmentRepository, IGeneralRepository {
        private static final String SQL_ASSIGNMENT_FIND_ALL = "SELECT AssignmentID, duedate, taskgiven, tslid, assignmentcoefficient\n"
                        + //
                        "\tFROM public.tblassignment";
        private static final String SQL_ASSIGNMENT_FIND_BY_ID = "SELECT AssignmentID, duedate, taskgiven, tslid, assignmentcoefficient\n"
                        + //
                        "\tFROM public.tblassignment where AssignmentID=?";
        private static final String SQL_ASSIGNMENT_CREATE = "INSERT INTO public.tblassignment(AssignmentID, duedate, taskgiven, tslid, assignmentcoefficient) VALUES (NEXTVAL('tblassignment_SEQ'), ?, ?, ?,?)";
        private static final String SQL_ASSIGNMENT_UPDATE = "UPDATE public.tblassignment SET duedate=?, taskgiven=?, tslid=?, assignmentcoefficient=? WHERE AssignmentID=?";
        private static final String SQL_ASSIGNMENT_DELETE = "DELETE FROM public.tblassignment WHERE AssignmentID=?";
        private static final String SQL_ASSIGNMENT_DELETE_ALL = "DELETE FROM public.tblassignment";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Assignment> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_ASSIGNMENT_FIND_ALL, assignmentRowMapper, new Object[] { userId });
        }

        @Override
        public Assignment findById(Integer userID, Integer AssignmentID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_ASSIGNMENT_FIND_BY_ID, assignmentRowMapper,
                                        new Object[] { userID, AssignmentID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID,  Date DueDate, String TaskGiven, Integer tslid,
                        Integer AssignmentCoefficient) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_ASSIGNMENT_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setDate(1, DueDate);
                                ps.setString(2, TaskGiven);
                                ps.setInt(3, tslid);
                                ps.setInt(4, assignmentcoefficient);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("AssignmentID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer AssignmentID, Date DueDate, String TaskGiven, Integer tslid,
                        Integer AssignmentCoefficient, Assignment assignment) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_ASSIGNMENT_UPDATE, new Object[] {
                                        assignment.getDueDate(),
                                        assignment.getTaskGiven(),
                                        assignment.getTslid(),
                                        assignment.getAssignmentCoefficient(),
                                        AssignmentID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer AssignmentID) {
                jdbcTemplate.update(SQL_ASSIGNMENT_DELETE, new Object[] { userID, AssignmentID });
        }

        private RowMapper<Assignment> assignmentRowMapper = ((rs, rowNum) -> {
                return new Assignment(
                                rs.getInt("AssignmentID"),
                                rs.getDate("duedate"),
                                rs.getString("taskgiven"),
                                rs.getInt("tslid"),
                                rs.getInt("assignmentcoefficient"));
        });
}
