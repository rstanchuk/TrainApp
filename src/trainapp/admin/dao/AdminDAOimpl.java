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

	@Override
	public Employee[] getEmployees() {
		Employee[] employees = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*)\n"
					+ "from Employee;");
			
			ResultSet rs = ps.executeQuery();
			int employeeCount = 0;
			while(rs.next()) {
				employeeCount = rs.getInt(1);
			}
			
			try {
				employees = new Employee[employeeCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select *\n"
						+ "from Employee;");

				rs = ps.executeQuery();
				while(rs.next()) {
					Employee empl = new Employee();
					empl.setSSN(rs.getString(1));
					empl.setUserName(rs.getString(2));
					empl.setPassword(rs.getString(3));
					empl.setFirstName(rs.getString(4));
					empl.setLastName(rs.getString(5));
					
					employees[index] = empl;
					index++;
				}
				con.close();
				
			} catch(Exception e) {
				System.out.println(e);
			}
			
			con.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		return employees;
	}

}
