package trainapp.employee.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainapp.employee.dao.EmployeeDAO;
import trainapp.employee.dao.EmployeeDAOimpl;

@WebServlet("/ETrainScheduleEdit")
public class ETrainScheduleEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ETrainScheduleEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		TrainSchedule schedule = (TrainSchedule)session.getAttribute("schedule");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeViews/ETrainScheduleEdit.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO ed = new EmployeeDAOimpl();
		HttpSession session = request.getSession();
		
		String transitLine = (String)session.getAttribute("nameOfTransitLine");
		String departureTime = request.getParameter("departureTime");
		String arrivalTime = request.getParameter("arrivalTime");
		String originStationIDstr = request.getParameter("originStationID");
		String destinationStationIDstr = request.getParameter("destinationStationID");
		String fareStr = request.getParameter("fare");
		String trainIDstr = request.getParameter("trainID");
		
		int originStationID = 0;
		int destinationStationID = 0;
		float fare = 0;
		int trainID = 0;
		
		if(originStationIDstr != null) {
			originStationID = Integer.parseInt(originStationIDstr);
		}
		
		if(destinationStationIDstr != null) {
			destinationStationID = Integer.parseInt(destinationStationIDstr);
		}
		
		if(fareStr != null) {
			fare = Float.parseFloat(fareStr);
		}
		
		if(trainIDstr != null) {
			trainID = Integer.parseInt(trainIDstr);
		}	
		
		if(originStationID != 0 && destinationStationID != 0 && fare != 0 && trainID != 0) {
			int status = ed.updateTrainSchedule(transitLine, departureTime, arrivalTime, originStationID, destinationStationID, fare, trainID);
			if(status != 0) {
				response.sendRedirect("/TrainApp/Esession");
			} else {
				request.setAttribute("error", "Database error!");
				request.getRequestDispatcher("/EmployeeViews/ETrainScheduleEdit.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "Error!");
			request.getRequestDispatcher("/EmployeeViews/ETrainScheduleEdit.jsp").forward(request, response);
		}

	}

}
