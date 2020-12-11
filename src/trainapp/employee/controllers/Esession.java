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
import trainapp.forum.SupportTicket;
import trainapp.trainschedule.TrainSchedule;

@WebServlet("/Esession")
public class Esession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Esession() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Employee employee = (Employee) session.getAttribute("Employee");
		
		request.setAttribute("FirstName", employee.getFirstName());
		request.setAttribute("LastName", employee.getLastName());
		request.setAttribute("UserName", employee.getUserName());
		request.setAttribute("SSN", employee.getSSN());
		
		EmployeeDAO ed = new EmployeeDAOimpl();
		SupportTicket[] tickets = ed.getSupportTickets();
		session.setAttribute("tickets", tickets);
		
		TrainSchedule[] schedules = ed.getTrainSchedules();
		session.setAttribute("schedules", schedules);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeViews/session.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
