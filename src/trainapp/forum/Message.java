package trainapp.forum;

public class Message {
	private int messageID;
	private int supportTicketID;
	private String userNameCustomer;
	private String userNameEmployee;
	private String body;
	
	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	public int getSupportTicketID() {
		return supportTicketID;
	}
	public void setSupportTicketID(int supportTicketID) {
		this.supportTicketID = supportTicketID;
	}
	public String getUserNameCustomer() {
		return userNameCustomer;
	}
	public void setUserNameCustomer(String userNameCustomer) {
		this.userNameCustomer = userNameCustomer;
	}
	public String getUserNameEmployee() {
		return userNameEmployee;
	}
	public void setUserNameEmployee(String userNameEmployee) {
		this.userNameEmployee = userNameEmployee;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
