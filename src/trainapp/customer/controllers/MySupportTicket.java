package trainapp.customer.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainapp.customer.Customer;
import trainapp.customer.dao.CustomerDAO;
import trainapp.customer.dao.CustomerDAOimpl;
import trainapp.forum.Message;
import trainapp.forum.SupportTicket;

@WebServlet("/MySupportTicket")
public class MySupportTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MySupportTicket() {
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
		
		CustomerDAO cd = new CustomerDAOimpl();
		Message[] messages = cd.getMessages(ticket.getSupportTicketID());
		sesh.setAttribute("messages", messages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerViews/supportticket.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesh = request.getSession();
		Customer customer = (Customer) sesh.getAttribute("Customer");
		SupportTicket ticket = (SupportTicket) sesh.getAttribute("ticket");
		
		CustomerDAO cd = new CustomerDAOimpl();
		String body = request.getParameter("body");
		
		if(body.isBlank()) {
			request.setAttribute("id", ticket.getSupportTicketID());
			request.setAttribute("username", ticket.getUserName());
			request.setAttribute("title", ticket.getTitle());
			request.setAttribute("body", ticket.getBody());
			request.setAttribute("message", "Your message is empty!");
			request.getRequestDispatcher("/CustomerViews/supportticket.jsp").forward(request, response);
		} else {
			Message msg = new Message();
			msg.setSupportTicketID(ticket.getSupportTicketID());
			msg.setUserNameCustomer(customer.getUserName());
			msg.setBody(body);
			
			int result = cd.insertMessage(msg);
			
			if(result != 0) {
				response.sendRedirect("/TrainApp/Session");
			} else {
				request.setAttribute("id", ticket.getSupportTicketID());
				request.setAttribute("username", ticket.getUserName());
				request.setAttribute("title", ticket.getTitle());
				request.setAttribute("body", ticket.getBody());
				request.setAttribute("message", "Database error!");
				request.getRequestDispatcher("/CustomerViews/supportticket.jsp").forward(request, response);
			}
		}
	}

}
