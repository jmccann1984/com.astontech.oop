package astontech.dao.mysql;

import astontech.dao.PhoneDAO;
import com.astontech.bo.EntityType;
import com.astontech.bo.Phone;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 6/28/2017.
 */
public class PhoneDAOImpl extends MySQL implements PhoneDAO{

    @Override
    public Phone getPhoneById(int phoneId) {
        Connect();
        Phone phone = null;
        try {
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetPhone);

            cStmnt.setInt(1,GET_BY_ID);
            cStmnt.setInt(2,phoneId);

            ResultSet rs = cStmnt.executeQuery();

            if(rs.next()){
                phone = new Phone();
                phone = HydratePhone(rs);
            }

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return phone;
    }

    @Override
    public List<Phone> getPhoneList() {
        Connect();
        List<Phone> phoneList = new ArrayList<Phone>();
        try {
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetPhone);

            cStmnt.setInt(1,GET_COLLECTION);
            cStmnt.setInt(2,0);

            ResultSet rs = cStmnt.executeQuery();

            while(rs.next()){
                phoneList.add(HydratePhone(rs));
            }

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return phoneList;
    }

    @Override
    public int insertPhone(Phone phone) {
        int id;
        try{
            id = ModifyPhone(INSERT, phone);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
            return 0;
        }
        return id;
    }

    @Override
    public boolean updatePhone(Phone phone) {
        int id;
        try{
            id = ModifyPhone(UPDATE, phone);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
            id = 0;
        }
        return id>0;
    }

    @Override
    public boolean deletePhone(int phoneId) {

        int id;
        Phone phone = new Phone();
        phone.setPhoneId(phoneId);
        try{
            id = ModifyPhone(DELETE, phone);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
            id = 0;
        }
        return id>0;
    }

    private static Phone HydratePhone(ResultSet rs) throws SQLException{
        /*
        * PhoneId           int         index 1
        * EntityTypeId      int         index 2
        * ClientId          int         index 3
        * PersonId          int         index 4
        * AreaCode          int         index 5
        * PhoneNumber       int         index 6
        * PhoneNumberPost   int         index 7
        */

        EntityType entityType = new EntityType();
        entityType.setEntityTypeId(rs.getInt(2));

        Phone phone = new Phone();
        phone.setPhoneId(rs.getInt(1));
        phone.setPhoneType(entityType);
        phone.setPhoneClient(new ClientDAOImpl().getClientById(rs.getInt(3)));
        phone.setPhonePerson(new PersonDAOImpl().getPersonById(rs.getInt(4)));
        phone.setAreaCode(rs.getInt(5));
        phone.setPhoneNumber(rs.getInt(6));
        phone.setPhoneNumberPost(rs.getInt(7));

        return phone;
    }

    public static int ModifyPhone(int key, Phone phone) throws SQLException{
        Connect();
        //`usp_ExecutePhone`(queryId, phoneId, entityTypeId, clientId, personId, areaCode, phoneNumber, phoneNumberPost)
        CallableStatement cStmnt = conn.prepareCall(Procedures.ExecPhone);

        cStmnt.setInt(1, key);
        if(phone.getPhoneId()==0)
            cStmnt.setNull(2, Types.INTEGER);
        else
            cStmnt.setInt(2, phone.getPhoneId());

        if(phone.getPhoneType().getEntityTypeId()==0)
            cStmnt.setNull(3, Types.INTEGER);
        else
            cStmnt.setInt(3, phone.getPhoneType().getEntityTypeId());

        if(phone.getPhoneClient().getClientId()==0)
            cStmnt.setNull(4, Types.INTEGER);
        else
            cStmnt.setInt(4, phone.getPhoneClient().getClientId());

        if(phone.getPhonePerson().getPersonId()==0)
            cStmnt.setNull(5, Types.INTEGER);
        else
           cStmnt.setInt(5, phone.getPhonePerson().getPersonId());

        if(phone.getAreaCode()==0)
            cStmnt.setNull(6, Types.INTEGER);
        else
            cStmnt.setInt(6, phone.getAreaCode());

        if(phone.getPhoneNumber()==0)
            cStmnt.setNull(7, Types.INTEGER);
        else
            cStmnt.setInt(7, phone.getPhoneNumber());

        if(phone.getPhoneNumberPost()==0)
            cStmnt.setNull(8, Types.INTEGER);
        else
            cStmnt.setInt(8, phone.getPhoneNumberPost());

        ResultSet rs = cStmnt.executeQuery();
        if(rs.next())
            return rs.getInt(1);
        else
            return 0;
    }
}
