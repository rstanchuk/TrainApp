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


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerViews/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO cd = new CustomerDAOimpl();
		String userName = request.getParameter("username");
		String password = request.getParameter("password1");
		
		if(userName.isBlank() || password.isBlank()) {
			request.setAttribute("message", "All fields must be filled out!");
			request.getRequestDispatcher("/CustomerViews/login.jsp").forward(request, response);
		} else {
			if(cd.doesCustomerExist(userName)) {
				Customer c = cd.getCustomer(userName, password);
				if(c != null && c.getFirstName() != null) {
					HttpSession session = request.getSession();
	                session.setAttribute("Customer", c);
	                response.sendRedirect("/TrainApp/Session");
				} else {
					request.setAttribute("message", "Password is wrong!");
					request.getRequestDispatcher("/CustomerViews/login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("message", "User doesn't exist!");
				request.getRequestDispatcher("/CustomerViews/login.jsp").forward(request, response);
			}
		}
	}

}