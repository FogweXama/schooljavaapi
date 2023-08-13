package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Timetable;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class TimetableRepository implements ITimetableRepository, IGeneralRepository {
        private static final String SQL_TIMETABLE_FIND_ALL = "SELECT timetableID, buildingid, periodid, tslid, dayid\n" + //
                        "\tFROM public.tbl_timetable";
        private static final String SQL_TIMETABLE_FIND_BY_ID = "SELECT timetableID, buildingid, periodid, tslid, dayid\n"
                        + //
                        "\tFROM public.tbl_timetable where timetableID=?";
        private static final String SQL_TIMETABLE_CREATE = "INSERT INTO public.tbl_timetable(timetableID, buildingid, periodid, tslid, dayid) VALUES (NEXTVAL('tbl_timetable_SEQ'), ?, ?, ?, ?)";
        private static final String SQL_TIMETABLE_UPDATE = "UPDATE public.tbl_timetable SET buildingid=?, periodid=?, tslid=?, dayid=? WHERE timetableID=?";
        private static final String SQL_TIMETABLE_DELETE = "DELETE FROM public.tbl_timetable WHERE timetableID=?";
        private static final String SQL_TIMETABLE_DELETE_ALL = "DELETE FROM public.tbl_timetable";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Timetable> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_TIMETABLE_FIND_ALL, timetableRowMapper, new Object[] { userID });
        }

        @Override
        public Timetable findById(Integer userID, Integer timetableID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_TIMETABLE_FIND_BY_ID, timetableRowMapper,
                                        new Object[] { userID, timetableID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

@Override
public Integer create(Integer userID, Integer BuildingID, Integer PeriodID, Integer TslID,
                Integer DayID) throws BadRequestException {
        try {
                KeyHolder keyHolder = new GeneratedKeyHolder();
                jdbcTemplate.update(connection -> {
                        PreparedStatement ps = connection.prepareStatement(SQL_TIMETABLE_CREATE,
                                        Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, BuildingID);
                        ps.setInt(2, PeriodID);
                        ps.setInt(3, TslID);
                        ps.setInt(4, DayID);
                        return ps;
                }, keyHolder);
                return (Integer) keyHolder.getKeys().get("timetableID");
        } catch (DataAccessException e) {
                throw new BadRequestException("Invalid request");
        }

        @Override
        public void update(Integer userID, Integer timetableID, Integer BuildingID, Integer PeriodID, Integer TslID,
                        Integer DayID, Timetable timetable) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_TIMETABLE_UPDATE, new Object[] {
                                        timetable.getBuildingID(),
                                        timetable.getPeriodID(),
                                        timetable.getTslID(),
                                        timetable.getDayID(),
                                        timetableID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer timetableID) {
                jdbcTemplate.update(SQL_TIMETABLE_DELETE, new Object[] { userID, timetableID });
        }

        private RowMapper<Timetable> timetableRowMapper = ((rs, rowNum) -> {
                return new Timetable(
                                rs.getInt("timetableID"),
                                rs.getInt("buildingid"),
                                rs.getInt("periodid"),
                                rs.getInt("tslid"),
                                rs.getInt("dayid"));
        });
}
