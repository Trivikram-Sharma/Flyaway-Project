package com.flyaway.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.flyaway.entity.Airline;
import com.flyaway.entity.Flight;
import com.flyaway.entity.Place;
import com.flyaway.util.HibernateUtil;

/**
 * Servlet implementation class ChangeFlight
 */
@WebServlet("/ChangeFlight")
public class ChangeFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeFlight() {
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
			SessionFactory sf = HibernateUtil.buildSessionFactory();
			Session s = sf.openSession();
			
			String flightId = request.getParameter("flightId");
			int airlineId = Integer.parseInt(request.getParameter("airlineName"));
			int seats = Integer.parseInt(request.getParameter("seats"));
			int source =Integer.parseInt(request.getParameter("source"));
			int destination = Integer.parseInt(request.getParameter("destination"));
			Double ticketPrice = Double.valueOf(request.getParameter("ticketPrice"));
			
			// Retrieving essential objects of session, printwriter, hibernate session etc.
			
			Query q = s.createQuery("from Place where placeId=:placeId");
			q.setParameter("placeId", source);
			Place p1 = (Place) q.getSingleResult();
			
			q.setParameter("placeId",destination);
			Place p2 = (Place) q.getSingleResult();
			
			Query aq = s.createQuery("from Airline where airlineId=:airlineId");
			aq.setParameter("airlineId", airlineId);
			Airline a = (Airline) aq.getSingleResult();
			
			Query qf = s.createQuery("from Flight where flightId=:flightId");
			qf.setParameter("flightId",flightId);
			Flight f = (Flight)qf.getSingleResult();
			f.setAirline(a);
			f.setSeats(seats);
			f.setSource(p1);
			f.setDestination(p2);
			f.setTicketPrice(ticketPrice);
			Transaction tx;
			try {
			tx = s.beginTransaction();
			//
			s.save(f);
			//
			tx.commit();
			out.println("<h1>Flight details modified. Please check the flight list</h1>");
			out.println("<a href='admin-homepage.jsp'>Click Here for admin Home page</a>");
			}
			catch(RollbackException rbe) {
				System.out.println(rbe.getMessage());
				out.println("<h2>Flight Details Not Modified. Kindly check the details and try again.</h2>");
				out.println("<a href='admin-homepage.jsp'>Click Here for admin Home page</a>");
			}
			
		}
		else {
			out.println("<h4>Your session timed out.</h4>");
			out.println("<a href='LogoutServlet'>Click Here to Logout and Login again</a>");
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
