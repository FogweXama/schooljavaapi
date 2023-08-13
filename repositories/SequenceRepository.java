package repositories;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Sequence;
import domain.Student;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class SequenceRepository implements ISequenceRepository, IGeneralRepository {
        private static final String SQL_SEQUENCE_FIND_ALL = "SELECT sequenceID, startdate, enddate, coefficient, termid, sequencename\n"
                        + //
                        "\tFROM public.tblsequence";
        private static final String SQL_SEQUENCE_FIND_BY_ID = "SELECT sequenceID, startdate, enddate, coefficient, termid, sequencename\n"
                        + //
                        "\tFROM public.tblsequence where sequenceID=?";
        private static final String SQL_SEQUENCE_CREATE = "INSERT INTO public.tblsequence(sequenceID, startdate, enddate, coefficient, termid, sequencename) VALUES (NEXTVAL('tblsequence_SEQ'), ?, ?, ?, ?, ?)";
        private static final String SQL_SEQUENCE_UPDATE = "UPDATE public.tblsequence SET startdate=?, enddate=?, coefficient=?, termid=?, sequencename=? WHERE sequenceID=?";
        private static final String SQL_SEQUENCE_DELETE = "DELETE FROM public.tblsequence WHERE sequenceID=?";
        private static final String SQL_SEQUENCE_DELETE_ALL = "DELETE FROM public.tblsequence";

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Sequence> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_SEQUENCE_FIND_ALL, sequenceRowMapper, new Object[] { userID });
        }

        @Override
        public Sequence findById(Integer userID, Integer sequenceID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_SEQUENCE_FIND_BY_ID, sequenceRowMapper,
                                        new Object[] { userID, sequenceID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String SequenceName, Date startDate, Date endDate,
                        Integer Coefficient, Integer TermID) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_SEQUENCE_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setDate(1, startDate);
                                ps.setDate(2, endDate);
                                ps.setInt(3, Coefficient);
                                ps.setInt(4, TermID);
                                ps.setString(5, SequenceName);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("sequenceID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer sequenceID, String SequenceName, Date startDate, Date endDate,
                        Integer Coefficient, Integer TermID, Sequence sequence) throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_SEQUENCE_UPDATE, new Object[] {
                                        sequence.getStartDate(),
                                        sequence.getEndDate(),
                                        sequence.getCoefficient(),
                                        sequence.getTermID(),
                                        sequence.getSequenceName(),
                                        sequenceID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer sequenceID) {
                jdbcTemplate.update(SQL_SEQUENCE_DELETE, new Object[] { userID, sequenceID });
        }

        private RowMapper<Sequence> sequenceRowMapper = ((rs, rowNum) -> {
                return new Sequence(
                                rs.getInt("sequenceID"),
                                rs.getDate("startdate"),
                                rs.getDate("enddate"),
                                rs.getInt("coefficient"),
                                rs.getInt("termid"),
                                rs.getString("sequencename"));
        });
}
