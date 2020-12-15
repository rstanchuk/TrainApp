package trainapp.trainschedule;

public class TransitLine {
	private String transitLine;
	private float revenue;
	private int numOfReservations;
	
	public String getTransitLine() {
		return transitLine;
	}
	public void setTransitLine(String transitLine) {
		this.transitLine = transitLine;
	}
	public float getRevenue() {
		return revenue;
	}
	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}
	public int getNumOfReservations() {
		return numOfReservations;
	}
	public void setNumOfReservations(int numOfReservations) {
		this.numOfReservations = numOfReservations;
	}
}
