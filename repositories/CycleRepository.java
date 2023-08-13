package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Cycle;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class CycleRepository implements ICycleRepository, IGeneralRepository {
        private static final String SQL_CYCLE_FIND_ALL = "SELECT CycleID, sectionid, cyclename, certificationtobeobtained FROM public.tblcycle";
        private static final String SQL_CYCLE_FIND_BY_ID = "SELECT CycleID, sectionid, cyclename, certificationtobeobtained\n"
                        + //
                        "\tFROM public.tblcycle where CycleID=?";
        private static final String SQL_CYCLE_CREATE = "INSERT INTO public.tblcycle(\n" + //
                        "\tCycleID, sectionid, cyclename, certificationtobeobtained)\n" + //
                        "\tVALUES (NEXTVAL('tblcycle_SEQ'), ?, ?, ?)";
        private static final String SQL_CYCLE_UPDATE = "UPDATE public.tblcycle\n" + //
                        "\tSET sectionid=?, cyclename=?, certificationtobeobtained=?\n" + //
                        "\tWHERE CycleID=?";
        private static final String SQL_CYCLE_DELETE = "DELETE FROM public.tblcycle WHERE CycleID=?";
        private static final String SQL_CYCLE_DELETE_ALL = "DELETE FROM public.tblcycle";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Cycle> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_CYCLE_FIND_ALL, cycleRowMapper, new Object[] { userId });
        }

        @Override
        public Cycle findById(Integer userID, Integer CycleID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_CYCLE_FIND_BY_ID, cycleRowMapper,
                                        new Object[] { userID, CycleID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, Integer SectionID, String CycleName,
                        String certificationtobeobtained) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_CYCLE_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setInt(1, SectionID);
                                ps.setString(2, CycleName);
                                ps.setString(3, certificationtobeobtained);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("CycleID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer CycleID, Integer SectionID, String CycleName,
                        String certificationtobeobtained, Cycle cycle) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_CYCLE_UPDATE, new Object[] {
                                        cycle.getSectionID(),
                                        cycle.getCycleName(),
                                        cycle.getCertificationToBeObtained(),
                                        CycleID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer CycleID) {
                jdbcTemplate.update(SQL_CYCLE_DELETE, new Object[] { userID, CycleID });
        }

        private RowMapper<Cycle> cycleRowMapper = ((rs, rowNum) -> {
                return new Cycle(
                                rs.getInt("CycleID"),
                                rs.getInt("sectionid"),
                                rs.getString("cyclename"),
                                rs.getString("certificationtobeobtained"));
        });
}
