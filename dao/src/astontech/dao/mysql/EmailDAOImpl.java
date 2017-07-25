package astontech.dao.mysql;

import astontech.dao.EmailDAO;
import com.astontech.bo.Email;
import com.astontech.bo.Employee;
import com.astontech.bo.EntityType;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 6/28/2017.
 */
public class EmailDAOImpl extends MySQL implements EmailDAO {

    @Override
    public Email getEmailById(int emailId) {
        Connect();
        Email email = null;
        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetEmail);

            cStmnt.setInt(1, GET_BY_ID);
            cStmnt.setInt(2, emailId);

            ResultSet rs = cStmnt.executeQuery();

            if(rs.next()){
                email = new Email();
                email = HydrateEmail(rs);
            }

        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }
        return email;

    }

    @Override
    public List<Email> getEmailList() {
        Connect();
        List<Email> emailList = new ArrayList<Email>();

        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetEmail);

            cStmnt.setInt(1, GET_COLLECTION);
            cStmnt.setInt(2, 0);

            ResultSet rs = cStmnt.executeQuery();

            while(rs.next()){
                emailList.add(HydrateEmail(rs));
            }

        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }

        return emailList;
    }

    @Override
    public int insertEmail(Email email) {
        int id = 0;
        try{
            id = ModifyEmail(INSERT, email);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }
        return id;
    }

    @Override
    public boolean updateEmail(Email email) {
        int id = 0;
        try{
            id = ModifyEmail(UPDATE, email);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }
        return id>0;
    }

    @Override
    public boolean deleteEmail(int emailId) {
        int id = 0;
        Email email = new Email();
        email.setEmailId(emailId);
        email.setEmailType(new EntityType());
        email.setEmailEmployee(new Employee());

        try{
            id = ModifyEmail(DELETE, email);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }
        return id>0;

    }

    public Email HydrateEmail(ResultSet rs) throws SQLException{
        /*
        *   EmailId         int     index 1
        *   EmailAddress    varchar index 2
        *   EmployeeId      int     index 3
        *   EntityTypeId    int     index 4
        */

        Email email = new Email();
        EntityType entityType = new EntityType();
        entityType.setEntityTypeId(rs.getInt(4));

        email.setEmailId(rs.getInt(1));
        email.setEmailAddress(rs.getString(2));
        email.setEmailEmployee(new EmployeeDAOImpl().getEmployeeById(rs.getInt(3)));
        email.setEmailType(entityType);

        return email;
    }

    public static int ModifyEmail(int key, Email email) throws SQLException {
        Connect();
        CallableStatement cStmnt = conn.prepareCall(Procedures.ExecEmail);

        cStmnt.setInt(1,key);
        cStmnt.setString(2,email.getEmailAddress());
        if(email.getEmailEmployee().getEmployeeId()==0)
            cStmnt.setNull(3, Types.INTEGER);
        else
            cStmnt.setInt(3,email.getEmailEmployee().getEmployeeId());
        if(email.getEmailType().getEntityTypeId()==0)
            cStmnt.setNull(4, Types.INTEGER);
        else
            cStmnt.setInt(4,email.getEmailType().getEntityTypeId());
        if(email.getEmailId()==0)
            cStmnt.setNull(5, Types.INTEGER);
        else
            cStmnt.setInt(5,email.getEmailId());

        ResultSet rs = cStmnt.executeQuery();
        if(rs.next())
            return rs.getInt(1);
        else
            return 0;
    }
}
