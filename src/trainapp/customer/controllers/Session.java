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

@WebServlet("/Session")
public class Session extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Session() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Customer customer = (Customer) session.getAttribute("Customer");
		
		request.setAttribute("FirstName", customer.getFirstName());
		request.setAttribute("LastName", customer.getLastName());
		request.setAttribute("UserName", customer.getUserName());
		request.setAttribute("Email", customer.getEmail());
		request.setAttribute("Discount", customer.getDiscount());
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerViews/session.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
