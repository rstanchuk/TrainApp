package trainapp.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trainapp.connection.ConnectionProvider;
import trainapp.employee.Employee;

public class AdminDAOimpl implements AdminDAO {
	static Connection con;
	static PreparedStatement ps;
	
	@Override
	public boolean verifyAdmin(String password) {
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select * from Admin;");
			
			ResultSet rs = ps.executeQuery();
			String righPassword = null;
			while(rs.next()) {
				righPassword = rs.getString(1);
			}
			
			con.close();
			
			if(righPassword.equals(password)) {
				return true;
			} else {
				return false;
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return false;
	}

	@Override
	public int updateEmployee(String userName, String firstName, String lastName) {
		int status = 0;
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("update Employee\n"
					+ "set firstName = '"+firstName+"', \n"
					+ "lastName = '"+lastName+"'\n"
					+ "where userName = '"+userName+"';");
			
			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}

	@Override
	public int insertEmployee(Employee empl) {
		int status = 0;
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("insert into Employee values(?, ?, ?, ?, ?);");
			ps.setString(1, empl.getSSN());
			ps.setString(2, empl.getUserName());
			ps.setString(3, empl.getPassword());
			ps.setString(4, empl.getFirstName());
			ps.setString(5, empl.getLastName());
			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}

	@Override
	public int deleteEmployee(Employee empl) {
		int status = 0;
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("delete from Employee where userName = '"+empl.getUserName()+"';");
			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}

}
