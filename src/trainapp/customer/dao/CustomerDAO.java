package trainapp.customer.dao;

import trainapp.customer.Customer;

public interface CustomerDAO {
	public int insertCustomer(Customer c);
	public Customer getCustomer(String username, String pass);
	public boolean doesCustomerExist(String username);
}