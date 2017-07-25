package astontech.dao;

import com.astontech.bo.Employee;

import java.util.List;

/**
 * Created by Joshua.McCann on 6/28/2017.
 */
public interface EmployeeDAO {
    //GET METHODS
    public Employee getEmployeeById(int employeeId);
    public List<Employee> getEmployeeList();

    //EXECUTE METHODS
    public int insertEmployee(Employee employee);
    public boolean updateEmployee(Employee employee);
    public boolean deleteEmployee(int employeeId);
}
