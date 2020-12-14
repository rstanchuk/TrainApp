package trainapp.admin.dao;

import trainapp.employee.Employee;

public interface AdminDAO {
	public boolean verifyAdmin(String password);
	
	public int updateEmployee(String userName, String firstName, String lastName);
	public int insertEmployee(Employee empl);
	public int deleteEmployee(Employee empl);
	public Employee[] getEmployees();
	
}
