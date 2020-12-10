package trainapp.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trainapp.connection.ConnectionProvider;
import trainapp.customer.Customer;
import trainapp.forum.Message;
import trainapp.forum.SupportTicket;
import trainapp.trainschedule.Station;
import trainapp.trainschedule.Stop;
import trainapp.trainschedule.TrainSchedule;

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
	
	@Override
	public SupportTicket[] getSupportTickets(String username) {
		SupportTicket[] tickets = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from SupportTicket where userName=?;");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			int ticketCount = 0;
			while(rs.next()) {
				ticketCount = rs.getInt(1);
			}
			
			try {
				tickets = new SupportTicket[ticketCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select * from SupportTicket where userName=?;");
				ps.setString(1, username);

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
	public int deleteSupportTicket(SupportTicket ticket) {
		int status = 0;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("delete from SupportTicket where supportTicketID=?;");
			ps.setString(1, Integer.toString(ticket.getSupportTicketID()));

			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	@Override
	public int insertSupportTicket(SupportTicket ticket) {
		int status = 0;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("insert into SupportTicket (userName, title, body) values (?, ?, ?);");
			ps.setString(1, ticket.getUserName());
			ps.setString(2, ticket.getTitle());
			ps.setString(3, ticket.getBody());

			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	@Override
	public SupportTicket[] searchByKeywordSupportTickets(String keyword) {
		SupportTicket[] tickets = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from SupportTicket where title like '%" + keyword + "%';");
			
			ResultSet rs = ps.executeQuery();
			int ticketCount = 0;
			while(rs.next()) {
				ticketCount = rs.getInt(1);
			}
			
			try {
				tickets = new SupportTicket[ticketCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select * from SupportTicket where title like '%" + keyword + "%';");

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
			ps = con.prepareStatement("insert into SupportTicketMessage (supportTicketID, userNameCustomer, body) values (?, ?, ?);");
			
			ps.setInt(1, msg.getSupportTicketID());
			ps.setString(2, msg.getUserNameCustomer());
			ps.setString(3, msg.getBody());

			status = ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}

	@Override
	public TrainSchedule[] getTrainSchedulesByOriginOrderByArrival(String origin) {
		TrainSchedule[] schedules = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from TrainSchedule ts, Station s where ts.originStation = s.stationID and s.name = ? order by ts.arrivalTime ASC;");
			ps.setString(1, origin);
			
			ResultSet rs = ps.executeQuery();
			int scheduleCount = 0;
			while(rs.next()) {
				scheduleCount = rs.getInt(1);
			}
			
			try {
				schedules = new TrainSchedule[scheduleCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select ts.* from TrainSchedule ts, Station s where ts.originStation = s.stationID and s.name = ? order by ts.arrivalTime ASC;");
				ps.setString(1, origin);

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
	public TrainSchedule[] getTrainScheduleByOriginOrderByDeparture(String origin) {
		TrainSchedule[] schedules = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from TrainSchedule ts, Station s where ts.originStation = s.stationID and s.name = ? order by ts.depTime ASC;");
			ps.setString(1, origin);
			
			ResultSet rs = ps.executeQuery();
			int scheduleCount = 0;
			while(rs.next()) {
				scheduleCount = rs.getInt(1);
			}
			
			try {
				schedules = new TrainSchedule[scheduleCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select ts.* from TrainSchedule ts, Station s where ts.originStation = s.stationID and s.name = ? order by ts.depTime ASC;");
				ps.setString(1, origin);

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
	public TrainSchedule[] getTrainScheduleByOriginOrderByFare(String origin) {
		TrainSchedule[] schedules = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from TrainSchedule ts, Station s where ts.originStation = s.stationID and s.name = ? order by ts.fare ASC;");
			ps.setString(1, origin);
			
			ResultSet rs = ps.executeQuery();
			int scheduleCount = 0;
			while(rs.next()) {
				scheduleCount = rs.getInt(1);
			}
			
			try {
				schedules = new TrainSchedule[scheduleCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select ts.* from TrainSchedule ts, Station s where ts.originStation = s.stationID and s.name = ? order by ts.fare ASC;");
				ps.setString(1, origin);

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
	public TrainSchedule[] getTrainScheduleByDestinationOrderByArrival(String destination) {
		TrainSchedule[] schedules = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from TrainSchedule ts, Station s where ts.desStation = s.stationID and s.name = ? order by ts.arrivalTime ASC;");
			ps.setString(1, destination );
			
			ResultSet rs = ps.executeQuery();
			int scheduleCount = 0;
			while(rs.next()) {
				scheduleCount = rs.getInt(1);
			}
			
			try {
				schedules = new TrainSchedule[scheduleCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select ts.* from TrainSchedule ts, Station s where ts.desStation = s.stationID and s.name = ? order by ts.arrivalTime ASC;");
				ps.setString(1, destination);

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
	public TrainSchedule[] getTrainScheduleByDestinationOrderByDeparture(String destination) {
		TrainSchedule[] schedules = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from TrainSchedule ts, Station s where ts.desStation = s.stationID and s.name = ? order by ts.depTime ASC;");
			ps.setString(1, destination );
			
			ResultSet rs = ps.executeQuery();
			int scheduleCount = 0;
			while(rs.next()) {
				scheduleCount = rs.getInt(1);
			}
			
			try {
				schedules = new TrainSchedule[scheduleCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select ts.* from TrainSchedule ts, Station s where ts.desStation = s.stationID and s.name = ? order by ts.depTime ASC;");
				ps.setString(1, destination);

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
	public TrainSchedule[] getTrainScheduleByDestinationOrderByFare(String destination) {
		TrainSchedule[] schedules = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from TrainSchedule ts, Station s where ts.desStation = s.stationID and s.name = ? order by ts.fare ASC;");
			ps.setString(1, destination );
			
			ResultSet rs = ps.executeQuery();
			int scheduleCount = 0;
			while(rs.next()) {
				scheduleCount = rs.getInt(1);
			}
			
			try {
				schedules = new TrainSchedule[scheduleCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select ts.* from TrainSchedule ts, Station s where ts.desStation = s.stationID and s.name = ? order by ts.fare ASC;");
				ps.setString(1, destination);

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
	public TrainSchedule[] getTrainScheduleByDateOrderByArrival(String date) {
		TrainSchedule[] schedules = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from TrainSchedule where depTime like '%" + date + "%' order by arrivalTime ASC;");
			
			ResultSet rs = ps.executeQuery();
			int scheduleCount = 0;
			while(rs.next()) {
				scheduleCount = rs.getInt(1);
			}
			
			try {
				schedules = new TrainSchedule[scheduleCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select * from TrainSchedule where depTime like '%" + date + "%' order by arrivalTime ASC;");

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
	public TrainSchedule[] getTrainScheduleByDateOrderByDeparture(String date) {
		TrainSchedule[] schedules = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from TrainSchedule where depTime like '%" + date + "%' order by depTime ASC;");
			
			ResultSet rs = ps.executeQuery();
			int scheduleCount = 0;
			while(rs.next()) {
				scheduleCount = rs.getInt(1);
			}
			
			try {
				schedules = new TrainSchedule[scheduleCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select * from TrainSchedule where depTime like '%" + date + "%' order by depTime ASC;");

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
	public TrainSchedule[] getTrainScheduleByDateOrderByFare(String date) {
		TrainSchedule[] schedules = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from TrainSchedule where depTime like '%" + date + "%' order by fare ASC;");
			
			ResultSet rs = ps.executeQuery();
			int scheduleCount = 0;
			while(rs.next()) {
				scheduleCount = rs.getInt(1);
			}
			
			try {
				schedules = new TrainSchedule[scheduleCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select * from TrainSchedule where depTime like '%" + date + "%' order by fare ASC;");

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
	public String getNameOfStation(int id) {
		String name = null;
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select name from Station where stationID = ?;");
			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				name = rs.getString(1);
			}
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return name;
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
	public Stop[] getStops(String transitLine) {
		Stop[] stops = null;
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select count(*) from Stop where transitLine = ?;");
			ps.setString(1, transitLine);
			
			ResultSet rs = ps.executeQuery();
			int stopCount = 0;
			while(rs.next()) {
				stopCount = rs.getInt(1);
			}
			
			try {
				stops = new Stop[stopCount];
				int index = 0;
				con = ConnectionProvider.getCon();
				ps = con.prepareStatement("select * from Stop where transitLine = ?;");
				ps.setString(1, transitLine);
				
				rs = ps.executeQuery();
				while(rs.next()) {
					Stop st = new Stop();
					st.setTransitLine(rs.getString(1));
					st.setStationID(rs.getInt(2));
					st.setDepartTime(rs.getString(3));
					st.setArrivalTime(rs.getString(4));

					stops[index] = st;
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
		
		return stops;
	}

	@Override
	public Station getStation(int stationID) {
		Station s = new Station();
		
		try {
			con = ConnectionProvider.getCon();
			ps = con.prepareStatement("select * from Station where stationID = ?;");
			ps.setString(1, Integer.toString(stationID));
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				s.setStationID(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setCity(rs.getString(3));
				s.setState(rs.getString(4));
			}
			con.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return s;
	}
}