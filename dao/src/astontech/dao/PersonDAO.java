package astontech.dao;

import com.astontech.bo.Person;

import java.util.List;

/**
 * Created by Joshua.McCann on 6/28/2017.
 */
public interface PersonDAO {

    //Notes: GET METHODS
    public Person getPersonById(int personId);
    public List<Person> getPersonList();


    //Notes: EXECUTE METHODS
    public int insertPerson(Person person);
    public boolean updatePerson(Person person);
    public boolean deletePerson(int personId);
}
