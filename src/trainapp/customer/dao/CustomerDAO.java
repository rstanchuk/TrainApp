package trainapp.customer.dao;

import trainapp.customer.Customer;
import trainapp.forum.Message;
import trainapp.forum.SupportTicket;
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
	
	public TrainSchedule[] getTrainSchedulesByOriginOrderByArrival(String origin);
	public TrainSchedule[] getTrainScheduleByOriginOrderByDeparture(String origin);
	public TrainSchedule[] getTrainScheduleByOriginOrderByFare(String origin);
	
	public TrainSchedule[] getTrainScheduleByDestinationOrderByArrival(String destination);
	public TrainSchedule[] getTrainScheduleByDestinationOrderByDeparture(String destination);
	public TrainSchedule[] getTrainScheduleByDestinationOrderByFare(String destination);
	
	public TrainSchedule[] getTrainScheduleByDateOrderByArrival(String date);
	public TrainSchedule[] getTrainScheduleByDateOrderByDeparture(String date);
	public TrainSchedule[] getTrainScheduleByDateOrderByFare(String date);
	
	public String getNameOfStation(int id);
	public TrainSchedule[] getTrainSchedules();
	
	public Stop[] getStops(String transitLine);
	public Station getStation(int stationID);

}