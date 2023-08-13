package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Role;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class RoleRepository implements IRoleRepository, IGeneralRepository {
        private static final String SQL_ROLE_FIND_ALL = "SELECT roleID, rolename, permissionid, roledescription\n" + //
                        "\tFROM public.tblrole";
        private static final String SQL_ROLE_FIND_BY_ID = "SELECT roleID, rolename, permissionid, roledescription\n" + //
                        "\tFROM public.tblrole where roleID=?";
        private static final String SQL_ROLE_CREATE = "INSERT INTO public.tblrole(roleID, rolename, permissionid, roledescription) VALUES (NEXTVAL('tblrole_SEQ'), ?, ?, ?)";
        private static final String SQL_ROLE_UPDATE = "UPDATE public.tblrole SET rolename=?, permissionid=?, roledescription=? WHERE roleID=?";
        private static final String SQL_ROLE_DELETE = "DELETE FROM public.tblrole WHERE roleID=?";
        private static final String SQL_ROLE_DELETE_ALL = "DELETE FROM public.tblrole";
        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Role> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_ROLE_FIND_ALL, roleRowMapper, new Object[] { userID });
        }

        @Override
        public Role findById(Integer userID, Integer roleID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_ROLE_FIND_BY_ID, roleRowMapper,
                                        new Object[] { userID, roleID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String RoleName, Integer PermissionID,
                        String RoleDescription)
                        throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_ROLE_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, RoleName);
                                ps.setInt(2, PermissionID);
                                ps.setString(3, RoleDescription);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("roleID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer roleID, String RoleName, Integer PermissionID,
                        String RoleDescription,
                        Role role) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_ROLE_UPDATE, new Object[] {
                                        role.getRoleName(),
                                        role.getPermissionID(),
                                        role.getRoleDescription(),
                                        roleID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer roleID) {
                jdbcTemplate.update(SQL_ROLE_DELETE, new Object[] { userID, roleID });
        }

        private RowMapper<Role> roleRowMapper = ((rs, rowNum) -> {
                return new Role(
                                rs.getInt("roleID"),
                                rs.getString("rolename"),
                                rs.getInt("permissionid"),
                                rs.getString("roledescription"));
        });
}
