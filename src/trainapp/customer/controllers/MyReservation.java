package trainapp.customer.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainapp.trainschedule.Reservation;


@WebServlet("/MyReservation")
public class MyReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyReservation() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesh = request.getSession();
		Reservation reservation = (Reservation)sesh.getAttribute("reservation");
		
		request.setAttribute("id", reservation.getReservationID());
		request.setAttribute("username", reservation.getUserName());
		request.setAttribute("line", reservation.getTransitLine());
		request.setAttribute("origin", reservation.getOrigin());
		request.setAttribute("dest", reservation.getDestination());
		request.setAttribute("deptime", reservation.getDepartureTime());
		request.setAttribute("arrtime", reservation.getArrivalTime());
		request.setAttribute("round", reservation.isRoundTrip());
		request.setAttribute("price", reservation.getPrice());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerViews/MyReservation.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
