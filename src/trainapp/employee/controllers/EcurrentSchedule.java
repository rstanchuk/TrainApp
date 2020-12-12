package trainapp.employee.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainapp.customer.dao.CustomerDAO;
import trainapp.customer.dao.CustomerDAOimpl;
import trainapp.trainschedule.Stop;
import trainapp.trainschedule.TrainSchedule;

@WebServlet("/EcurrentSchedule")
public class EcurrentSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EcurrentSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesh = request.getSession();
		TrainSchedule schedule = (TrainSchedule)sesh.getAttribute("currentSchedule");
		
		CustomerDAO cd = new CustomerDAOimpl();
		Stop[] stops = cd.getStops(schedule.getTransitLine());
		
		sesh.setAttribute("stops", stops);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeViews/EcurrentSchedule.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
