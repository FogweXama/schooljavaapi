package repositories;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.period;
import domain.Period;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class PeriodRepository implements IPeriodRepository, IGeneralRepository {
        private static final String SQL_PERIOD_FIND_ALL = "SELECT periodid, periodname, start_time, end_time\n" + //
                        "\tFROM public.tblperiod";
        private static final String SQL_PERIOD_FIND_BY_ID = "SELECT periodid, periodname, start_time, end_time\n" + //
                        "\tFROM public.tblperiod where periodid=?";
        private static final String SQL_PERIOD_CREATE = "INSERT INTO public.tblperiod(periodid, periodname, start_time, end_time) VALUES (NEXTVAL('tblperiod_SEQ'), ?, ?, ?)";
        private static final String SQL_PERIOD_UPDATE = "UPDATE public.tblperiod SET periodname=?, start_time=?, end_time=? WHERE periodid=?";
        private static final String SQL_PERIOD_DELETE = "DELETE FROM public.tblperiod WHERE periodid=?";
        private static final String SQL_PERIOD_DELETE_ALL = "DELETE FROM public.tblperiod";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Period> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_PERIOD_FIND_ALL, periodRowMapper, new Object[] { userID });
        }

        @Override
        public Period findById(Integer userID, Integer PeriodID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_PERIOD_FIND_BY_ID, periodRowMapper,
                                        new Object[] { userID, PeriodID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String PeriodName, Date startTime, Date endTime)
                        throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_PERIOD_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setDate(1, PeriodName);
                                ps.setString(2, startTime);
                                ps.setInt(3, endTime); 
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("periodID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer PeriodID, String PeriodName, Date startTime, Date endTime,
                        Period period)
                        throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_PERIOD_UPDATE, new Object[] {
                                        period.getPeriodName(),
                                        period.getStart_time(),
                                        period.getEnd_time(), 
                                        PeriodID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer PeriodID) {
                jdbcTemplate.update(SQL_PERIOD_DELETE, new Object[] { userID, PeriodID });
        }

        private RowMapper<Period> periodRowMapper = ((rs, rowNum) -> {
                return new Period(
                                rs.getInt("PeriodID"),
                                rs.getString("periodname"),
                                rs.getDate("start_time"),
                                rs.getDate("end_time"));
        });
}
