package trainapp.customer.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerViews/supportticket.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
