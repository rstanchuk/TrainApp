package trainapp.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trainapp.connection.ConnectionProvider;
import trainapp.employee.Employee;

public class EmployeeDAOimpl implements EmployeeDAO {
	static Connection con;
	static PreparedStatement ps;
	
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
	public Employee getEmployee(String username, String pass) {
		Employee empl = new Employee();
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select * from Employee where userName=? and password=?;");
			ps.setString(1, username);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {				
				empl.setSSN(rs.getString(1));
				empl.setUserName(rs.getString(2));
				empl.setPassword(rs.getString(3));
				empl.setFirstName(rs.getString(4));
				empl.setLastName(rs.getString(5));
			}
			con.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		return empl;
	}

	@Override
	public boolean doesEmployeeExist(String username) {
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from Employee where userName=?;");
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