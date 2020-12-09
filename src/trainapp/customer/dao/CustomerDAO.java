package trainapp.customer.dao;

import trainapp.customer.Customer;
import trainapp.forum.Message;
import trainapp.forum.SupportTicket;

public interface CustomerDAO {
	public int insertCustomer(Customer c);
	public Customer getCustomer(String username, String pass);
	public boolean doesCustomerExist(String username);
	
	public SupportTicket[] getSupportTickets(String username);
	public int deleteSupportTicket(SupportTicket ticket);
	public int insertSupportTicket(SupportTicket ticket);
	public SupportTicket[] searchByKeywordSupportTickets(String keyword);
	
	public Message[] getMessages(int supportTicketID);
	public int insertMessage(Message msg);
}