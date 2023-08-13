package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.AcademicYear;
import domain.Level;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class LevelRepository implements ILevelRepository, IGeneralRepository {
        private static final String SQL_LEVEL_FIND_ALL = "SELECT levelid, levelname, cycleid\n" + //
                        "\tFROM public.tbllevel";
        private static final String SQL_LEVEL_FIND_BY_ID = "SELECT levelid, levelname, cycleid FROM public.tbllevel where levelid=?";
        private static final String SQL_LEVEL_CREATE = "INSERT INTO public.tbllevel(levelid, levelname, cycleid) VALUES (NEXTVAL('tbllevel_SEQ'),?,?)";
        private static final String SQL_LEVEL_UPDATE = "UPDATE public.tbllevel SET levelname=?, cycleid=? WHERE levelid=?";
        private static final String SQL_LEVEL_DELETE = "DELETE FROM public.tbllevel WHERE levelid=?";
        private static final String SQL_LEVEL_DELETE_ALL = "DELETE FROM public.tbllevel";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Level> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_LEVEL_FIND_ALL, levelRowMapper, new Object[] { userID });
        }

        @Override
        public Level findById(Integer userID, Integer LevelID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_LEVEL_FIND_BY_ID, levelRowMapper,
                                        new Object[] { userID, LevelID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String LevelName, Integer CycleID)
                        throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_LEVEL_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setDate(1, LevelName);
                                ps.setString(2, CycleID); 
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("LevelID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer LevelID, String LevelName, Integer CycleID, Level Level)
                        throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_LEVEL_UPDATE, new Object[] {
                                        Level.getLevelName(),
                                        Level.getCycleID(),
                                        LevelID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer LevelID) {
                jdbcTemplate.update(SQL_LEVEL_DELETE, new Object[] { userID, LevelID });
        }

        private RowMapper<Level> levelRowMapper = ((rs, rowNum) -> {
                return new Level(
                                rs.getInt("LevelID"),
                                rs.getString("LevelName"),
                                rs.getInt("cycleID"));
        });
}
