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
import trainapp.trainschedule.TrainSchedule;

@WebServlet("/EsearchTrainSchedules")
public class EsearchTrainSchedules extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EsearchTrainSchedules() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmployeeDAO ed = new EmployeeDAOimpl();
		
		int stationID = Integer.parseInt(request.getParameter("stationID"));
		String location = request.getParameter("location");

		if(location.equals("origin")) {
			TrainSchedule[] searchedSchedules = ed.getTrainSchedulesByOrigin(stationID);
			session.setAttribute("searchedSchedules", searchedSchedules);
			
		} else if(location.equals("destination")) {
			TrainSchedule[] searchedSchedules = ed.getTrainSchedulesByDestination(stationID);
			session.setAttribute("searchedSchedules", searchedSchedules);
			
		} else if(location.equals("all")) {
			TrainSchedule[] searchedSchedules = ed.getTrainSchedulesAll(stationID);
			session.setAttribute("searchedSchedules", searchedSchedules);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeViews/EsearchTrainSchedules.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
