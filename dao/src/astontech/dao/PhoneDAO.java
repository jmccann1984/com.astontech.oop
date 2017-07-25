package astontech.dao;

import com.astontech.bo.Phone;

import java.util.List;

/**
 * Created by Joshua.McCann on 6/28/2017.
 */
public interface PhoneDAO {
    //GET PROCEDURES
    public Phone getPhoneById(int phoneId);
    public List<Phone> getPhoneList();

    //UPDATE PROCEDURES
    public int insertPhone(Phone phone);
    public boolean updatePhone(Phone phone);
    public boolean deletePhone(int phoneId);
}
