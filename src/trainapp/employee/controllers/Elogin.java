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

@WebServlet("/Elogin")
public class Elogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Elogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeViews/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO ed = new EmployeeDAOimpl();
		String userName = request.getParameter("username");
		String password = request.getParameter("password1");
		
		if(userName.isBlank() || password.isBlank()) {
			request.setAttribute("message", "All fields must be filled out!");
			request.getRequestDispatcher("/EmployeeViews/login.jsp").forward(request, response);
		} else {
			if(ed.doesEmployeeExist(userName)) {
				Employee empl = ed.getEmployee(userName, password);
				if(empl != null && empl.getFirstName() != null) {
					HttpSession session = request.getSession();
	                session.setAttribute("Employee", empl);
	                response.sendRedirect("/TrainApp/Esession");
				} else {
					request.setAttribute("message", "Password is wrong!");
					request.getRequestDispatcher("/EmployeeViews/login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("message", "User doesn't exist!");
				request.getRequestDispatcher("/CustomerViews/login.jsp").forward(request, response);
			}
		}
	}

}
