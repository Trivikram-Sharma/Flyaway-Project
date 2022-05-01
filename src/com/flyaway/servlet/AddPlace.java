package com.flyaway.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.dao.PlaceDao;
import com.flyaway.entity.Place;

/**
 * Servlet implementation class AddPlace
 */
@WebServlet("/AddPlace")
public class AddPlace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPlace() {
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
		String city = request.getParameter("City");
		String state = request.getParameter("State");
		Place p = new Place();
		p.setCity(city);
		p.setState(state);
		int r = PlaceDao.validatePlace(p);
		boolean status;
		if(r!=1) {
			status = PlaceDao.addPlace(p);
		}
		else {
			status = false;
		}
		out.println("<html><body>");
		if(status) {
			out.println("<h1>Place Added Successfully!</h1>");
		}
		else {
			out.println("<h1>Place already exists/unknown error occured. Please check the places list.</h1>");
		}
		out.println("<a href='places.jsp'>Click Here to view Places list.</a>");
		}
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
