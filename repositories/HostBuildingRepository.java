package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.AcademicYear;
import domain.HostBuilding;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class HostBuildingRepository implements IHostBuildingRepository, IGeneralRepository {
        private static final String SQL_HOSTBUILDING_FIND_ALL = "SELECT buildingid, buildingname, buildingdescription, latitude, longitude\n"
                        + //
                        "\tFROM public.tblhostbuilding";
        private static final String SQL_HOSTBUILDING_FIND_BY_ID = "SELECT buildingid, buildingname, buildingdescription, latitude, longitude\n"
                        + //
                        "\tFROM public.tblhostbuilding where buildingid=?";
        private static final String SQL_HOSTBUILDING_CREATE = "INSERT INTO public.tblbuilding(buildingid, buildingname, buildingdescription, latitude, longitude) VALUES (NEXTVAL('tblbuilding_SEQ'), ?,?, ?, ?)";
        private static final String SQL_HOSTBUILDING_UPDATE = "UPDATE public.tblbuilding SET buildingname=?, buildingdescription=?, latitude=?, longitude=? WHERE buildingid=?";
        private static final String SQL_HOSTBUILDING_DELETE = "DELETE FROM public.tblbuilding WHERE buildingid=?";
        private static final String SQL_HOSTBUILDING_DELETE_ALL = "DELETE FROM public.tblbuilding";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<HostBuilding> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_HOSTBUILDING_FIND_ALL, buildingRowMapper, new Object[] { userID });
        }

        @Override
        public HostBuilding findById(Integer userID, Integer buildingID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_HOSTBUILDING_FIND_BY_ID, buildingRowMapper,
                                        new Object[] { userID, buildingID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String HostBuildingName,
                        String BuildingDescription,
                        Double Latitude, Double Longitude) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_HOSTBUILDING_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, HostBuildingName);
                                ps.setString(2, BuildingDescription);
                                ps.setDouble(3, Latitude);
                                ps.setDouble(4, Longitude);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("buildingID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer buildingID, String HostBuildingName, String BuildingDescription,
                        Double Latitude, Double Longitude, HostBuilding building) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_HOSTBUILDING_UPDATE, new Object[] {
                                        building.getBuildingName(),
                                        building.getBuildingDescription(),
                                        building.getLatitude(),
                                        building.getLongitude(),
                                        buildingID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer buildingId) {
                jdbcTemplate.update(SQL_HOSTBUILDING_DELETE, new Object[] { userID, buildingId });
        }

        private RowMapper<HostBuilding> buildingRowMapper = ((rs, rowNum) -> {
                return new HostBuilding(
                                rs.getInt("BuildingID"),
                                rs.getString("Buildingname"),
                                rs.getString("Buildingdescription"),
                                rs.getDouble("latitude"),
                                rs.getDouble("longitude"));
        });
}
