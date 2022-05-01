package com.flyaway.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.flyaway.dao.FlightDao;
import com.flyaway.entity.Flight;
import com.flyaway.util.HibernateUtil;

/**
 * Servlet implementation class DeleteFlight
 */
@WebServlet("/DeleteFlight")
public class DeleteFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		if(session!=null && session.getAttribute("userName")!=null && session.getAttribute("password")!=null) {
			Flight f = new Flight();
			f.setFlightId(request.getParameter("fid"));
			boolean status = FlightDao.deleteFlight(f);
			if(status) {
				out.println("<h1>Flight Details deleted successfully!</h1>");
				out.println("<a href='flights.jsp'>Click Here for flight list </a>");
				out.println("<a href='admin-homepage.jsp'>Admin Home Page</a>");
			}
			else {
				out.println("<h4>Flight details not deleted!! Please check the flight details and try again!!</h4>");
				out.println("<a href='flights.jsp'>Click Here for flight list </a>");
				out.println("<a href='admin-homepage.jsp'>Admin Home Page</a>");
			}
		}
		else {
			out.println("<h4>Session timed out! Please Login again</h4>");
			out.println("<a href='index.html'>Home Page</a>");
		}
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
