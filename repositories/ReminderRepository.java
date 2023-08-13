package repositories;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Reminder;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class ReminderRepository implements IReminderRepository, IGeneralRepository {
        private static final String SQL_REMINDER_FIND_ALL = "SELECT reminderID, dayofclass, timeofclass, hasbeennotified\n"
                        + //
                        "\tFROM public.tblreminder";
        private static final String SQL_REMINDER_FIND_BY_ID = "SELECT reminderID, dayofclass, timeofclass, hasbeennotified\n"
                        + //
                        "\tFROM public.tblreminder where reminderID=?";
        private static final String SQL_REMINDER_CREATE = "INSERT INTO public.tblreminder(reminderID, dayofclass, timeofclass, hasbeennotified) VALUES (NEXTVAL('tblreminder_SEQ'), ?, ?, ?)";
        private static final String SQL_REMINDER_UPDATE = "UPDATE public.tblreminder SET dayofclass=?, timeofclass=?, hasbeennotified=? WHERE reminderID=?";
        private static final String SQL_REMINDER_DELETE = "DELETE FROM public.tblreminder WHERE reminderID=?";
        private static final String SQL_REMINDER_DELETE_ALL = "DELETE FROM public.tblreminder";

        @Autowired
        JdbcTemplate jdbcTemplate;

@Override
public List<Reminder> findAll(Integer userID) throws ResourceNotFoundException {
        return jdbcTemplate.query(SQL_REMINDER_FIND_ALL, reminderRowMapper, new Object[] { userID });

        @Override
        public Reminder findById(Integer userID, Integer reminderID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_REMINDER_FIND_BY_ID, reminderRowMapper,
                                        new Object[] { userID, reminderID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, Date DayOfClass, Date TimeOfClass,
                        Byte HasBeenNotified)
                        throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_REMINDER_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setDate(1, DayOfClass);
                                ps.setDate(2, TimeOfClass);
                                ps.setInt(3, HasBeenNotified);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("reminderID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID,  Date DayOfClass, Date TimeOfClass, Byte HasBeenNotified,
                        Reminder reminder) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_REMINDER_UPDATE, new Object[] {
                                        reminder.getDayOfClass(),
                                        reminder.getTimeOfClass(),
                                        reminder.isHasBeenNotified(), 
                                        reminderID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer reminderID) {
                jdbcTemplate.update(SQL_REMINDER_DELETE, new Object[] { userID, reminderID });
        }

        private RowMapper<Reminder> reminderRowMapper = ((rs, rowNum) -> {
                return new Reminder(
                                rs.getInt("reminderID"),
                                rs.getDate("dayofclass"),
                                rs.getDate("timeofclass"),
                                rs.getString("hasbeennotified"));
        });
}
