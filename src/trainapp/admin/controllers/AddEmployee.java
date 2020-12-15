package trainapp.admin.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainapp.admin.dao.AdminDAO;
import trainapp.admin.dao.AdminDAOimpl;


@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public AddEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminViews/AddEmployee.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO ad = new AdminDAOimpl();
		String ssn = request.getParameter("ssn");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		
//		if(password.isBlank()) {
//			request.setAttribute("message", "All fields must be filled out!");
//			request.getRequestDispatcher("/AdminViews/login.jsp").forward(request, response);
//		} else {
//			if(ad.verifyAdmin(password)) {
//				response.sendRedirect("/TrainApp/Asession");
//			} else {
//				request.setAttribute("message", "Wrong Password!");
//				request.getRequestDispatcher("/AdminViews/login.jsp").forward(request, response);
//			}
//		}
	}

}
