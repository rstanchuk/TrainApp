package trainapp.admin.dao;

import trainapp.employee.Employee;
import trainapp.trainschedule.CustomerRevenueReport;
import trainapp.trainschedule.Reservation;
import trainapp.trainschedule.RevenueReport;
import trainapp.trainschedule.TransitLine;

public interface AdminDAO {
	public boolean verifyAdmin(String password);
	
	public int updateEmployee(String userName, String firstName, String lastName);
	public int insertEmployee(Employee empl);
	public int deleteEmployee(Employee empl);
	public Employee[] getEmployees();
	
	public RevenueReport[] obtainSalesReports(String date);
	public float obtainTotalSales(String date);
	
	public Reservation[] obtainReservationsByLine(String line);
	public Reservation[] obtainReservationsByName(String firstName, String lastName);
	public Reservation[] getReservations();
	
	public RevenueReport[] getRevenueForLine();
	public float getRevenueTotal();
	public CustomerRevenueReport[] getRevenueForCustomer();
	
	public CustomerRevenueReport getBestCustomer();
	public TransitLine[] getBestTransitLines();
}
