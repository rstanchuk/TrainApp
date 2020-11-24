package trainapp.employee.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainapp.employee.Employee;
import trainapp.employee.dao.EmployeeDAO;
import trainapp.employee.dao.EmployeeDAOimpl;

@WebServlet("/Eregister")
public class Eregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Eregister() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeViews/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO ed = new EmployeeDAOimpl();
		String userName = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String SSN = request.getParameter("SSN");
		String firstName = request.getParameter("FirstName");
		String lastName = request.getParameter("LastName");
		
		if(userName.isBlank() || password1.isBlank() || password2.isBlank() || SSN.isBlank() || firstName.isBlank()
				|| lastName.isBlank()) {
			request.setAttribute("message", "Some of the fields are empty!");
			request.getRequestDispatcher("/EmployeeViews/register.jsp").forward(request, response);
		} else if(!password1.equals(password2)) {
			request.setAttribute("message", "Passwords don't match!");
			request.getRequestDispatcher("/EmployeeViews/register.jsp").forward(request, response);
		} else if(ed.doesEmployeeExist(userName)) {
			request.setAttribute("message", "Username already exist!");
			request.getRequestDispatcher("/EmployeeViews/register.jsp").forward(request, response);
		} else {
			Employee empl = new Employee();
			empl.setSSN(SSN);
			empl.setUserName(userName);
			empl.setPassword(password1);
			empl.setFirstName(firstName);
			empl.setLastName(lastName);
			
			int result = ed.insertEmployee(empl);
			
			if(result != 0) {
				//Go back to login page
				response.sendRedirect("/TrainApp/Elogin");
			} else {
				request.setAttribute("message", "Database error!");
				request.getRequestDispatcher("/EmployeeViews/register.jsp").forward(request, response);
			}
			
		}
	}

}
