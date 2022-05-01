package com.flyaway.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.flyaway.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.flyaway.dao.FlightDao;
import com.flyaway.entity.Airline;
import com.flyaway.entity.Flight;
import com.flyaway.entity.Place;

/**
 * Servlet implementation class AddFlight
 */
@WebServlet("/AddFlight")
public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFlight() {
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
		String flightId = request.getParameter("flightId");
		int seats = Integer.parseInt(request.getParameter("seats"));
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		int airlineId = Integer.parseInt(request.getParameter("airlineName"));
		double ticketPrice = Double.valueOf(request.getParameter("ticketPrice"));
		
		// Retrieving essential objects of session, printwriter, hibernate session etc.
		
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session s = sf.openSession();
		Query q = s.createQuery("from Place where placeId=:placeId");
		q.setParameter("placeId", Integer.parseInt(source));
		Place p1 = (Place) q.getSingleResult();
		
		q.setParameter("placeId",Integer.parseInt(destination));
		Place p2 = (Place) q.getSingleResult();
		
		Query aq = s.createQuery("from Airline where airlineId=:airlineId");
		aq.setParameter("airlineId", airlineId);
		Airline a = (Airline) aq.getSingleResult();
		Flight f = new Flight();
		f.setFlightId(flightId);
		f.setSeats(seats);
		f.setSource(p1);
		f.setDestination(p2);
		f.setAirline(a);
		f.setTicketPrice(ticketPrice);
		
		out.println("<html><body>");
		int r = FlightDao.validateFlight(f);
		if(r==0) {
			boolean b = FlightDao.addFlight(f);
			if(b) {
				out.println("<h1>Flight Details added successfully!</h1>");
			}
			else {
				out.println("<h1>Details are not added. Please check the flight details and try again!</h1>");
			}
		}
		else {
			out.println("<h1>The flight details with provided input already exist. Please check the flight list and try again.</h1");
		}
		
		out.println("<a href='admin-homepage.html'>Click Here for Home page</a>");
		out.println("<a href='flights.jsp'>Click Here for flight list</a>");}
		
		else {
			out.println("<h4>Your Session has expired. Please Login again</h4>");
			out.println("<a href='LogoutServlet'>Click Here to logout and re login</a>");
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
