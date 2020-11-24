package trainapp.employee.dao;

import trainapp.employee.Employee;

public interface EmployeeDAO {
	public int insertEmployee(Employee empl);
	public Employee getEmployee(String username, String pass);
	public boolean doesEmployeeExist(String username);
}
