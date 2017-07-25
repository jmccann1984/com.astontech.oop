package astontech.dao.mysql;

import astontech.dao.EmployeeDAO;
import com.astontech.bo.Employee;
import com.astontech.bo.Person;
import common.helpers.DateHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 6/28/2017.
 */
public class EmployeeDAOImpl extends MySQL implements EmployeeDAO {

    @Override
    public Employee getEmployeeById(int employeeId) {
        Connect();
        Employee employee = null;

        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetEmployee);

            cStmnt.setInt(1, GET_BY_ID);
            cStmnt.setInt(2, employeeId);
            cStmnt.setInt(3, 0);

            ResultSet rs = cStmnt.executeQuery();

            if(rs.next()){
                employee = new Employee();
                employee = HydrateEmployee(rs);
            }

        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }

        return employee;
    }

    @Override
    public List<Employee> getEmployeeList() {
        Connect();
        List<Employee> employeeList = new ArrayList<Employee>();

        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetEmployee);

            cStmnt.setInt(1, GET_COLLECTION);
            cStmnt.setInt(2, 0);
            cStmnt.setInt(3, 0);

            ResultSet rs = cStmnt.executeQuery();

            while(rs.next()){
                employeeList.add(HydrateEmployee(rs));
            }

        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }

        return employeeList;
    }

    @Override
    public int insertEmployee(Employee employee) {
        int id;
        try{
            id = ModifyEmployee(INSERT, employee);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
            id = 0;
        }
        return id;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        int id;
        try{
            id = ModifyEmployee(UPDATE, employee);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
            id = 0;
        }
        return id>0;
    }

    @Override
    public boolean deleteEmployee(int employeeId) {
        int id;
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        try{
            id = ModifyEmployee(DELETE, employee);
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
            id = 0;
        }
        return id>0;
    }

    public Employee HydrateEmployee(ResultSet rs) throws SQLException {
        /*
        *   EmployeeId      int     index 1
        *   HireDate        Date    index 2
        *   TermDate        Date    index 3
        *   BirthDate       Date    index 4
        *   PersonId        int     index 5
        *   CreateDate      Date    index 6
        */

        Person person = new PersonDAOImpl().getPersonById(rs.getInt(5));

        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt(1));
        employee.setHireDate(rs.getDate(2));
        employee.setTermDate(rs.getDate(3));
        employee.setBirthDate(rs.getDate(4));
        employee.setPersonId(rs.getInt(5));
        employee.setFirstName(person.getFirstName());
        employee.setLastName(person.getLastName());
        employee.setDisplayFirstName(person.getDisplayFirstName());
        employee.setCreateDate(rs.getDate(6));

        return employee;
    }

    public static int ModifyEmployee(int key, Employee employee) throws SQLException{
        Connect();
        CallableStatement cStmnt = conn.prepareCall(Procedures.ExecEmployee);

        cStmnt.setInt(1,key);
        if(employee.getEmployeeId()==0)
            cStmnt.setNull(2, Types.INTEGER);
        else
            cStmnt.setInt(2,employee.getEmployeeId());
        if(employee.getHireDate()==null)
            cStmnt.setNull(3,Types.DATE);
        else
            cStmnt.setDate(3,DateHelper.utilDateToSqlDate(employee.getHireDate()));
        if(employee.getTermDate()==null)
            cStmnt.setNull(4, Types.DATE);
        else
            cStmnt.setDate(4,DateHelper.utilDateToSqlDate(employee.getTermDate()));
        if(employee.getBirthDate()==null)
            cStmnt.setNull(5, Types.DATE);
        else
            cStmnt.setDate(5,DateHelper.utilDateToSqlDate(employee.getBirthDate()));
        if(employee.getPersonId()==0)
            cStmnt.setNull(6, Types.INTEGER);
        else
            cStmnt.setInt(6,employee.getPersonId());

        ResultSet rs = cStmnt.executeQuery();
        if(rs.next())
            return rs.getInt(1);
        else
            return 0;
    }
}
