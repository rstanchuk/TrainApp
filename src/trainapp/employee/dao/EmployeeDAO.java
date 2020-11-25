package trainapp.employee.dao;

import trainapp.employee.Employee;
import trainapp.forum.Message;
import trainapp.forum.SupportTicket;

public interface EmployeeDAO {
	public int insertEmployee(Employee empl);
	public Employee getEmployee(String username, String pass);
	public boolean doesEmployeeExist(String username);
	
	public SupportTicket[] getSupportTickets();
	public Message[] getMessages(int supportTicketID);
	public int insertMessage(Message msg);
}
