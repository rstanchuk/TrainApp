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
import trainapp.trainschedule.CustomerRevenueReport;
import trainapp.trainschedule.RevenueReport;

@WebServlet("/RevenueListing")
public class RevenueListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RevenueListing() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO ad = new AdminDAOimpl();
		
		HttpSession session = request.getSession();
		request.setAttribute("totalRevenue", ad.getRevenueTotal());

		String type = request.getParameter("type");
		if(type.equals("transit")) {
			RevenueReport[] reports = ad.getRevenueForLine();
			
			session.setAttribute("reports", reports);
			
			request.getRequestDispatcher("/AdminViews/RevenueListingTransitLine.jsp").forward(request, response);
		} else if (type.equals("name")) {
			CustomerRevenueReport[] reports = ad.getRevenueForCustomer();
			
			session.setAttribute("reports", reports);
			
			request.getRequestDispatcher("/AdminViews/RevenueListingCustomer.jsp").forward(request, response);
		}
		
		
	}

}
