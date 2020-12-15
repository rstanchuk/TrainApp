package trainapp.admin.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainapp.admin.dao.AdminDAO;
import trainapp.admin.dao.AdminDAOimpl;
import trainapp.trainschedule.Reservation;

@WebServlet("/ListReservations")
public class ListReservations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListReservations() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO ad = new AdminDAOimpl();
		Reservation[] reservations = null;
		String type = request.getParameter("type");
		if(type.equals("transit")) {
			String transitLine = request.getParameter("transitLine");
			reservations = ad.obtainReservationsByLine(transitLine);
		} else if (type.equals("name")) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			reservations = ad.obtainReservationsByName(firstName, lastName);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("reservations", reservations);
		
		request.getRequestDispatcher("/AdminViews/ListReservations.jsp").forward(request, response);
	}

}
