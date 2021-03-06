package trainapp.customer.dao;

import trainapp.customer.Customer;
import trainapp.forum.Message;
import trainapp.forum.SupportTicket;
import trainapp.trainschedule.Reservation;
import trainapp.trainschedule.Station;
import trainapp.trainschedule.Stop;
import trainapp.trainschedule.TrainSchedule;

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
	
	public String getNameOfStation(int id);
	public TrainSchedule[] getTrainSchedules();
	
	public Stop[] getStops(String transitLine);
	public Station[] getStations();
	public Station getStation(int stationID);
	
	public Reservation[] getReservations(String userName);
	public int deleteReservation(Reservation res);
	public int cancelReservation(Reservation res);
	public int numOfStops(TrainSchedule ts);
	
	public int insertReservation(Reservation res);
	
	public TrainSchedule[] getTrainSchedulesByArrival(String origin, String destination, String date);
	public TrainSchedule[] getTrainSchedulesByDeparture(String origin, String destination, String date);
	public TrainSchedule[] getTrainSchedulesByFare(String origin, String destination, String date);
}