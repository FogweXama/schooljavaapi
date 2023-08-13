package repositories;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Holiday;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class HolidayRepository implements IHolidayRepository, IGeneralRepository {
        private static final String SQL_HOLIDAY_FIND_ALL = "SELECT holidayid, termid, holidayname, end_time, start_time\n"
                        + //
                        "\tFROM public.tblholiday";
        private static final String SQL_HOLIDAY_FIND_BY_ID = "SELECT holidayid, termid, holidayname, end_time, start_time\n"
                        + //
                        "\tFROM public.tblholiday where holidayid=?";
        private static final String SQL_HOLIDAY_CREATE = "INSERT INTO public.tblholiday(holidayid, termid, holidayname, end_time, start_time) VALUES (NEXTVAL('tblholiday_SEQ'), ?, ?, ?, ?)";
        private static final String SQL_HOLIDAY_UPDATE = "UPDATE public.tblholiday SET termid=?, holidayname=?, end_time=?, start_time=? WHERE holidayid=?";
        private static final String SQL_HOLIDAY_DELETE = "DELETE FROM public.tblholiday WHERE holidayid=?";
        private static final String SQL_HOLIDAY_DELETE_ALL = "DELETE FROM public.tblholiday";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Holiday> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_HOLIDAY_FIND_ALL, holidayRowMapper, new Object[] { userID });
        }

        @Override
        public Holiday findById(Integer userID, Integer HolidayID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_HOLIDAY_FIND_BY_ID, holidayRowMapper,
                                        new Object[] { userID, HolidayID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String HolidayName, Date Start_Time, Date End_Time,
                        Integer TermID)
                        throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_HOLIDAY_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setInt(1, TermID);
                                ps.setString(2, HolidayName);
                                ps.setDate(3, End_Time);
                                ps.setDate(4, Start_Time);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("HolidayID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer HolidayID, String HolidayName, Date Start_Time, Date End_Time,
                        Integer TermID,
                        Holiday holiday) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_HOLIDAY_UPDATE, new Object[] {
                                        holiday.getTermID(),
                                        holiday.getHolidayName(),
                                        holiday.getEnd_time(),
                                        holiday.getStart_time(),
                                        HolidayID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer holidayID) {
                jdbcTemplate.update(SQL_HOLIDAY_DELETE, new Object[] { userID, holidayID });
        }

        private RowMapper<Holiday> holidayRowMapper = ((rs, rowNum) -> {
                return new Holiday(
                                rs.getInt("HolidayID"),
                                rs.getString("Holidayname"),
                                rs.getInt("Start_Time"),
                                rs.getInt("end_Time"),
                                rs.getInt("termID"));
        });
}
