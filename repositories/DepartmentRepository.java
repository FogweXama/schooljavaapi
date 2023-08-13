package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Department;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class DepartmentRepository implements IDepartmentRepository, IGeneralRepository {
        private static final String SQL_DEPARTMENT_FIND_ALL = "SELECT departmentid, departmentname, schoolid\n" + //
                        "\tFROM public.tbldepartment";
        private static final String SQL_DEPARTMENT_FIND_BY_ID = "SELECT departmentid, departmentname, schoolid\n" + //
                        "\tFROM public.tbldepartment where departmentid=?";
        private static final String SQL_DEPARTMENT_CREATE = "INSERT INTO public.tbldepartment(departmentid, departmentname, schoolid) VALUES (NEXTVAL('tbldepartment_SEQ'), ?, ?)";
        private static final String SQL_DEPARTMENT_UPDATE = "UPDATE public.tbldepartment SET departmentname=?, schoolid=? WHERE departmentid=?";
        private static final String SQL_DEPARTMENT_DELETE = "DELETE FROM public.tbldepartment WHERE departmentid=?";
        private static final String SQL_DEPARTMENT_DELETE_ALL = "DELETE FROM public.tbldepartment";
        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Department> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_DEPARTMENT_FIND_ALL, departmentRowMapper, new Object[] { userID });
        }

        @Override
        public Department findById(Integer userID, Integer departmentID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_DEPARTMENT_FIND_BY_ID, departmentRowMapper,
                                        new Object[] { userID, departmentID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String DepartmentName, Integer SchoolID) {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_DEPARTMENT_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setInt(2, SchoolID);
                                ps.setString(1, DepartmentName);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("departmentID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer DepartmentID, String DepartmentName, Integer SchoolID,
                        Department department)
                        throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_DEPARTMENT_UPDATE, new Object[] {
                                        department.getDepartmentName(),
                                        department.getSchoolID(),
                                        DepartmentID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer DepartmentId) {
                jdbcTemplate.update(SQL_DEPARTMENT_DELETE, new Object[] { userID, DepartmentId });
        } 

        private RowMapper<Department> departmentRowMapper = ((rs, rowNum) -> {
                return new Department(rs.getInt("DepartmentID"),
                                rs.getInt("SchoolID"),
                                rs.getString("Departmentname"));
        });
}
