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
import trainapp.employee.Employee;


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
		String lastName = request.getParameter("lastName");
		
		if(ssn.isBlank() || userName.isBlank() || password.isBlank() || firstName.isBlank() || lastName.isBlank()) {
			request.setAttribute("error", "All fields must be filled out!");
			request.getRequestDispatcher("/AdminViews/AddEmployee.jsp").forward(request, response);
		} else {
			Employee empl = new Employee();
			empl.setSSN(ssn);
			empl.setUserName(userName);
			empl.setPassword(password);
			empl.setFirstName(firstName);
			empl.setLastName(lastName);
			
			int status = ad.insertEmployee(empl);
			if(status != 0) {
				response.sendRedirect("/TrainApp/Asession");
			} else {
				request.setAttribute("error", "Database Error!");
				request.getRequestDispatcher("/AdminViews/AddEmployee.jsp").forward(request, response);
			}
		}
	}

}
