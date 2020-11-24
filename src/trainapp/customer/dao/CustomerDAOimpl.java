package trainapp.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trainapp.connection.ConnectionProvider;
import trainapp.customer.Customer;

public class CustomerDAOimpl implements CustomerDAO {
	static Connection con;
	static PreparedStatement ps;

	@Override
	public int insertCustomer(Customer c) {
		int status = 0;
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("insert into Customer values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, c.getUserName());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getEmail());
			ps.setString(4, c.getFirstName());
			ps.setString(5, c.getLastName());
			if(c.isChild() == true) { ps.setString(6, "1"); } else { ps.setString(6, "0"); }
			if(c.isSenior() == true) { ps.setString(7, "1"); } else { ps.setString(7, "0"); }
			if(c.isDisabled() == true) { ps.setString(8, "1"); } else { ps.setString(8, "0"); }
			ps.setString(9, Float.toString(c.getDiscount()));
			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}

	@Override
	public Customer getCustomer(String username, String pass) {
		Customer c = new Customer();
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select * from Customer where userName=? and password=?;");
			ps.setString(1, username);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c.setUserName(rs.getString(1));
				c.setPassword(rs.getString(2));
				c.setEmail(rs.getString(3));
				c.setFirstName(rs.getString(4));
				c.setLastName(rs.getString(5));
				c.setChild(rs.getBoolean(6));
				c.setSenior(rs.getBoolean(7));
				c.setDisabled(rs.getBoolean(8));
				c.setDiscount(rs.getFloat(9));
			}
			con.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		return c;
	}
	
	@Override
	public boolean doesCustomerExist(String username) {
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from Customer where userName=?;");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			String userCount = "0";
			while(rs.next()) {
				userCount = rs.getString(1);
			}
			
			con.close();
			
			if(userCount.charAt(0) == '1') {
				return true;
			} else {
				return false;
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		return false;
	}

}