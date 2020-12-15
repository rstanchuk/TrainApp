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
import trainapp.trainschedule.RevenueReport;


@WebServlet("/SalesReport")
public class SalesReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SalesReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminViews/SalesReport.jsp");
//		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO ad = new AdminDAOimpl();
		String date = request.getParameter("date");
		
		RevenueReport[] reports = ad.obtainSalesReports(date);
		float totalSales = ad.obtainTotalSales(date);
		
		HttpSession session = request.getSession();
		session.setAttribute("revenueReports", reports);
		session.setAttribute("totalSales", totalSales);
		
		request.getRequestDispatcher("/AdminViews/SalesReport.jsp").forward(request, response);
	}

}
