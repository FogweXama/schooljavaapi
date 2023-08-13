package repositories;

import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.Section;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class SectionRepository implements ISectionRepository, IGeneralRepository {
        private static final String SQL_SECTION_FIND_ALL = "SELECT sectionID, sectionname, schoolid\n" + //
                        "\tFROM public.tblsection";
        private static final String SQL_SECTION_FIND_BY_ID = "SELECT sectionID, sectionname, schoolid\n" + //
                        "\tFROM public.tblsection where sectionID=?";
        private static final String SQL_SECTION_CREATE = "INSERT INTO public.tblsection(sectionID, sectionname, schoolid) VALUES (NEXTVAL('tblsection_SEQ'), ?, ?)";
        private static final String SQL_SECTION_UPDATE = "UPDATE public.tblsection SET sectionname=?, schoolid=? WHERE sectionID=?";
        private static final String SQL_SECTION_DELETE = "DELETE FROM public.tblsection WHERE sectionID=?";
        private static final String SQL_SECTION_DELETE_ALL = "DELETE FROM public.tblsection";

        @Autowired
        JdbcTemplate jdbcTemplate;

@Override
public List<Section> findAll(Integer userID) throws ResourceNotFoundException {
        return jdbcTemplate.query(SQL_SECTION_FIND_ALL, sectionRowMapper, new Object[] { userID });

        @Override
        public Section findById(Integer userID, Integer sectionID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_SECTION_FIND_BY_ID, sectionRowMapper,
                                        new Object[] { userID, sectionID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String SectionName, Integer SchoolID)
                        throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_SECTION_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, SectionName); 
                                ps.setInt(2, SchoolID);
                                ps.setString(4, sectioncoefficient);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("sectionID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer sectionID, String SectionName, Integer SchoolID, Section section)
                        throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_SECTION_UPDATE, new Object[] {
                                        section.getSectionName(),
                                        section.getSchoolID(), 
                                        sectionID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer sectionID) {
                jdbcTemplate.update(SQL_SECTION_DELETE, new Object[] { userID, sectionID });

        }

        private RowMapper<Section> sectionRowMapper = ((rs, rowNum) -> {
                return new Section(
                                rs.getInt("sectionID"),
                                rs.getString("sectionname"),
                                rs.getInt("schoolid"));
        });
}
