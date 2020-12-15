package trainapp.admin.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainapp.admin.dao.AdminDAO;
import trainapp.admin.dao.AdminDAOimpl;
import trainapp.employee.Employee;

@WebServlet("/EditEmployee")
public class EditEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminViews/EditEmployee.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminDAO ad = new AdminDAOimpl();
		
		Employee empl = (Employee)session.getAttribute("employee");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		if(firstName.isBlank() || lastName.isBlank()) {
			request.setAttribute("error", "All fields must be filled out!");
			request.getRequestDispatcher("/AdminViews/EditEmployee.jsp").forward(request, response);
		} else {
			int status = ad.updateEmployee(empl.getUserName(), firstName, lastName);
			if(status != 0) {
				response.sendRedirect("/TrainApp/Asession");
			} else {
				request.setAttribute("error", "Database Error!");
				request.getRequestDispatcher("/AdminViews/EditEmployee.jsp").forward(request, response);
			}
		}
	}

}
