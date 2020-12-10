package trainapp.trainschedule;

public class TrainSchedule {
	private String transitLine;
	private String depTime;
	private String arrivalTime ;
	private int originStation;
	private int desStation;
	private float fare;
	private int tid;
	
	public String getTransitLine() {
		return transitLine;
	}
	public void setTransitLine(String transitLine) {
		this.transitLine = transitLine;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getOriginStation() {
		return originStation;
	}
	public void setOriginStation(int originStation) {
		this.originStation = originStation;
	}
	public int getDesStation() {
		return desStation;
	}
	public void setDesStation(int desStation) {
		this.desStation = desStation;
	}
	public float getFare() {
		return fare;
	}
	public void setFare(float fare) {
		this.fare = fare;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
}
