package trainapp.employee.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainapp.employee.Employee;
import trainapp.employee.dao.EmployeeDAO;
import trainapp.employee.dao.EmployeeDAOimpl;
import trainapp.forum.Message;
import trainapp.forum.SupportTicket;

@WebServlet("/EmySupportTicket")
public class EmySupportTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmySupportTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesh = request.getSession();
		SupportTicket ticket = (SupportTicket) sesh.getAttribute("ticket");
		
		request.setAttribute("id", ticket.getSupportTicketID());
		request.setAttribute("username", ticket.getUserName());
		request.setAttribute("title", ticket.getTitle());
		request.setAttribute("body", ticket.getBody());
		
		EmployeeDAO cd = new EmployeeDAOimpl();
		Message[] messages = cd.getMessages(ticket.getSupportTicketID());
		sesh.setAttribute("messages", messages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeViews/supportticket.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesh = request.getSession();
		Employee employee = (Employee) sesh.getAttribute("Employee");
		SupportTicket ticket = (SupportTicket) sesh.getAttribute("ticket");
		
		EmployeeDAO ed = new EmployeeDAOimpl();
		String body = request.getParameter("body");
		
		if(body.isBlank()) {
			request.setAttribute("id", ticket.getSupportTicketID());
			request.setAttribute("username", ticket.getUserName());
			request.setAttribute("title", ticket.getTitle());
			request.setAttribute("body", ticket.getBody());
			request.setAttribute("message", "Your message is empty!");
			request.getRequestDispatcher("/EmployeeViews/supportticket.jsp").forward(request, response);
		} else {
			Message msg = new Message();
			msg.setSupportTicketID(ticket.getSupportTicketID());
			msg.setUserNameEmployee(employee.getUserName());
			msg.setBody(body);
			
			int result = ed.insertMessage(msg);
			
			if(result != 0) {
				response.sendRedirect("/TrainApp/Esession");
			} else {
				request.setAttribute("id", ticket.getSupportTicketID());
				request.setAttribute("username", ticket.getUserName());
				request.setAttribute("title", ticket.getTitle());
				request.setAttribute("body", ticket.getBody());
				request.setAttribute("message", "Database error!");
				request.getRequestDispatcher("/EmployeeViews/supportticket.jsp").forward(request, response);
			}
		}
	}

}
