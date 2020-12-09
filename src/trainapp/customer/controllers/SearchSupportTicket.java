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
import trainapp.forum.SupportTicket;

@WebServlet("/SearchSupportTicket")
public class SearchSupportTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchSupportTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		CustomerDAO cd = new CustomerDAOimpl();
		String keyword = request.getParameter("keyword");
		
		SupportTicket[] searchedTickets = cd.searchByKeywordSupportTickets(keyword);
		session.setAttribute("searchedTickets", searchedTickets);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerViews/SearchSupportTicket.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
