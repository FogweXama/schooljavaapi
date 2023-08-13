package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Administrator;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class AdministratorRepository implements IAdministratorRepository, IGeneralRepository {
    private static final String SQL_ADMINISTRATIVE_FIND_ALL = "SELECT staffid, administratorid, administrationname, roleid, office FROM public.tbladministrator";
    private static final String SQL_ADMINISTRATIVE_FIND_BY_ID = "SELECT staffid, administratorid, administrationname, roleid, office FROM public.tbladministrator where administratorid=?";
    private static final String SQL_ADMINISTRATIVE_CREATE = "INSERT INTO public.tbladministrator(staffid, administratorid, administrationname, roleid, office) VALUES (?, NEXTVAL('tbladministrator_SEQ'), ?, ?, ?)";
    private static final String SQL_ADMINISTRATIVE_UPDATE = "UPDATE public.tbladministrator SET staffid=?,  administrationname=?, roleid=?, office=? WHERE administratorid=?";
    private static final String SQL_ADMINISTRATIVE_DELETE = "DELETE FROM public.tbladministrator WHERE administratorid=?";
    private static final String SQL_ADMINISTRATIVE_DELETE_ALL = "DELETE FROM public.tbladministrator";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Administrator> findAll(Integer userID) throws ResourceNotFoundException {
        return jdbcTemplate.query(SQL_ADMINISTRATIVE_FIND_ALL, administratorRowMapper, new Object[] { userId });
    }

    @Override
    public Administrator findById(Integer userID, Integer AdministratorId) throws ResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_ADMINISTRATIVE_FIND_BY_ID, administratorRowMapper,
                    new Object[] { userID, AdministratorId });
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("Entry not found");
        }
    }

    @Override
    public Integer create(Integer userID, Integer StaffID, String AdministratorName,
            Integer RoleID, String Office) throws BadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_ADMINISTRATIVE_CREATE,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, StaffID);
                ps.setString(2, AdministratorName);
                ps.setInt(3, RoleID);
                ps.setString(4, Office);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("AdministratorID");
        } catch (DataAccessException e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer userID, Integer StaffID, Integer AdministratorID, String AdministratorName,
            Integer RoleID, String Office, Administrator administrator) throws BadRequestException {
        try {
            jdbcTemplate.update(SQL_ADMINISTRATIVE_UPDATE, new Object[] {
                    administrator.getStaffID(),
                    administrator.getAdministrationname(),
                    administrator.getRoleID(),
                    administrator.getOffice(),
                    AdministratorID });
        } catch (DataAccessException e) {
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public void removeById(Integer userID, Integer AdministratorId) {
        jdbcTemplate.update(SQL_ADMINISTRATIVE_DELETE, new Object[] { userID, AdministratorId });
    }

    private RowMapper<Administrator> administratorRowMapper = ((rs, rowNum) -> {
        return new Administrator(
                rs.getInt("staffid"),
                rs.getInt("administratorid"),
                rs.getString("administrationname"),
                rs.getInt("roleid"),
                rs.getString("office"));
    });
}
