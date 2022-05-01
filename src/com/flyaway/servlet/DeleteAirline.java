package com.flyaway.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.dao.AirlineDao;
import com.flyaway.entity.Airline;

/**
 * Servlet implementation class DeleteAirline
 */
@WebServlet("/DeleteAirline")
public class DeleteAirline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAirline() {
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

			int airlineId = Integer.parseInt(request.getParameter("aid"));
			Airline a = AirlineDao.getAirline(airlineId);
			boolean status = AirlineDao.deleteAirline(a);
			if(status) {
				out.println("<h2>Airline deleted successfully.</h2>");
				out.println("<a href='airlines.jsp'>Click Here for Airline List</a>");
			}
			else {
				out.println("<h2>Airline not deleted!! Please check the details and try again.</h2>");
				out.println("<a href='airlines.jsp'>Airlines List</a>");
				out.println("<a href='admin-homepage.jsp'>Admin Homepage</a>");
			}
	
		}
		else {
			out.println("<h2>Session timed out!! Please login again.</h2>");
			out.println("<a href='index.html'>Click Here to Login</a>");
		}
		out.println("</html></body>");
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
