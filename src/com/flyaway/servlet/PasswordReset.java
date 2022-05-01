package com.flyaway.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.dao.AdminDao;
import com.flyaway.entity.Admin;

/**
 * Servlet implementation class PasswordReset
 */
@WebServlet("/PasswordReset")
public class PasswordReset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordReset() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session!=null && session.getAttribute("userName")!=null && session.getAttribute("password")!=null) {
		String p1 = request.getParameter("npwd1");
		String p2 = request.getParameter("npwd2");
		String userName = request.getParameter("userName");
		String oldp = (String) session.getAttribute("password");
		Admin aold1 = AdminDao.getAdmin(userName,oldp);
		Admin anew = new Admin();
		anew.setUserName(userName);
		out.println("<a href='index.html'>Click Here for Main page</a>");
		if (p1.equals(p2)) {
			anew.setPassword(p1);
			if(AdminDao.modifyAdmin(aold1, anew)) {
				out.println("<h1>Password Reset Succesfully!</h1>");
			}
			else {
				out.println("<h1>Password Reset failed!! Kindly check your registered credentials.</h1>");
			}
			
		}else {
			out.println("<h4>Invalid Input provided!! Passwords do not match!! Please enter same desired password in both fields</h4>");
		}
		}
		else {
			out.println("<h4>Your session has expired.</h4>");
			out.println("<a href='index.html'>Click Here to Home Page</a>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
