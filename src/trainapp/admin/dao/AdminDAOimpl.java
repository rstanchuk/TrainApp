package trainapp.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trainapp.connection.ConnectionProvider;

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

}
