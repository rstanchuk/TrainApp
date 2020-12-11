package trainapp.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trainapp.connection.ConnectionProvider;
import trainapp.employee.Employee;
import trainapp.forum.Message;
import trainapp.forum.SupportTicket;
import trainapp.trainschedule.TrainSchedule;

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

	@Override
	public SupportTicket[] getSupportTickets() {
		SupportTicket[] tickets = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from SupportTicket;");
			
			ResultSet rs = ps.executeQuery();
			int ticketCount = 0;
			while(rs.next()) {
				ticketCount = rs.getInt(1);
			}
			
			try {
				tickets = new SupportTicket[ticketCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select * from SupportTicket");

				rs = ps.executeQuery();
				while(rs.next()) {
					SupportTicket st = new SupportTicket();
					st.setSupportTicketID(rs.getInt(1));
					st.setUserName(rs.getString(2));
					st.setTitle(rs.getString(3));
					st.setBody(rs.getString(4));
					
					tickets[index] = st;
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
		
		
		return tickets;
	}
	
	@Override
	public Message[] getMessages(int supportTicketID) {
		Message[] messages = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from SupportTicketMessage where supportTicketID=?;");
			ps.setString(1, Integer.toString(supportTicketID));
			
			ResultSet rs = ps.executeQuery();
			int messageCount = 0;
			while(rs.next()) {
				messageCount = rs.getInt(1);
			}
			
			try {
				messages = new Message[messageCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select * from SupportTicketMessage where supportTicketID=?;");
				ps.setString(1, Integer.toString(supportTicketID));

				rs = ps.executeQuery();
				while(rs.next()) {
					Message msg = new Message();
					msg.setMessageID(rs.getInt(1));
					msg.setSupportTicketID(rs.getInt(2));
					msg.setUserNameCustomer(rs.getString(3));
					msg.setUserNameEmployee(rs.getString(4));
					msg.setBody(rs.getString(5));
					
					messages[index] = msg;
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
		
		
		return messages;
	}
	
	@Override
	public int insertMessage(Message msg) {
		int status = 0;
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("insert into SupportTicketMessage (supportTicketID, userNameEmployee, body) values (?, ?, ?);");
			
			ps.setInt(1, msg.getSupportTicketID());
			ps.setString(2, msg.getUserNameEmployee());
			ps.setString(3, msg.getBody());

			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	@Override
	public TrainSchedule[] getTrainSchedules() {
		TrainSchedule[] schedules = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from TrainSchedule order by depTime ASC;");
			
			ResultSet rs = ps.executeQuery();
			int scheduleCount = 0;
			while(rs.next()) {
				scheduleCount = rs.getInt(1);
			}
			
			try {
				schedules = new TrainSchedule[scheduleCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select * from TrainSchedule order by depTime ASC;");

				rs = ps.executeQuery();
				while(rs.next()) {
					TrainSchedule ts = new TrainSchedule();
					ts.setTransitLine(rs.getString(1));
					ts.setDepTime(rs.getString(2));
					ts.setArrivalTime(rs.getString(3));
					ts.setOriginStation(rs.getInt(4));
					ts.setDesStation(rs.getInt(5));
					ts.setFare(rs.getFloat(6));
					ts.setTid(rs.getInt(7));

					schedules[index] = ts;
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
		
		return schedules;
	}

	@Override
	public int updateTrainSchedule(String transitLine, String departureTime, String arrivalTime, int originStationID, int destinationStationID, float fare, int trainID) {
		int status = 0;
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("update TrainSchedule\n"
					+ "set depTime = '" + departureTime + "', \n"
					+ "arrivalTime = '" + arrivalTime + "', \n"
					+ "originStation = "+ originStationID +", \n"
					+ "desStation = "+ destinationStationID +", \n"
					+ "fare = "+fare+", \n"
					+ "tid = "+trainID+"\n"
					+ "where transitLine = '"+transitLine+"';");
			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}

	@Override
	public int updateStop(String transitLine, int stopID, String arrivalTime, String departureTime) {
		int status = 0;
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("update Stop\n"
					+ "set departTime = '"+departureTime+"',\n"
					+ "arrivalTime = '"+arrivalTime+"'\n"
					+ "where stationID = "+stopID+" \n"
					+ "and transitLine = '"+transitLine+"';");
			
			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}

	@Override
	public int deleteStop(int stopID, String transitLine) {
		int status = 0;
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("delete from Stop\n"
					+ "where stationID = "+stopID+" \n"
					+ "and \n"
					+ "transitLine = '"+transitLine+"';");
			
			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}

}