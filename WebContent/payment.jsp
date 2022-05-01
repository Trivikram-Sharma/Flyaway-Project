<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.flyaway.entity.Flight" %>
    <%@ page import="com.flyaway.dao.*" %>
    <%@ page import="com.flyaway.util.*" %>
    <%@ page import="org.hibernate.*" %>
    <%@ page import="java.util.*" %>
    <%@ page import="java.lang.*" %>
    <%@ page import="javax.persistence.Query" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
	body{text-align: center}
</style>
<title>Payment Page</title>
</head>
<body>
	<%
		String fid = (String)session.getAttribute("fid");
		int np = Integer.valueOf((String)session.getAttribute("np"));
		System.out.println(np);
		String pname = request.getParameter("pname");
		long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
		String emailId = request.getParameter("emailId");
		Flight f = FlightDao.getFlight(fid);
		
		//Setting info on session
		session.setAttribute("fid",fid);
		session.setAttribute("pname",pname);
		session.setAttribute("phoneNumber",phoneNumber);
		session.setAttribute("emailId",emailId);
		session.setAttribute("np",np);
	%>
	<form action="payment-status.jsp" method="post">
	Flight No : <%=fid %><br />
	Flight starts at: <%=f.getSource().getCity() %><br />
	Flight Lands at: <%=f.getDestination().getCity() %><br />
	Airline : <%=f.getAirline().getAirlineName() %><br />
	Ticket Price per seat :<%=f.getTicketPrice() %><br />
	--------------------------------------------------------<br />
	Name : <%=pname %><br />
	Phone Number: <%=phoneNumber %><br />
	Email-ID: <%=emailId %> <br />
	Number of Persons Travelling/ Seats booked: <%=np %><br />
	---------------------------------------------------------<br />
	<h5>Please verify above details and proceed to Payment below.</h5>
	Amount:
	<input type="number" name="amount" id="amount"><br>
	<input type="submit" name="Submit" id="submit" value="Pay Now">
	</form>
</body>
</html>