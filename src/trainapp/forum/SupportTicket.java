package trainapp.forum;


public class SupportTicket {
	private int supportTicketID;
    private String userName;
    private String title;
    private String body;
    
	public int getSupportTicketID() {
		return supportTicketID;
	}
	public void setSupportTicketID(int supportTicketID) {
		this.supportTicketID = supportTicketID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
