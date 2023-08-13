package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Staff;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class StaffRepository implements IStaffRepository, IGeneralRepository {
        private static final String SQL_STAFF_FIND_ALL = "SELECT staffID, personid, shift, ptastaff, username, userpwd, salary, productdescription\n"
                        + //
                        "\tFROM public.tblstaff";
        private static final String SQL_STAFF_FIND_BY_ID = "SELECT staffID, personid, shift, ptastaff, username, userpwd, salary, productdescription\n"
                        + //
                        "\tFROM public.tblstaff where staffID=?";
        private static final String SQL_STAFF_CREATE = "INSERT INTO public.tblstaff(staffID, personid, shift, ptastaff, username, userpwd, salary, productdescription) VALUES (NEXTVAL('tblstaff_SEQ'), ?, ?, ?, ?, ?, ?, ?)";
        private static final String SQL_STAFF_UPDATE = "UPDATE public.tblstaff SET personid=?, shift=?, ptastaff=?, username=?, userpwd=?, salary=?, productdescription=? WHERE staffID=?";
        private static final String SQL_STAFF_DELETE = "DELETE FROM public.tblstaff WHERE staffID=?";
        private static final String SQL_STAFF_DELETE_ALL = "DELETE FROM public.tblstaff";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Staff> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_STAFF_FIND_ALL, staffRowMapper, new Object[] { userID });
        }

        @Override
        public Staff findById(Integer userID, Integer staffID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_STAFF_FIND_BY_ID, staffRowMapper,
                                        new Object[] { userID, staffID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, Integer PersonID, String Shift, Boolean PTAStaff,
                        String Username, String Password, Double Salary, String ProductDescription)
                        throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_STAFF_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setDate(1, PersonID);
                                ps.setString(2, Shift);
                                ps.setInt(3, PTAStaff);
                                ps.setString(4, Username);
                                ps.setDate(5, Password);
                                ps.setString(6, Salary);
                                ps.setInt(7, ProductDescription);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("staffID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer staffID, Integer PersonID, String Shift, Boolean PTAStaff,
                        String Username,
                        String Password, Double Salary, String ProductDescription, Staff staff)
                        throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_STAFF_UPDATE, new Object[] {
                                        staff.getPersonID(),
                                        staff.getShift(),
                                        staff.isPtaStaff(),
                                        staff.getUsername(),
                                        staff.getUserpwd(),
                                        staff.getSalary(),
                                        staff.getProductDescription(),
                                        staffID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer staffID) {
                jdbcTemplate.update(SQL_STAFF_DELETE, new Object[] { userID, staffID });
        }

        private RowMapper<Staff> staffRowMapper = ((rs, rowNum) -> {
                return new Staff(
                                rs.getInt("staffID"),
                                rs.getInt("personid"),
                                rs.getString("shift"),
                                rs.getBoolean("productdescription"),
                                rs.getString("username"),
                                rs.getString("userpwd"),
                                rs.getDouble("salary"),
                                rs.getString("ptastaff"));
        });
}
