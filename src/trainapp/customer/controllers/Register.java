package trainapp.customer.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainapp.customer.Customer;
import trainapp.customer.dao.CustomerDAO;
import trainapp.customer.dao.CustomerDAOimpl;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerViews/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO cd = new CustomerDAOimpl();
		String userName = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		String firstName = request.getParameter("FirstName");
		String lastName = request.getParameter("LastName");
		boolean isChild = Boolean.parseBoolean(request.getParameter("isChild"));
		boolean isSenior = Boolean.parseBoolean(request.getParameter("isSenior"));
		boolean isDisabled = Boolean.parseBoolean(request.getParameter("isDisabled"));
		
		if(userName.isBlank() || password1.isBlank() || password2.isBlank() || email.isBlank() || firstName.isBlank()
				|| lastName.isBlank()) {
			request.setAttribute("message", "Some of the fields are empty!");
			request.getRequestDispatcher("/CustomerViews/register.jsp").forward(request, response);
		} else if(!password1.equals(password2)) {
			request.setAttribute("message", "Passwords don't match!");
			request.getRequestDispatcher("/CustomerViews/register.jsp").forward(request, response);
		} else if(isChild == true && isSenior == true) {
			request.setAttribute("message", "You can't be a child and a senior at the same time!");
			request.getRequestDispatcher("/CustomerViews/register.jsp").forward(request, response);
		} else if(cd.doesCustomerExist(userName)) {
			request.setAttribute("message", "Username already exist!");
			request.getRequestDispatcher("/CustomerViews/register.jsp").forward(request, response);
		} else {
			float discount = 0;
			
			float childDiscount = 25;
			float seniorDiscount = 35;
			float disabledDiscount = 50;
			
			if(isChild) { discount = childDiscount; }
			if(isSenior) { discount = seniorDiscount; }
			if(isDisabled) { discount = disabledDiscount; }
			
			Customer c = new Customer();
			c.setUserName(userName);
			c.setPassword(password1);
			c.setEmail(email);
			c.setFirstName(firstName);
			c.setLastName(lastName);
			c.setChild(isChild);
			c.setSenior(isSenior);
			c.setDisabled(isDisabled);
			c.setDiscount(discount);
			
			int result = cd.insertCustomer(c);
			
			if(result != 0) {
				//Go back to login page
				response.sendRedirect("/TrainApp/Login");
			} else {
				request.setAttribute("message", "Database error!");
				request.getRequestDispatcher("/CustomerViews/register.jsp").forward(request, response);
			}
		}
		
	}

}
