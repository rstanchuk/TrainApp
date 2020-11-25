package trainapp.customer.dao;

import trainapp.customer.Customer;
import trainapp.forum.SupportTicket;

public interface CustomerDAO {
	public int insertCustomer(Customer c);
	public Customer getCustomer(String username, String pass);
	public boolean doesCustomerExist(String username);
	
	public SupportTicket[] getSupportTickets(String username);
}