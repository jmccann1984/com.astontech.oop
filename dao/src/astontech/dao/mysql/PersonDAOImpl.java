package astontech.dao.mysql;

import astontech.dao.PersonDAO;
import com.astontech.bo.Person;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 6/28/2017.
 */
public class PersonDAOImpl extends MySQL implements PersonDAO {

    @Override
    public Person getPersonById(int personId) {
        Connect();
        Person person = null; //not instanticated because if no records returned we don't want to use memory
        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetPerson);

            cStmnt.setInt(1, GET_BY_ID);
            cStmnt.setInt(2, personId);
            ResultSet rs = cStmnt.executeQuery();

            if(rs.next()){
                person = new Person();
                person = HydrateObject(rs);
            }
         } catch (SQLException sqlEx){
            logger.error(sqlEx);
        }

        return person;
    }

    @Override
    public List<Person> getPersonList() {
        Connect();
        List<Person> personList = new ArrayList<Person>();
        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetPerson);

            cStmnt.setInt(1, GET_COLLECTION);
            cStmnt.setInt(2, 0);
            ResultSet rs = cStmnt.executeQuery();

            while(rs.next()){
                personList.add(HydrateObject(rs));
            }
        } catch (SQLException sqlEx){
            logger.error(sqlEx);
        }

        return personList;
    }

    @Override
    public int insertPerson(Person person) {
        Connect();
        int id = 0;
        try {
            id = ModifyRecord(INSERT, person);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }

        return id;
    }

    @Override
    public boolean updatePerson(Person person) {
        Connect();
        int id = 0;
        try {
            id = ModifyRecord(UPDATE, person);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }

        return id>0;
    }

    @Override
    public boolean deletePerson(int personId) {
        Connect();
        int id = 0;
        try {
            Person person = new Person();
            person.setPersonId(personId);

            id = ModifyRecord(DELETE, person);

        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }

        return id>0;
    }

    private static Person HydrateObject(ResultSet rs) throws SQLException {
        /*
        * PersonId          int         index 1
        * Title             varchar     index 2
        * FirstName         varchar     index 3
        * LastName          varchar     index 4
        * DisplayFirstName  varchar     index 5
        * Gender            char        index 6
        * CreateDate        Date        index 7
        * IsDeleted         Byte        index 8
        */

        //Notes:    HYDRATING AN OBJECT
        Person person = (new Person());
        person.setTitle(rs.getString(2));
        person.setFirstName(rs.getString(3));
        person.setLastName(rs.getString(4));
        person.setDisplayFirstName(rs.getString(5));
        person.setGender(rs.getString(6).charAt(0));
        person.setPersonId(rs.getInt(1));
        person.setIsDeleted(rs.getByte(8));
        person.setCreateDate(rs.getDate(7));

        return person;
    }

    public static int ModifyRecord(int key, Person person) throws SQLException {
        /*
                    IN queryId			int,
                    IN personId			int,
                    IN title			varchar(10),
                    in firstName		varchar(50),
                    in lastName			varchar(50),
                    in displayFirstName	varchar(50),
                    in gender			char(1))
            */
        CallableStatement cStmnt = conn.prepareCall(Procedures.ExecPerson);

        cStmnt.setInt(1, key);
        cStmnt.setInt(2, person.getPersonId());
        cStmnt.setString(3, person.getTitle());
        cStmnt.setString(4, person.getFirstName());
        cStmnt.setString(5, person.getLastName());
        cStmnt.setString(6, person.getDisplayFirstName());
        cStmnt.setString(7, Character.toString(person.getGender()));

        ResultSet rs = cStmnt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        else
            return 0;
    }
}
