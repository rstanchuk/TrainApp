package trainapp.customer.controllers;

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
import trainapp.trainschedule.TrainSchedule;

@WebServlet("/SearchTrainSchedule")
public class SearchTrainSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchTrainSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerDAO cd = new CustomerDAOimpl();
		TrainSchedule[] searchSchedule = null;
		
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		String date = request.getParameter("date");
		String sortby = request.getParameter("sortby");
		
		if(origin.equals("all") && destination.equals("all")) {
			searchSchedule = cd.getTrainSchedules();
		} else if(sortby.equals("arrival")) {
			searchSchedule = cd.getTrainSchedulesByArrival(origin, destination, date);
		} else if(sortby.equals("departure")) {
			searchSchedule = cd.getTrainSchedulesByDeparture(origin, destination, date);
		} else if(sortby.equals("fare")) {
			searchSchedule = cd.getTrainSchedulesByFare(origin, destination, date);
		}
		
		session.setAttribute("searchSchedule", searchSchedule);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerViews/SearchTrainSchedule.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
