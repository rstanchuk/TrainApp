package trainapp.trainschedule;

public class Reservation {
	private int reservationID;
    private String userName;
    private String transitLine;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private boolean roundTrip;
    private float price;
    private boolean current;
    
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTransitLine() {
		return transitLine;
	}
	public void setTransitLine(String transitLine) {
		this.transitLine = transitLine;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public boolean isRoundTrip() {
		return roundTrip;
	}
	public void setRoundTrip(boolean roundTrip) {
		this.roundTrip = roundTrip;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isCurrent() {
		return current;
	}
	public void setCurrent(boolean current) {
		this.current = current;
	}
}
