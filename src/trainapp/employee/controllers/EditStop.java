package trainapp.employee.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainapp.employee.dao.EmployeeDAO;
import trainapp.employee.dao.EmployeeDAOimpl;

@WebServlet("/EditStop")
public class EditStop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditStop() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO ed = new EmployeeDAOimpl();
		HttpSession session = request.getSession();
		
		String transitLine = (String)session.getAttribute("transitLine");
		int stopID = Integer.parseInt(request.getParameter("stopID"));
		String arrivalTime = request.getParameter("arrivalTime");
		String departureTime = request.getParameter("departureTime");
		
		int status = ed.updateStop(transitLine, stopID, arrivalTime, departureTime);
		if(status != 0) {
			response.sendRedirect("/TrainApp/Esession");
		}
	}

}
