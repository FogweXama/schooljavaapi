package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Level;
import domain.LevelDivision;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class DivisionRepository implements IDivisionRepository, IGeneralRepository {
        private static final String SQL_LEVELDIV_FIND_ALL = "SELECT leveldivisionid, divisionname, levelid\n" + //
                        "\tFROM public.tblleveldivision";
        private static final String SQL_LEVELDIV_FIND_BY_ID = "SELECT leveldivisionid, divisionname, levelid\n" + //
                        "\tFROM public.tblleveldivision where leveldivisionid=?";
        private static final String SQL_LEVELDIV_CREATE = "INSERT INTO public.tblleveldivision(leveldivisionid, divisionname, levelid) VALUES (NEXTVAL('tblleveldivision_SEQ'), ?, ?)";
        private static final String SQL_LEVELDIV_UPDATE = "UPDATE public.tblleveldivision SET leveldivisionid=?, divisionname=?, levelid=? WHERE leveldivisionid=?";
        private static final String SQL_LEVELDIV_DELETE = "DELETE FROM public.tblleveldivision WHERE leveldivisionid=?";
        private static final String SQL_LEVELDIV_DELETE_ALL = "DELETE FROM public.tblleveldivision";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<LevelDivision> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_LEVELDIV_FIND_ALL, divisionRowMapper, new Object[] { userID });
        }

        @Override
        public LevelDivision findById(Integer userID, Integer LevelDivisionId) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_LEVELDIV_FIND_BY_ID, divisionRowMapper,
                                        new Object[] { userID, LevelDivisionId });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String LevelDivisionName, Integer LevelID)
                        throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_LEVELDIV_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, LevelDivisionName);
                                ps.setInt(2, LevelID);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("LevelDivisionId");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer LevelDivisionID, String LevelDivisionName, Integer LevelID,
                        LevelDivision division) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_LEVELDIV_UPDATE, new Object[] {
                                        division.getDivisionName(),
                                        division.getLevelID(),
                                        LevelDivisionID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer LevelDivisionID) {
                jdbcTemplate.update(SQL_LEVELDIV_DELETE, new Object[] { userID, LevelDivisionID });
        }

        private RowMapper<LevelDivision> divisionRowMapper = ((rs, rowNum) -> {
                return new LevelDivision(
                                rs.getInt("LevelDivisionID"),
                                rs.getDate("divisionName"),
                                rs.getInt("levelid"));
        });
}
