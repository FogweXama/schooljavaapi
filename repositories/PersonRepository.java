package repositories;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import javax.swing.tree.RowMapper;

import domain.AcademicYear;
import domain.Person;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

@Repository
public class PersonRepository implements IPersonRepository, IGeneralRepository {
        private static final String SQL_PERSON_FIND_ALL = "SELECT personid, personname, personaddress, persontelephone, persondob, ofundeclaredsex, yearin, roleid, isfulltime, personmatricule, iscurrentlyactive, exitdate\n"
                        + //
                        "\tFROM public.tblperson";
        private static final String SQL_PERSON_FIND_BY_ID = "SELECT personid, personname, personaddress, persontelephone, persondob, ofundeclaredsex, yearin, roleid, isfulltime, personmatricule, iscurrentlyactive, exitdate\n"
                        + //
                        "\tFROM public.tblperson where personid=?";
        private static final String SQL_PERSON_CREATE = "INSERT INTO public.tblperson(personid, personname, personaddress, persontelephone, persondob, ofundeclaredsex, yearin, roleid, isfulltime, personmatricule, iscurrentlyactive, exitdate) VALUES (NEXTVAL('tblperson_SEQ'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        private static final String SQL_PERSON_UPDATE = "UPDATE public.tblperson SET personname=?, personaddress=?, persontelephone=?, persondob=?, ofundeclaredsex=?, yearin=?, roleid=?, isfulltime=?, personmatricule=?, iscurrentlyactive=?, exitdate=? WHERE personid=?";
        private static final String SQL_PERSON_DELETE = "DELETE FROM public.tblperson WHERE personid=?";
        private static final String SQL_PERSON_DELETE_ALL = "DELETE FROM public.tblperson";
        @Autowired
        JdbcTemplate jdbcTemplate;

        @Override
        public List<Person> findAll(Integer userID) throws ResourceNotFoundException {
                return jdbcTemplate.query(SQL_PERSON_FIND_ALL, personRowMapper, new Object[] { userID });
        }

        @Override
        public Person findById(Integer userID, Integer personID) throws ResourceNotFoundException {
                try {
                        return jdbcTemplate.queryForObject(SQL_PERSON_FIND_BY_ID, personRowMapper,
                                        new Object[] { userID, personID });
                } catch (DataAccessException e) {
                        throw new ResourceNotFoundException("Entry not found");
                }
        }

        @Override
        public Integer create(Integer userID, String PersonName, String PersonAddress,
                        String PersonTelephone,
                        Date PersonDOB, Byte OfUndeclaredSex, Date YearIn, Integer RoleID, Boolean IsFullTime,
                        String PersonMatricule, Boolean IsCurrentlyActive, Date ExitDate) throws BadRequestException {
                try {
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(connection -> {
                                PreparedStatement ps = connection.prepareStatement(SQL_PERSON_CREATE,
                                                Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, PersnonName);
                                ps.setString(2, PersonAddress);
                                ps.setString(3, PersonTelephone);
                                ps.setDate(4, PersonDOB);
                                ps.setByte(5, OfUndeclaredSex);
                                ps.setDate(6, YearIn);
                                ps.setInt(7, RoleID);
                                ps.setBool(8, IsFullTime);
                                ps.setString(9, PersonMatricule);
                                ps.setBool(10, IsCurrentlyActive);
                                ps.setDate(11, ExitDate);
                                return ps;
                        }, keyHolder);
                        return (Integer) keyHolder.getKeys().get("personID");
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid request");
                }
        }

        @Override
        public void update(Integer userID, Integer personID, String PersonName, String PersonAddress,
                        String PersonTelephone,
                        Date PersonDOB, Byte OfUndeclaredSex, Date YearIn, Integer RoleID, Boolean IsFullTime,
                        String PersonMatricule, Boolean IsCurrentlyActive, Date ExitDate, Person person)
                        throws BadRequestException {
                try {
                        jdbcTemplate.update(SQL_PERSON_UPDATE, new Object[] {
                                        person.getPersonName(),
                                        person.getPersonAddress(),
                                        person.getPersonTelephone(),
                                        person.getPersonDOB(),
                                        person.getOfDeclareSex(),
                                        person.getYearIn(),
                                        person.getRoleID(),
                                        person.isFullTime(),
                                        person.getPersonMatricule(),
                                        person.isCurrentlyActive(),
                                        person.getExitDate(),
                                        personID });
                } catch (DataAccessException e) {
                        throw new BadRequestException("Invalid Request");
                }
        }

        @Override
        public void removeById(Integer userID, Integer personID) {
                jdbcTemplate.update(SQL_PERSON_DELETE, new Object[] { userID, personID });
        }

        private RowMapper<Person> personRowMapper = ((rs, rowNum) -> {
                return new Person(
                                rs.getInt("personid"),
                                rs.getString("personname"),
                                rs.getString("personaddress"),
                                rs.getString("persontelephone"),
                                rs.getDate("persondob"),
                                rs.getByte("ofundeclaredsex"),
                                rs.getDate("yearin"),
                                rs.getInt("roleid"),
                                rs.getBoolean("isfulltime"),
                                rs.getString("personmatricule"),
                                rs.getBoolean("iscurrentlyactive"),
                                rs.getDate("exitdate"));
        });

}
