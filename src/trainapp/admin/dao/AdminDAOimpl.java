package trainapp.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trainapp.connection.ConnectionProvider;
import trainapp.employee.Employee;
import trainapp.trainschedule.CustomerRevenueReport;
import trainapp.trainschedule.Reservation;
import trainapp.trainschedule.RevenueReport;
import trainapp.trainschedule.TransitLine;

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

	@Override
	public RevenueReport[] obtainSalesReports(String date) {
		RevenueReport[] reports = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*)\n"
					+ "from (select transitLine, sum(price)\n"
					+ "from Reservation\n"
					+ "where departureTime like '%"+date+"%'\n"
					+ "group by transitLine) t;");
			
			ResultSet rs = ps.executeQuery();
			int reportCount = 0;
			while(rs.next()) {
				reportCount = rs.getInt(1);
			}
			
			try {
				reports = new RevenueReport[reportCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select transitLine, sum(price)\n"
						+ "from Reservation\n"
						+ "where departureTime like '%"+date+"%'\n"
						+ "group by transitLine;");

				rs = ps.executeQuery();
				while(rs.next()) {
					RevenueReport report = new RevenueReport();
					report.setTransitLine(rs.getString(1));
					report.setRevenue(rs.getFloat(2));
					
					reports[index] = report;
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
		
		
		return reports;
	}
	
	@Override
	public float obtainTotalSales(String date) {
		float total = 0;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select sum(t.sales)\n"
					+ "from (select transitLine, sum(price) sales\n"
					+ "from Reservation\n"
					+ "where departureTime like '%"+date+"%') t;");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				total = rs.getFloat(1);
			}
			
			con.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return total;
	}

	@Override
	public Reservation[] obtainReservationsByLine(String line) {
		Reservation[] reservations = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*)\n"
					+ "from Reservation\n"
					+ "where transitLine = '"+line+"';");
			
			ResultSet rs = ps.executeQuery();
			int resCount = 0;
			while(rs.next()) {
				resCount = rs.getInt(1);
			}
			
			try {
				reservations = new Reservation[resCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select *\n"
						+ "from Reservation\n"
						+ "where transitLine = '"+line+"';");
				
				rs = ps.executeQuery();
				while(rs.next()) {
					Reservation st = new Reservation();
					st.setReservationID(rs.getInt(1));
					st.setUserName(rs.getString(2));
					st.setTransitLine(rs.getString(3));
					st.setOrigin(rs.getString(4));
					st.setDestination(rs.getString(5));
					st.setDepartureTime(rs.getString(6));
					st.setArrivalTime(rs.getString(7));
					st.setRoundTrip(rs.getBoolean(8));
					st.setPrice(rs.getFloat(9));
					st.setCurrent(rs.getBoolean(10));
					
					reservations[index] = st;
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
		
		return reservations;
	}

	@Override
	public Reservation[] obtainReservationsByName(String firstName, String lastName) {
		Reservation[] reservations = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*)\n"
					+ "from Reservation r, Customer c\n"
					+ "where r.userName = c.userName and c.firstName = '"+firstName+"' and c.lastName = '"+lastName+"';");
			
			ResultSet rs = ps.executeQuery();
			int resCount = 0;
			while(rs.next()) {
				resCount = rs.getInt(1);
			}
			
			try {
				reservations = new Reservation[resCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select r.*\n"
						+ "from Reservation r, Customer c\n"
						+ "where r.userName = c.userName and c.firstName = '"+firstName+"' and c.lastName = '"+lastName+"';");
				
				rs = ps.executeQuery();
				while(rs.next()) {
					Reservation st = new Reservation();
					st.setReservationID(rs.getInt(1));
					st.setUserName(rs.getString(2));
					st.setTransitLine(rs.getString(3));
					st.setOrigin(rs.getString(4));
					st.setDestination(rs.getString(5));
					st.setDepartureTime(rs.getString(6));
					st.setArrivalTime(rs.getString(7));
					st.setRoundTrip(rs.getBoolean(8));
					st.setPrice(rs.getFloat(9));
					st.setCurrent(rs.getBoolean(10));
					
					reservations[index] = st;
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
		
		return reservations;
	}

	@Override
	public Reservation[] getReservations() {
		Reservation[] reservations = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*)\n"
					+ "from Reservation;");
			
			ResultSet rs = ps.executeQuery();
			int resCount = 0;
			while(rs.next()) {
				resCount = rs.getInt(1);
			}
			
			try {
				reservations = new Reservation[resCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select *\n"
						+ "from Reservation;");
				
				rs = ps.executeQuery();
				while(rs.next()) {
					Reservation st = new Reservation();
					st.setReservationID(rs.getInt(1));
					st.setUserName(rs.getString(2));
					st.setTransitLine(rs.getString(3));
					st.setOrigin(rs.getString(4));
					st.setDestination(rs.getString(5));
					st.setDepartureTime(rs.getString(6));
					st.setArrivalTime(rs.getString(7));
					st.setRoundTrip(rs.getBoolean(8));
					st.setPrice(rs.getFloat(9));
					st.setCurrent(rs.getBoolean(10));
					
					reservations[index] = st;
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
		
		return reservations;
	}

	@Override
	public RevenueReport[] getRevenueForLine() {
		RevenueReport[] reports = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from (select transitLine, sum(price) from Reservation group by transitLine)t;");
			
			ResultSet rs = ps.executeQuery();
			int reportCount = 0;
			while(rs.next()) {
				reportCount = rs.getInt(1);
			}
			
			try {
				reports = new RevenueReport[reportCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select transitLine, sum(price) from Reservation group by transitLine;");

				rs = ps.executeQuery();
				while(rs.next()) {
					RevenueReport report = new RevenueReport();
					report.setTransitLine(rs.getString(1));
					report.setRevenue(rs.getFloat(2));
					
					reports[index] = report;
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
		
		
		return reports;
	}

	@Override
	public float getRevenueTotal() {
		float total = 0;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select sum(revenue) from \n"
					+ "(select transitLine, sum(price) revenue from Reservation group by transitLine)t;");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				total = rs.getFloat(1);
			}
			
			con.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return total;
	}

	@Override
	public CustomerRevenueReport[] getRevenueForCustomer() {
		CustomerRevenueReport[] reports = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from (select c.firstName, c.lastName, c.userName, sum(price) \n"
					+ "from Reservation r, Customer c \n"
					+ "where r.userName = c.userName \n"
					+ "group by c.firstName, c.lastName, c.userName)t;");
			
			ResultSet rs = ps.executeQuery();
			int reportCount = 0;
			while(rs.next()) {
				reportCount = rs.getInt(1);
			}
			
			try {
				reports = new CustomerRevenueReport[reportCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select c.firstName, c.lastName, c.userName, sum(price) \n"
						+ "from Reservation r, Customer c \n"
						+ "where r.userName = c.userName \n"
						+ "group by c.firstName, c.lastName, c.userName;");

				rs = ps.executeQuery();
				while(rs.next()) {
					CustomerRevenueReport report = new CustomerRevenueReport();
					report.setFirstName(rs.getString(1));
					report.setLastName(rs.getString(2));
					report.setUserName(rs.getString(3));
					report.setRevenue(rs.getFloat(4));
					
					reports[index] = report;
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
		
		
		return reports;
	}

	@Override
	public CustomerRevenueReport getBestCustomer() {
		CustomerRevenueReport report = null;	
		try {
			report = new CustomerRevenueReport();
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select c.firstName, c.lastName, c.userName, sum(price) \n"
					+ "from Reservation r, Customer c \n"
					+ "where r.userName = c.userName \n"
					+ "group by c.firstName, c.lastName, c.userName;");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				report = new CustomerRevenueReport();
				report.setFirstName(rs.getString(1));
				report.setLastName(rs.getString(2));
				report.setUserName(rs.getString(3));
				report.setRevenue(rs.getFloat(4));
			}
			con.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}		
		
		return report;
	}

	@Override
	public TransitLine[] getBestTransitLines() {
		TransitLine[] transitLines = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from (\n"
					+ "select transitLine, sum(price), count(*)\n"
					+ "from Reservation \n"
					+ "group by transitLine \n"
					+ "order by count(*) DESC\n"
					+ "limit 5) t;");
			
			ResultSet rs = ps.executeQuery();
			int tsCount = 0;
			while(rs.next()) {
				tsCount = rs.getInt(1);
			}
			
			try {
				transitLines = new TransitLine[tsCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select transitLine, sum(price), count(*)\n"
						+ "from Reservation \n"
						+ "group by transitLine \n"
						+ "order by count(*) DESC\n"
						+ "limit 5;");

				rs = ps.executeQuery();
				while(rs.next()) {
					TransitLine line = new TransitLine();
					line.setTransitLine(rs.getString(1));
					line.setRevenue(rs.getFloat(2));
					line.setNumOfReservations(rs.getInt(3));
					
					transitLines[index] = line;
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
		
		
		return transitLines;
	}


}
