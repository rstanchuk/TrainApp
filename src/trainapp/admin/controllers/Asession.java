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
import trainapp.trainschedule.CustomerRevenueReport;
import trainapp.trainschedule.TransitLine;


@WebServlet("/Asession")
public class Asession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Asession() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminDAO ad = new AdminDAOimpl();
		
		Employee[] employees = ad.getEmployees();
		session.setAttribute("employees", employees);
		
		CustomerRevenueReport bestCustomer = ad.getBestCustomer();
		session.setAttribute("bestCustomer", bestCustomer);
		
		TransitLine[] lines = ad.getBestTransitLines();
		session.setAttribute("bestTransitLines", lines);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminViews/session.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
