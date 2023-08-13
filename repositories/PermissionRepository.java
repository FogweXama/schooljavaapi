package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.AcademicYear;
import domain.Permission;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class PermissionRepository implements IPermissionRepository, IGeneralRepository {
        private static final String SQL_PERMISSION_FIND_ALL = "SELECT permissionid, permissiondescription\n" + //
                        "\tFROM public.tblpermission";
        private static final String SQL_PERMISSION_FIND_BY_ID = "SELECT permissionid, permissiondescription\n" + //
                        "\tFROM public.tblpermission where permissionid=?";
        private static final String SQL_PERMISSION_CREATE = "INSERT INTO public.tblpermission(permissionid, permissiondescription) VALUES (NEXTVAL('tblpermission_SEQ'), ?)";
        private static final String SQL_PERMISSION_UPDATE = "UPDATE public.tblpermission SET permissiondescription=? WHERE permissionid=?";
        private static final String SQL_PERMISSION_DELETE = "DELETE FROM public.tblpermission WHERE permissionid=?";
        private static final String SQL_PERMISSION_DELETE_ALL = "DELETE FROM public.tblpermission";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Permission> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_PERMISSION_FIND_ALL, permissionRowMapper, new Object[] { userID });
        }

        @Override
        public Permission findById(Integer userID, Integer PermissionID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_PERMISSION_FIND_BY_ID, permissionRowMapper,
                                        new Object[] { userID, PermissionID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String PermissionDescription)
                        throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_PERMISSION_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, PermissionDescription);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("AssignmentID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer permissionID, String PermissionDescription, Permission permission)
                        throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_PERMISSION_UPDATE, new Object[] {
                                        permission.getPermissionDescription()),
                                        permissionID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer permissionID) {
                jdbcTemplate.update(SQL_PERMISSION_DELETE, new Object[] { userID, permissionID });
        }

        private RowMapper<Permission> permissionRowMapper = ((rs, rowNum) -> {
                return new Permission(
                                rs.getInt("PermissionID"),
                                rs.getString("PermissionDescription"));
        });
}
