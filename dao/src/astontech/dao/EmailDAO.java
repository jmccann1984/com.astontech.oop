package astontech.dao;

import com.astontech.bo.Email;

import java.util.List;

/**
 * Created by Joshua.McCann on 6/28/2017.
 */
public interface EmailDAO {
    //GET METHODS
    public Email getEmailById(int emailId);
    public List<Email> getEmailList();

    //EXECUTE METHODS
    public int insertEmail(Email email);
    public boolean updateEmail(Email email);
    public boolean deleteEmail(int emailId);
}
