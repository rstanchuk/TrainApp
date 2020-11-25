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
import trainapp.forum.SupportTicket;

@WebServlet("/CreateSupportTicket")
public class CreateSupportTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateSupportTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");
		
		request.setAttribute("username", customer.getUserName());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerViews/createsupportticket.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");
		
		CustomerDAO cd = new CustomerDAOimpl();
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		if(title.isBlank() || body.isBlank()) {
			request.setAttribute("message", "All fields must be filled out!");
			request.getRequestDispatcher("/CustomerViews/createsupportticket.jsp").forward(request, response);
		} else {
			SupportTicket ticket = new SupportTicket();
			ticket.setUserName(customer.getUserName());
			ticket.setTitle(title);
			ticket.setBody(body);
			
			int result = cd.insertSupportTicket(ticket);
			
			if(result != 0) {
				response.sendRedirect("/TrainApp/Session");
			} else {
				request.setAttribute("message", "Database error!");
				request.getRequestDispatcher("/CustomerViews/createsupportticket.jsp").forward(request, response);
			}
		}
	}

}
