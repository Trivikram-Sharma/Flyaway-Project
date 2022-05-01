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
 * Servlet implementation class ChangeAirline
 */
@WebServlet("/ChangeAirline")
public class ChangeAirline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAirline() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		out.println("<body><html>");
		if(session!=null && session.getAttribute("userName")!=null && session.getAttribute("password")!=null) {
			int airlineId = Integer.parseInt(request.getParameter("airlineId"));
			String airlineName = request.getParameter("airlineName");
			String scope = request.getParameter("scope");
			Airline a = AirlineDao.getAirline(airlineId);
			a.setAirlineName(airlineName);
			a.setScope(scope);
			boolean status = AirlineDao.modifyAirline(a);
			
			if(status) {
				out.println("<h2>Airline modified successfully!</h2>");
				out.println("<a href='airlines.jsp'>Click Here for list of airlines</a>");
			}
			else {
				out.println("<h2>Flight details not modified!! Please check the details and try again.</h2>");
				out.println("<a href='airlines.jsp'>Airlines List</a>");
				out.println("<a href='admin-homepage.jsp'>Click Here for admin Homepage</a>");
			}
			
		}
		else {
			out.println("<h3>Session Timed out!! Please login and try again!</h3>");
			out.println("<a href='index.html'>Click Here to Login</a>");
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
