package trainapp.trainschedule;

public class Station {
	private int stationID;
	private String name;
	private String city;
	private String state;
	
	public int getStationID() {
		return stationID;
	}
	public void setStationID(int stationID) {
		this.stationID = stationID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
