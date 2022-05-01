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
 * Servlet implementation class AddAirline
 */
@WebServlet("/AddAirline")
public class AddAirline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAirline() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session!=null && session.getAttribute("userName")!=null && session.getAttribute("password")!=null) {
		String airlineName = request.getParameter("airlineName");
		String scope = request.getParameter("scope");
		Airline a = new Airline();
		a.setAirlineName(airlineName);
		a.setScope(scope);
		int r = AirlineDao.validateAirline(a);
		boolean status;
		if(r!=1) {
			status = AirlineDao.addAirline(a);
		}
		else {
			status = false;
		}
		
		out.println("<html></body>");
		if(status) {
			out.println("<h1>Airline Added Successfully!</h1>");
		}
		else {
			out.println("<h1>Airline already exists/unknown error occured. Please check the Airlines list.</h1>");
		}
		out.println("<a href='airlines.jsp'>Click Here to view Airlines list.</a>");
		out.println("</body></html>");}
		else {
			out.println("<h4>Your Session has expired. Please Login again</h4>");
			out.println("<a href='LogoutServlet'>Click Here to logout and re login</a>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
