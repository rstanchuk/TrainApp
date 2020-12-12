package trainapp.employee.dao;

import trainapp.employee.Employee;
import trainapp.forum.Message;
import trainapp.forum.SupportTicket;
import trainapp.trainschedule.CustomerReport;
import trainapp.trainschedule.TrainSchedule;

public interface EmployeeDAO {
	public int insertEmployee(Employee empl);
	public Employee getEmployee(String username, String pass);
	public boolean doesEmployeeExist(String username);
	
	public SupportTicket[] getSupportTickets();
	public Message[] getMessages(int supportTicketID);
	public int insertMessage(Message msg);
	
	public TrainSchedule[] getTrainSchedules();
	
	public int updateTrainSchedule(String transitLine, String departureTime, String arrivalTime, int originStationID, int destinationStationID, float fare, int trainID);
	public int updateStop(String transitLine, int stopID, String arrivalTime, String departureTime);
	public int deleteStop(int stopID, String transitLine);
	
	public TrainSchedule[] getTrainSchedulesByOrigin(int stationID);
	public TrainSchedule[] getTrainSchedulesByDestination(int stationID);
	public TrainSchedule[] getTrainSchedulesAll(int stationID);
	
	public CustomerReport[] getCustomerReports(String transitLine, String date);
}
