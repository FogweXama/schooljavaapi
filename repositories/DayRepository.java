package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Day;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class DayRepository implements IDayRepository, IGeneralRepository {
        private static final String SQL_DAY_FIND_ALL = "SELECT DayID, dayname\n" + //
                        "\tFROM public.tblday";
        private static final String SQL_DAY_FIND_BY_ID = "SELECT DayID, dayname FROM public.tblday where DayID=?";
        private static final String SQL_DAY_CREATE = "INSERT INTO public.tblday(\n" + //
                        "\tDayID, dayname)\n" + //
                        "\tVALUES (NEXTVAL('tblday_SEQ'), ?)";
        private static final String SQL_DAY_UPDATE = "UPDATE public.tblday SET dayname=? WHERE DayID=?";
        private static final String SQL_DAY_DELETE = "DELETE FROM public.tblday WHERE DayID=?";
        private static final String SQL_DAY_DELETE_ALL = "DELETE FROM public.tblday";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Day> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_DAY_FIND_ALL, dayRowMapper, new Object[] { userId });
        }

        @Override
        public Day findById(Integer userID, Integer DayID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_DAY_FIND_BY_ID, dayRowMapper,
                                        new Object[] { userID, DayID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String DayName) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_DAY_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, DayName);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("DayID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer DayID, String DayName, Day day) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_DAY_UPDATE, new Object[] {
                                        day.getDayName(),
                                        DayID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer DayID) {
                jdbcTemplate.update(SQL_DAY_DELETE, new Object[] { userID, DayID });
        }

        private RowMapper<Day> dayRowMapper = ((rs, rowNum) -> {
                return new Day(rs.getInt("DayID"),
                                rs.getInt("DayName"));
        });
}
