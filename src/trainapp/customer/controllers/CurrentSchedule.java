package trainapp.customer.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainapp.customer.Customer;
import trainapp.customer.dao.CustomerDAO;
import trainapp.customer.dao.CustomerDAOimpl;
import trainapp.trainschedule.Reservation;
import trainapp.trainschedule.Station;
import trainapp.trainschedule.Stop;
import trainapp.trainschedule.TrainSchedule;


@WebServlet("/CurrentSchedule")
public class CurrentSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CurrentSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesh = request.getSession();
		TrainSchedule schedule = (TrainSchedule)sesh.getAttribute("currentschedule");
		
		CustomerDAO cd = new CustomerDAOimpl();
		Stop[] stops = cd.getStops(schedule.getTransitLine());
		
		sesh.setAttribute("stops", stops);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerViews/CurrentSchedule.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO cd = new CustomerDAOimpl();
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		String round = request.getParameter("round");
		
		
		if(origin != null && destination != null) {
			int originNum = Integer.parseInt(origin);
			int destNum = Integer.parseInt(destination);
			if(originNum == destNum) {
				request.setAttribute("error", "You can't make this reservation");
				request.getRequestDispatcher("/CustomerViews/CurrentSchedule.jsp").forward(request, response);
			} else {
				Station currentOrigin = cd.getStation(originNum);
				Station currentDestination = cd.getStation(destNum);
				
				
				HttpSession sesh = request.getSession();
				TrainSchedule schedule = (TrainSchedule)sesh.getAttribute("currentschedule");
				Customer c = (Customer)sesh.getAttribute("Customer");
				Stop[] stops = (Stop[])sesh.getAttribute("stops");
				
				float fare = schedule.getFare();
				int numOfStopsOnTransit = cd.numOfStops(schedule);

				float pricePerStop = fare / (float)numOfStopsOnTransit;
				
				int numOfStops = destNum - originNum;
				numOfStops = Math.abs(numOfStops);
				if(numOfStops == 0) {
					numOfStops = 1;
				}
				float discount = 0;
				if(c.getDiscount() > 0) {
					discount = c.getDiscount() / 100;
				}
				
				float customerPrice = (float)numOfStops * pricePerStop;
				if(round != null) {
					customerPrice *= 2;
				}
				customerPrice *= (1 - discount);
				
				String userName = c.getUserName();
				String transitLine = schedule.getTransitLine();
				origin = currentOrigin.getName() + "-" + currentOrigin.getCity() + "-" + currentOrigin.getState();
				destination = currentDestination.getName() + "-" + currentDestination.getCity() + "-" + currentDestination.getState();
				String departureTime = null;
				for(int i = 0; i < stops.length; i++) {
					if(stops[i].getStationID() == originNum) {
						departureTime = stops[i].getDepartTime();
					}
				}
				String arrivalTime = null;
				for(int i = 0; i < stops.length; i++) {
					if(stops[i].getStationID() == destNum) {
						arrivalTime = stops[i].getArrivalTime();
					}
				}
				boolean roundTrip = false;
				if(round != null) {
					roundTrip = true;
				}
				float price = customerPrice;
				
				Reservation res = new Reservation();
				res.setUserName(userName);
				res.setTransitLine(transitLine);
				res.setOrigin(origin);
				res.setDestination(destination);
				res.setDepartureTime(departureTime);
				res.setArrivalTime(arrivalTime);
				res.setRoundTrip(roundTrip);
				res.setPrice(price);
				res.setCurrent(true);
				
				int status = cd.insertReservation(res);
				if(status != 0) {
					response.sendRedirect("/TrainApp/Session");
				} else {
					request.setAttribute("error", "Database Error!");
					request.getRequestDispatcher("/CustomerViews/CurrentSchedule.jsp").forward(request, response);
				}
			}
		}
	}

}
