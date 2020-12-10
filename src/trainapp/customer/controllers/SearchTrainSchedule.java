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
		String searchby = request.getParameter("searchby");
		String keyword = request.getParameter("keyword");
		String sortby = request.getParameter("sortby");
		
		TrainSchedule[] searchSchedule = null;
		
		if(searchby.equals("all")) {
			searchSchedule = cd.getTrainSchedules();
		} else if(searchby.equals("origin")) {
			if(sortby.equals("arrival")) {
				searchSchedule = cd.getTrainSchedulesByOriginOrderByArrival(keyword);
			} else if(sortby.equals("departure")) {
				searchSchedule = cd.getTrainScheduleByOriginOrderByDeparture(keyword);
			} else if(sortby.equals("fare")) {
				searchSchedule = cd.getTrainScheduleByOriginOrderByFare(keyword);
			}
		} else if(searchby.equals("destination")) {
			if(sortby.equals("arrival")) {
				searchSchedule = cd.getTrainScheduleByDestinationOrderByArrival(keyword);
			} else if(sortby.equals("departure")) {
				searchSchedule = cd.getTrainScheduleByDestinationOrderByDeparture(keyword);
			} else if(sortby.equals("fare")) {
				searchSchedule = cd.getTrainScheduleByDestinationOrderByFare(keyword);
			}
		} else if(searchby.equals("date")) {
			if(sortby.equals("arrival")) {
				searchSchedule = cd.getTrainScheduleByDateOrderByArrival(keyword);
			} else if(sortby.equals("departure")) {
				searchSchedule = cd.getTrainScheduleByDateOrderByDeparture(keyword);
			} else if(sortby.equals("fare")) {
				searchSchedule = cd.getTrainScheduleByDateOrderByFare(keyword);
			}
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
